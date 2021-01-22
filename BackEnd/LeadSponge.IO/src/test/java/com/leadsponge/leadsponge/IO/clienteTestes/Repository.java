package com.leadsponge.leadsponge.IO.clienteTestes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.repository.cliente.ClienteRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Testando o repositorio da Cliente")
public class Repository {

	@Autowired
	private ClienteRepository repository;

	@AfterEach
	private void setUp() {
		repository.deleteAll();
	}

	@Test
	@Rollback(true)
	@DisplayName("Criar Cliente e persistir os dados, retornar: id, nome e descrição")
	public void criar() {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		cliente = this.repository.save(cliente);
		Cliente clienteNovo = repository.findById(cliente.getId()).orElse(null);
		assertThat(clienteNovo.getId()).isNotNull();
		assertEquals("nome", clienteNovo.getNome());
		assertEquals("url", clienteNovo.getUrl());
		assertEquals("resumo", clienteNovo.getResumo());
	}

	@Test
	@Rollback(true)
	@DisplayName("Deletar uma CLiente")
	public void deletar() {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		this.repository.save(cliente);
		this.repository.delete(cliente);
		assertThat(repository.findById(cliente.getId())).isEmpty();
	}

	@Test
	@Rollback(true)
	@DisplayName("Atualizar uma CLiente e persistir os dados, retorno: id, nome e descrição")
	public void atualizar() {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		cliente = this.repository.save(cliente);
		Cliente clienteAtualizado = new Cliente(3L, "nomeNovo", "urlNovo", "resumoNovo", null, null, null, null, null);
		cliente = this.repository.save(clienteAtualizado);
		assertThat(clienteAtualizado.getNome()).isEqualTo("nomeNovo");
		assertThat(clienteAtualizado.getUrl()).isEqualTo("urlNovo");
		assertThat(clienteAtualizado.getResumo()).isEqualTo("resumoNovo");
	}

	@Test
	@Rollback(true)
	@DisplayName("Buscar CLiente por id, retorno: id, nome e descrição")
	public void buscarPorId() {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		cliente = this.repository.save(cliente);
		assertThat(cliente.getId()).isNotNull();
		assertThat(cliente.getNome()).isEqualTo("nome");
		assertThat(cliente.getUrl()).isEqualTo("url");
		assertThat(cliente.getResumo()).isEqualTo("resumo");
	}

	@Test
	@Rollback(true)
	@DisplayName("Salva todas as entidades fornecidas.")
	public void salvarTodosEntidades() {
		List<Cliente> clientes = Arrays.asList(new Cliente(null, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(null, "nome2", "url2", "resumo2", null, null, null, null, null));
		List<Cliente> clienteSalvas = this.repository.saveAll(clientes);
		assertThat(clienteSalvas.get(0).getId()).isNotNull();
		assertThat(clienteSalvas.get(0).getNome()).isEqualTo("nome1");
		assertThat(clienteSalvas.get(0).getUrl()).isEqualTo("url1");
		assertThat(clienteSalvas.get(0).getResumo()).isEqualTo("resumo1");
		assertThat(clienteSalvas.get(1).getId()).isNotNull();
		assertThat(clienteSalvas.get(1).getNome()).isEqualTo("nome2");
		assertThat(clienteSalvas.get(1).getUrl()).isEqualTo("url2");
		assertThat(clienteSalvas.get(1).getResumo()).isEqualTo("resumo2");
	}

	@Test
	@Rollback(true)
	@DisplayName("exclui uma determinada entidade.")
	public void deletarEntidade() {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		this.repository.delete(cliente);
		assertThat(repository.findById(cliente.getId())).isEmpty();
	}

	@Test
	@Rollback(true)
	@DisplayName("Exclui as entidades fornecidas.")
	public void deletarTodosPorEntidades() {
		List<Cliente> clientes = Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome2", "url2", "resumo2", null, null, null, null, null));
		this.repository.saveAll(clientes);
		this.repository.deleteAll(clientes);
		assertThat(repository.findById(1L)).isEmpty();
		assertThat(repository.findById(2L)).isEmpty();
	}

}
