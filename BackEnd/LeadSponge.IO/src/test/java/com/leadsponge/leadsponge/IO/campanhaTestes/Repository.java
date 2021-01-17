package com.leadsponge.leadsponge.IO.campanhaTestes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.campanha.CampanhaRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Testando o repositorio da Campanha")
public class Repository {

	@Autowired
	private CampanhaRepository repository;

	@AfterEach
	private void setUp() {
		repository.deleteAll();
	}

	@Test
	@Rollback(true)
	@DisplayName("Criar Campanha e persistir os dados, retornar: id, nome e descrição")
	public void criar() {
		Campanha campanha = new Campanha(null, "Nome", "Descrição");
		campanha = this.repository.save(campanha);
		Campanha campanhaNovo = repository.findById(campanha.getId()).orElse(null);
		assertThat(campanhaNovo.getId()).isNotNull();
		assertEquals("Nome", campanhaNovo.getNome());
		assertEquals("Descrição", campanhaNovo.getDescricao());
	}

	@Test
	@Rollback(true)
	@DisplayName("Deletar uma Campanha")
	public void deletar() {
		Campanha campanha = new Campanha(null, "Nome de uma campanha", "Descrição de uma campanha");
		this.repository.save(campanha);
		this.repository.delete(campanha);
		assertThat(repository.findById(campanha.getId())).isEmpty();
	}

	@Test
	@Rollback(true)
	@DisplayName("Atualizar uma Campanha e persistir os dados, retorno: id, nome e descrição")
	public void atualizar() {
		Campanha campanha = new Campanha(null, "Nome de uma campanha", "Descrição de uma campanha");
		campanha = this.repository.save(campanha);
		campanha = new Campanha(campanha.getId(), "Nome dois", "Descrição dois");
		campanha = this.repository.save(campanha);
		assertThat(campanha.getNome()).isEqualTo("Nome dois");
		assertThat(campanha.getDescricao()).isEqualTo("Descrição dois");
	}

	@Test
	@Rollback(true)
	@DisplayName("Buscar Campanha por id, retorno: id, nome e descrição")
	public void buscarPorId() {
		Campanha campanha = new Campanha(null, "Nome de uma campanha", "Descrição de uma campanha");
		campanha = this.repository.save(campanha);
		assertThat(campanha.getId()).isNotNull();
		assertThat(campanha.getNome()).isEqualTo("Nome de uma campanha");
		assertThat(campanha.getDescricao()).isEqualTo("Descrição de uma campanha");
	}

	@Test
	@Rollback(true)
	@DisplayName("Salva todas as entidades fornecidas.")
	public void salvarTodosEntidades() {
		Campanha campanha1 = new Campanha(null, "Nome1", "Descrição1");
		Campanha campanha2 = new Campanha(null, "Nome2", "Descrição2");
		List<Campanha> campanhas = new ArrayList<>();
		campanhas.add(campanha1);
		campanhas.add(campanha2);
		List<Campanha> campanhaSalvas = this.repository.saveAll(campanhas);
		assertThat(campanhaSalvas.get(0).getId()).isNotNull();
		assertThat(campanhaSalvas.get(0).getNome()).isEqualTo("Nome1");
		assertThat(campanhaSalvas.get(0).getDescricao()).isEqualTo("Descrição1");
		assertThat(campanhaSalvas.get(1).getId()).isNotNull();
		assertThat(campanhaSalvas.get(1).getNome()).isEqualTo("Nome2");
		assertThat(campanhaSalvas.get(1).getDescricao()).isEqualTo("Descrição2");
	}

	@Test
	@Rollback(true)
	@DisplayName("exclui uma determinada entidade.")
	public void deletarEntidade() {
		Campanha campanha = new Campanha(1L, "Nome1", "Descrição1");
		this.repository.delete(campanha);
		assertThat(repository.findById(campanha.getId())).isEmpty();
	}

	@Test
	@Rollback(true)
	@DisplayName("Exclui as entidades fornecidas.")
	public void deletarTodosPorEntidades() {
		List<Campanha> campanhas = Arrays.asList(new Campanha(1L, "Nome1", "Descrição1"), new Campanha(2L, "Nome2", "Descrição2"));
		this.repository.saveAll(campanhas);
		this.repository.deleteAll(campanhas);
		assertThat(repository.findById(1L)).isEmpty();
		assertThat(repository.findById(2L)).isEmpty();
	}

//	@Test
//	@DisplayName("Exclui todas as entidades gerenciadas pelo repositório.")
//	public void deletarTudo() {
//		List<Campanha> campanhas = Arrays.asList(new Campanha(1L, "Nome1", "Descrição1"), new Campanha(2L, "Nome2", "Descrição2"), new Campanha(3L, "Nome3", "Descrição3"));
//		this.repository.saveAll(campanhas);
//		this.repository.deleteAll();// TODO consertar está dando erro
//		assertThat(repository.findById(1L)).isEmpty();
//		assertThat(repository.findById(2L)).isEmpty();
//		assertThat(repository.findById(3L)).isEmpty();
//	}

//	@Test
//	@DisplayName("Retorna todas as instâncias do tipo.")
//	public void buscarTodosEntidades() {
//		List<Campanha> campanhas = Arrays.asList(new Campanha(null, "Nome1", "Descrição1"), new Campanha(null, "Nome2", "Descrição2"));
//		this.repository.saveAll(campanhas);
//		List<Campanha> campanhaBusca = this.repository.findAll(); // TODO consertar está dando erro
//		assertThat(campanhaBusca.get(0).getId()).isNotNull();
//		assertThat(campanhaBusca.get(0).getNome()).isEqualTo("Nome1");
//		assertThat(campanhaBusca.get(0).getDescricao()).isEqualTo("Descrição1");
//		assertThat(campanhaBusca.get(1).getId()).isNotNull();
//		assertThat(campanhaBusca.get(1).getNome()).isEqualTo("Nome2");
//		assertThat(campanhaBusca.get(1).getDescricao()).isEqualTo("Descrição2");
//	}

//	@Test
//	@DisplayName("Retorna se uma entidade com o ID fornecido existe.")
//	public void buscarCIds() {
//		this.repository.saveAll(Arrays.asList(new Campanha(null, "Nome1", "Descrição1"), new Campanha(null, "Nome2", "Descrição2")));
//		List<Long> ids = Arrays.asList(1L, 2L);
//		List<Campanha> campanhas = this.repository.findAllById(ids);// TODO consertar está dando erro
//		assertThat(campanhas.get(0).getId()).isNotNull();
//		assertThat(campanhas.get(0).getNome()).isEqualTo("Nome1");
//		assertThat(campanhas.get(0).getDescricao()).isEqualTo("Descrição1");
//		assertThat(campanhas.get(1).getId()).isNotNull();
//		assertThat(campanhas.get(1).getNome()).isEqualTo("Nome2");
//		assertThat(campanhas.get(1).getDescricao()).isEqualTo("Descrição2");
//	}

//	@Test
//	@DisplayName("Retorna se uma entidade com o ID fornecido existe.") // renomear
//	public void verificarSeExistePorId() {
//		Campanha campanha = this.repository.save(new Campanha(1L, "Nome", "Descrição"));
//		assertEquals(true, this.repository.existsById(campanha.getId())); // TODO consertar está dando erro
//	}
}
