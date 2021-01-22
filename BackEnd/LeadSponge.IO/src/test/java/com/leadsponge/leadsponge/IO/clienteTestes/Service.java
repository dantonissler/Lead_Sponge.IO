package com.leadsponge.leadsponge.IO.clienteTestes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionSystemException;

import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.repository.Filter.ClienteFilter;
import com.leadsponge.IO.repository.cliente.ClienteRepository;
import com.leadsponge.IO.services.ClienteService;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DisplayName("Testando a service da Cliente")
@SpringBootTest(classes = LeadSpongeApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Service {

	@Autowired
	private ClienteService service;

	@Autowired
	private ClienteRepository repository;

	@AfterEach
	private void setUp() {
		repository.deleteAll();
	}

	@Test
	@Rollback(true)
	@DisplayName("Criar Campanha com nome, url e resumo, percistir os dados.")
	public void Criar() {
		Cliente cliente = this.service.salvar(new Cliente(1L, "nome", "url", "resumo", new ArrayList<Contato>(), new ArrayList<Negociacao>(), new ArrayList<Segmento>(), new ArrayList<Usuario>(), Mockito.any(Usuario.class)));
		assertThat(cliente.getId()).isNotNull();
		assertEquals("nome", cliente.getNome());
		assertEquals("url", cliente.getUrl());
		assertEquals("resumo", cliente.getResumo());
	}

	@Test
	@Rollback(true)
	@DisplayName("Deletar verificar se foi removido.")
	public void deletar() {
		Cliente cliente = this.service.salvar(new Cliente(1L, "nome", "url", "resumo", new ArrayList<Contato>(), new ArrayList<Negociacao>(), new ArrayList<Segmento>(), new ArrayList<Usuario>(), Mockito.any(Usuario.class)));
		service.deletar(cliente.getId());
		assertThat(repository.findById(cliente.getId())).isEmpty();
	}

	@Test
	@DisplayName("Detalhar retornar uma entidade selecionada por id de campanha.")
	public void detalhar() {
		Cliente clienteSalva = this.repository.save(new Cliente(1L, "nome", "url", "resumo", null, null, null, null, null));
		Cliente cliente = service.deletar(clienteSalva.getId());
		assertThat(cliente.getId()).isNotNull();
		assertEquals("nome", cliente.getNome());
		assertEquals("url", cliente.getUrl());
		assertEquals("resumo", cliente.getResumo());
	}

	@Test
	@Rollback(true)
	@DisplayName("Filtrar itens selecionados ")
	public void filtrarPorTodos() {
		repository.saveAll(Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome2", "url2", "resumo2", null, null, null, null, null)));
		ClienteFilter filtro = new ClienteFilter("nome", "url", "resumo");
		Pageable pageable = PageRequest.of(0, 10);
		Page<Cliente> campanhaPage = service.filtrar(filtro, pageable);
		assertEquals("nome1", campanhaPage.getContent().get(0).getNome());
		assertEquals("url1", campanhaPage.getContent().get(0).getUrl());
		assertEquals("resumo1", campanhaPage.getContent().get(0).getResumo());
	}

	@Test
	@Rollback(true)
	@DisplayName("Filtrar itens selecionados filtrar por nome.")
	public void filtrarPorNome() {
		repository.saveAll(Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome2", "url2", "resumo2", null, null, null, null, null)));
		ClienteFilter filtro = new ClienteFilter("nome", null, null);
		Pageable pageable = PageRequest.of(0, 2);
		Page<Cliente> clientePage = service.filtrar(filtro, pageable);
		assertEquals("nome1", clientePage.getContent().get(0).getNome());
		assertEquals("url1", clientePage.getContent().get(0).getUrl());
		assertEquals("resumo1", clientePage.getContent().get(0).getResumo());
		assertEquals("nome2", clientePage.getContent().get(1).getNome());
		assertEquals("url2", clientePage.getContent().get(1).getUrl());
		assertEquals("resumo2", clientePage.getContent().get(1).getResumo());
		assertThat(clientePage.getSize()).isEqualTo(2);
	}

	@Test
	@Rollback(true)
	@DisplayName("Filtrar itens selecionados filtrar por descrição.")
	public void filtrarPorNomeEUrl() {
		repository.saveAll(Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome", "url", "resumo", null, null, null, null, null)));
		ClienteFilter filtro = new ClienteFilter("nome1", "url1", null);
		Pageable pageable = PageRequest.of(0, 10);
		Page<Cliente> campanhaPage = service.filtrar(filtro, pageable);
		assertEquals("nome1", campanhaPage.getContent().get(0).getNome());
		assertEquals("url1", campanhaPage.getContent().get(0).getUrl());
		assertEquals("resumo1", campanhaPage.getContent().get(0).getResumo());
	}

	@Test
	@Rollback(true)
	@DisplayName("Filtrar itens selecionados filtrar por descrição.")
	public void filtrarPorNomeEResumo() {
		repository.saveAll(Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome", "url", "resumo", null, null, null, null, null)));
		ClienteFilter filtro = new ClienteFilter("nome1", null, "resumo1");
		Pageable pageable = PageRequest.of(0, 10);
		Page<Cliente> campanhaPage = service.filtrar(filtro, pageable);
		assertEquals("nome1", campanhaPage.getContent().get(0).getNome());
		assertEquals("url1", campanhaPage.getContent().get(0).getUrl());
		assertEquals("resumo1", campanhaPage.getContent().get(0).getResumo());
	}

	@Test
	@Rollback(true)
	@DisplayName("Filtrar itens selecionados filtrar por descrição.")
	public void filtrarPorResumo() {
		repository.saveAll(Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome", "url", "resumo", null, null, null, null, null)));
		ClienteFilter filtro = new ClienteFilter(null, null, "resumo1");
		Pageable pageable = PageRequest.of(0, 10);
		Page<Cliente> campanhaPage = service.filtrar(filtro, pageable);
		assertEquals("nome1", campanhaPage.getContent().get(0).getNome());
		assertEquals("url1", campanhaPage.getContent().get(0).getUrl());
		assertEquals("resumo1", campanhaPage.getContent().get(0).getResumo());
	}

	@Test
	@Rollback(true)
	@DisplayName("Atualizar uma entidade de cliente por id.")
	public void atualizar() {
		Cliente clienteSalva = this.repository.save(new Cliente(1L, "nome", "url", "resumo", null, null, null, null, null));
		Cliente cliente = this.service.atualizar(clienteSalva.getId(),
				new Cliente(1L, "nome2", "url2", "resumo2", new ArrayList<Contato>(), new ArrayList<Negociacao>(), new ArrayList<Segmento>(), new ArrayList<Usuario>(), Mockito.any(Usuario.class)));
		assertThat(cliente.getId()).isNotNull();
		assertEquals("nome2", cliente.getNome());
		assertEquals("url2", cliente.getUrl());
		assertEquals("resumo2", cliente.getResumo());
	}

	@Test
	@Rollback(true)
	@DisplayName("Criar Campanha com nome e descrição, percistir os dados.")
	public void CriarComNomeNull() {
		Exception exception = assertThrows(TransactionSystemException.class, () -> this.repository.save(new Cliente(1L, null, "url", "resumo", null, null, null, null, null)));
		assertTrue(exception.getMessage().contains("Error while committing the transaction"));
	}
//	this.repository.save(new Cliente(1L, "nome", "url", "resumo", Arrays.asList(Mockito.any(Contato.class)), Arrays.asList(Mockito.any(Negociacao.class)),
//			Arrays.asList(Mockito.any(Segmento.class)), Arrays.asList(Mockito.any(Usuario.class)), Mockito.any(Usuario.class))));
}
