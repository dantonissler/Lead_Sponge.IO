package com.leadsponge.leadsponge.IO.CampanhaTestes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionSystemException;

import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.Filter.CampanhaFilter;
import com.leadsponge.IO.repository.campanha.CampanhaRepository;
import com.leadsponge.IO.services.CampanhaService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Testando a service da Campanha")
public class Service {

	@Autowired
	private CampanhaService service;

	@Autowired
	private CampanhaRepository repository;

	@Mock
	private Page<Campanha> campanhaPage;

	@AfterEach
	private void setUp() {
		repository.deleteAll();
	}

	@Test
	@Rollback(true)
	@DisplayName("Criar Campanha com nome e descrição, percistir os dados.")
	public void Criar() {
		Campanha campanha = this.service.salvar(new Campanha(1L, "Nome de uma campanha", "Descrição de uma campanha"));
		assertThat(campanha.getId()).isNotNull();
		assertEquals("Nome de uma campanha", campanha.getNome());
		assertEquals("Descrição de uma campanha", campanha.getDescricao());
	}

	@Test
	@Rollback(true)
	@DisplayName("Criar Campanha com nome e descrição, percistir os dados.")
	public void CriarNomeNull() {
		Campanha campanha = this.service.salvar(new Campanha(1L, "Nome de uma campanha", "Descrição de uma campanha"));
		assertThat(campanha.getId()).isNotNull();
		assertEquals("Nome de uma campanha", campanha.getNome());
		assertEquals("Descrição de uma campanha", campanha.getDescricao());
	}

	@Test
	@Rollback(true)
	@DisplayName("Deletar verificar se foi removido.")
	public void deletar() {
		Campanha campanha = this.service.salvar(new Campanha(1L, "Nome de uma campanha", "Descrição de uma campanha"));
		service.deletar(campanha.getId());
		assertThat(repository.findById(campanha.getId())).isEmpty();
	}

	@Test
	@DisplayName("Detalhar retornar uma entidade selecionada por id de campanha.")
	public void detalhar() {
		Campanha campanhaSalva = this.repository.save(new Campanha(1L, "Nome", "Descrição"));
		Campanha campanha = service.deletar(campanhaSalva.getId());
		assertThat(campanha.getId()).isNotNull();
		assertEquals("Nome", campanha.getNome());
		assertEquals("Descrição", campanha.getDescricao());
	}

	@Test
	@Rollback(true)
	@DisplayName("Filtrar itens selecionados ")
	public void filtrar() {
		repository.saveAll(Arrays.asList(new Campanha(null, "Nome1", "Descrição1"), new Campanha(null, "Nome2", "Descrição2")));
		CampanhaFilter filtro = new CampanhaFilter("Nome1", "Descrição1");
		Pageable pageable = PageRequest.of(0, 10);
		Page<Campanha> campanhaPage = service.filtrar(filtro, pageable);
		assertEquals("Nome1", campanhaPage.getContent().get(0).getNome());
		assertEquals("Descrição1", campanhaPage.getContent().get(0).getDescricao());
	}

	@Test
	@Rollback(true)
	@DisplayName("Filtrar itens selecionados filtrar por nome.")
	public void filtrarPorNome() {
		repository.saveAll(Arrays.asList(new Campanha(null, "DanNome1", "Descrição1"), new Campanha(null, "DanNome2", "Descrição2")));
		CampanhaFilter filtro = new CampanhaFilter("DanNome", null);
		Pageable pageable = PageRequest.of(0, 2);
		Page<Campanha> campanhaPage = service.filtrar(filtro, pageable);
		assertEquals("DanNome1", campanhaPage.getContent().get(0).getNome());
		assertEquals("Descrição1", campanhaPage.getContent().get(0).getDescricao());
		assertEquals("DanNome2", campanhaPage.getContent().get(1).getNome());
		assertEquals("Descrição2", campanhaPage.getContent().get(1).getDescricao());
		assertThat(campanhaPage.getSize()).isEqualTo(2);
	}

	@Test
	@Rollback(true)
	@DisplayName("Filtrar itens selecionados filtrar por descrição.")
	public void filtrarPorDescricao() {
		repository.saveAll(Arrays.asList(new Campanha(null, "Nome1", "DanDescrição1"), new Campanha(null, "Nome2", "Descrição2")));
		CampanhaFilter filtro = new CampanhaFilter(null, "DanDescrição1");
		Pageable pageable = PageRequest.of(0, 10);
		Page<Campanha> campanhaPage = service.filtrar(filtro, pageable);
		assertEquals("Nome1", campanhaPage.getContent().get(0).getNome());
		assertEquals("DanDescrição1", campanhaPage.getContent().get(0).getDescricao());
	}

	@Test
	@Rollback(true)
	@DisplayName("Atualizar uma entidade de campanha por id.")
	public void atualizar() {
		Campanha campanhaSalva = this.service.salvar(new Campanha(1L, "Nome1", "Descrição1"));
		Campanha campanha = this.service.atualizar(campanhaSalva.getId(), new Campanha(null, "Nome2", "Descrição2"));
		assertThat(campanha.getId()).isNotNull();
		assertEquals("Nome2", campanha.getNome());
		assertEquals("Descrição2", campanha.getDescricao());
	}

	// TODO validar anotações.
	@Test
	@Rollback(true)
	@DisplayName("Criar Campanha com nome e descrição, percistir os dados.")
	public void CriarComNomeNull() {
		Exception exception = assertThrows(TransactionSystemException.class, () -> service.salvar(new Campanha(1L, "a", "Descrição de uma campanha")));
		assertTrue(exception.getMessage().contains("Error while committing the transaction"));
	}
}
