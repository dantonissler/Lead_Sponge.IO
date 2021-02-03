package com.leadsponge.leadsponge.IO.clienteTestes;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.repository.Filter.ClienteFilter;
import com.leadsponge.IO.services.ClienteService;
import com.leadsponge.leadsponge.IO.Util;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class)
@AutoConfigureMockMvc
@DisplayName("Testando token do EndPoint Cliente")
public class EndPoint {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@MockBean
	private ClienteService service;

	@Mock
	private Cliente cliente;

	@Mock
	private Page<Cliente> campanhaPage;

	private MediaType contentType = new MediaType("application", "json");

	@Before
	private void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
	}

	@Test
	@DisplayName("Listar Clientes, retornar a Clientes e status 200")
	public void listarClientes() throws Exception {
		ClienteFilter clienteFilter = new ClienteFilter();
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(clienteFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/clientes").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).filtrar(clienteFilter, pageable);
	}

	@Test
	@DisplayName("Listar Clientes usando filtro pelo nome, retornar a Cliente e status 200")
	public void listarClientesNome() throws Exception {
		ClienteFilter clienteFilter = new ClienteFilter("nome", null, null);
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(clienteFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/clientes?nome=nome").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).filtrar(clienteFilter, pageable);
	}

	@Test
	@DisplayName("Listar Clientes usando filtro pela resumo, retornar a Cliente e status 200")
	public void listarClientesResumo() throws Exception {
		ClienteFilter clienteFilter = new ClienteFilter(null, null, "resumo");
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(clienteFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/clientes?resumo=resumo").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).filtrar(clienteFilter, pageable);
	}

	@Test
	@DisplayName("Listar Clientes usando filtro pela url, retornar a Cliente e status 200")
	public void listarClientesUrl() throws Exception {
		ClienteFilter clienteFilter = new ClienteFilter(null, "url", null);
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(clienteFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/clientes?url=url").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).filtrar(clienteFilter, pageable);
	}

	@Test
	@DisplayName("Buscar Cliente usando o id, retornar a Cliente e status 200 sucesso")
	public void buscarCliente() throws Exception {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		when(service.detalhar(3L)).thenReturn(cliente);
		mockMvc.perform(get("/clientes/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
				.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.nome").value("nome")).andExpect(jsonPath("$.resumo").value("resumo"));
		verify(service, times(1)).detalhar(3L);
	}

	@Test
	@DisplayName("Deletar Cliente, retornar a Cliente e status 200")
	public void deletarCliente() throws Exception {
		mockMvc.perform(delete("/clientes/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).deletar(3L);
	}

	@Test
	@DisplayName("Criar Cliente, retornar a Cliente e status 201")
	public void criarCliente() throws Exception {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		when(service.salvar(cliente)).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(post("/clientes").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.nome").value("nome")).andExpect(jsonPath("$.resumo").value("resumo"));
		verify(service, times(1)).salvar(Mockito.any(Cliente.class));
	}

	@Test
	@DisplayName("Atualizar Cliente, retornar a Cliente e status 201")
	public void atualizarCliente() throws Exception {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		when(service.atualizar(1L, cliente)).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(put("/clientes/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.nome").value("nome")).andExpect(jsonPath("$.resumo").value("resumo"));
		verify(service, times(1)).atualizar(1L, cliente);
	}

	// Permissão de acesso

	@Test
	@DisplayName("Listar Clientes sem permissão de acesso, retornar o status 403")
	public void permissaoListarClientes() throws Exception {
		ClienteFilter clienteFilter = new ClienteFilter();
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(clienteFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/clientes").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).filtrar(clienteFilter, pageable);
	}

	@Test
	@DisplayName("Listar Clientes usando filtro pelo nome sem permissão de acesso, retornar o status 403")
	public void permissaoListarClientesNome() throws Exception {
		ClienteFilter campanhaFilter = new ClienteFilter("nome", null, null);
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/clientes?nome=nome").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).filtrar(campanhaFilter, pageable);
	}

	@Test
	@DisplayName("Listar Clientes usando filtro pela resumo sem permissão de acesso, retornar o status 403")
	public void permissaoListarClientesResumo() throws Exception {
		ClienteFilter campanhaFilter = new ClienteFilter(null, null, "resumo");
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/clientes?resumo=resumo").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).filtrar(campanhaFilter, pageable);
	}

	@Test
	@DisplayName("Listar Clientes usando filtro pela url sem permissão de acesso, retornar o status 403")
	public void permissaoListarClientesUrl() throws Exception {
		ClienteFilter campanhaFilter = new ClienteFilter(null, "url", null);
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/clientes?resumo=resumo").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).filtrar(campanhaFilter, pageable);
	}

	@Test
	@DisplayName("Buscar Cliente usando o id sem permissão de acesso, retornar o status 403")
	public void permissaoBuscarCliente() throws Exception {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		when(service.detalhar(3L)).thenReturn(cliente);
		mockMvc.perform(get("/clientes/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
				.andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).detalhar(3L);
	}

	@Test
	@DisplayName("Deletar Cliente sem permissão de acesso, retornar a Cliente e status 403")
	public void permissaoDeletarCliente() throws Exception {
		mockMvc.perform(delete("/clientes/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).deletar(3L);
	}

	@Test
	@DisplayName("Criar Cliente sem permissão de acesso, retornar o status 403")
	public void permissaoCriarCliente() throws Exception {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		when(service.salvar(cliente)).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(post("/clientes").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).salvar(Mockito.any(Cliente.class));
	}

	@Test
	@DisplayName("Atualizar Cliente sem permissão de acesso, retornar o status 403")
	public void permissaoAtualizarCliente() throws Exception {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		when(service.atualizar(1L, cliente)).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(put("/clientes/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).atualizar(1L, cliente);
	}

	// testando o Token de acesso

	@Test
	@DisplayName("Listar Clientes com usuario e senha incorretos, retornar status 401")
	public void listamosClientesTokenIncorreto() throws Exception {
		ClienteFilter clienteFilter = new ClienteFilter();
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(clienteFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/clientes").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
				.andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
		verify(service, times(0)).filtrar(clienteFilter, pageable);
	}

	@Test
	@DisplayName("Buscar Cliente usando usuario e senha incorretos, retornar status 401")
	public void buscarClientesTokenIncorreto() throws Exception {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		when(service.detalhar(3L)).thenReturn(cliente);
		mockMvc.perform(get("/clientes/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
				.andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
		verify(service, times(0)).detalhar(3L);
	}

	@Test
	@DisplayName("Deletar Cliente com usuario e senha incorretos, retornar status 401")
	public void deletarClientesTokenIncorreto() throws Exception {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		when(service.deletar(3L)).thenReturn(cliente);
		mockMvc.perform(delete("/clientes/1").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
				.andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
		verify(service, times(0)).deletar(1L);
	}

	@Test
	@DisplayName("Criar Cliente com usuario e senha incorretos, retornar status 401")
	public void criarClientesTokenIncorreto() throws Exception {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		when(service.salvar(cliente)).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(post("/clientes").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
		verify(service, times(0)).atualizar(1L, cliente);
	}

	@Test
	@DisplayName("Atualizar Cliente com usuario e senha incorretos, retornar status 401")
	public void atualizarClientesTokenIncorreto() throws Exception {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		when(service.atualizar(1L, cliente)).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(put("/clientes/1").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
		verify(service, times(0)).atualizar(1L, cliente);
	}

	@Test
	@DisplayName("Listar Clientes sem token, retornar status 401")
	public void listamosSemToken() throws Exception {
		ClienteFilter clienteFilter = new ClienteFilter();
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(clienteFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/clientes")).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
		verify(service, times(0)).filtrar(clienteFilter, pageable);
	}

	@Test
	@DisplayName("Buscar Clientes sem token, retornar status 401")
	public void buscarSemToken() throws Exception {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		when(service.detalhar(3L)).thenReturn(cliente);
		mockMvc.perform(get("/clientes/{id}", 3L)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
		verify(service, times(0)).detalhar(3L);
	}

	@Test
	@DisplayName("Deletar Cliente sem token, retornar status 401")
	public void deletarSemToken() throws Exception {
		when(service.deletar(3L)).thenReturn(cliente);
		mockMvc.perform(delete("/clientes/1")).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
		verify(service, times(0)).deletar(1L);
	}

	@Test
	@DisplayName("Criar Cliente sem token, retornar status 401")
	public void criarSemToken() throws Exception {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		when(service.salvar(cliente)).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(post("/clientes").accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print())
				.andExpect(jsonPath("$.error").value("unauthorized"));
		verify(service, times(0)).salvar(cliente);
	}

	@Test
	@DisplayName("Atualizar Cliente sem token, retornar status 401")
	public void atualizarSemToken() throws Exception {
		Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
		when(service.atualizar(1L, cliente)).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(put("/clientes/1").accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print())
				.andExpect(jsonPath("$.error").value("unauthorized"));
		verify(service, times(0)).atualizar(1L, cliente);
	}

	// validar a entidade ate aqui

	@Test
	@DisplayName("Criar Cliente informando nome e descricao, retornar as informações enviadas e Status 201")
	public void criarClienteSemInformarUmaDescricao() throws Exception {
		Cliente cliente = new Cliente(null, "nome", "url", "", null, null, null, null, null);
		when(service.salvar(Mockito.any(Cliente.class))).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(post("/clientes").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print());
		verify(service, times(1)).salvar(cliente);
	}

	@Test
	@DisplayName("Atualizar Cliente sem informar uma descricao, retornar as informações e status 201")
	public void atualizarClienteSemInformarUmaDescricao() throws Exception {
		Cliente clienteNovo = new Cliente(null, "nome", "url", "", null, null, null, null, null);
		when(service.atualizar(1L, clienteNovo)).thenReturn(clienteNovo);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(clienteNovo);
		mockMvc.perform(put("/clientes/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.nome").value("nome")).andExpect(jsonPath("$.resumo").value(""));
		verify(service, times(1)).atualizar(1L, clienteNovo);
	}

	@Test
	@DisplayName("Criar Cliente informando um nome null, retornar mensagem de erro e status 400.")
	public void criarNomeNull() throws Exception {
		Cliente cliente = new Cliente(null, null, "url", "resumo", null, null, null, null, null);
		when(service.salvar(Mockito.any(Cliente.class))).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(post("/clientes").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome")).andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null"))
				.andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).salvar(cliente);
	}

	@Test
	@DisplayName("Atualizar Cliente informando um nome null, retornar mensagem de erro e status 400.")
	public void atualizarNomeNull() throws Exception {
		Cliente cliente = new Cliente(null, null, "url", "resumo", null, null, null, null, null);
		when(service.atualizar(1L, cliente)).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(put("/clientes/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome")).andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null"))
				.andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).atualizar(1L, cliente);
	}

	@Test
	@DisplayName("Criar Cliente informando um nome vazio, retornar mensagem de erro e status 400.")
	public void criarNomeVazio() throws Exception {
		Cliente cliente = new Cliente(null, "", "url", "resumo", null, null, null, null, null);
		when(service.salvar(Mockito.any(Cliente.class))).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(post("/clientes").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).salvar(cliente);
	}

	@Test
	@DisplayName("Atualizar Cliente informando um nome vazio, retornar mensagem de erro e status 400.")
	public void atualizarNomeVazio() throws Exception {
		Cliente cliente = new Cliente(null, "", "url", "resumo", null, null, null, null, null);
		when(service.atualizar(1L, cliente)).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(put("/clientes/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).atualizar(1L, cliente);
	}

	@Test
	@DisplayName("Criar Cliente informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
	public void criarNomeAcima50Caracteres() throws Exception {
		Cliente cliente = new Cliente(null, "Nome campanha, nome Cliente, nome cliente e nomes nome", "url", "resumo", null, null, null, null, null);
		when(service.salvar(Mockito.any(Cliente.class))).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(post("/clientes").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).salvar(cliente);
	}

	@Test
	@DisplayName("Atualizar Cliente informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
	public void atualizarNomeAcima50Caracteres() throws Exception {
		Cliente cliente = new Cliente(null, "Nome campanha, nome Cliente, nome cliente e nomes nome", "url", "resumo", null, null, null, null, null);
		when(service.atualizar(1L, cliente)).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(put("/clientes/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).atualizar(1L, cliente);
	}

	@Test
	@DisplayName("Criar Cliente informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
	public void criarNomeAbaixo4Caracteres() throws Exception {
		Cliente cliente = new Cliente(null, "Nom", "url", "resumo", null, null, null, null, null);
		when(service.salvar(Mockito.any(Cliente.class))).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(post("/clientes").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).salvar(cliente);
	}

	@Test
	@DisplayName("Atualizar Cliente informando um nome abaixo 4 caracteres, retornar mensagem de erro e status 400.")
	public void atualizarNomeAbaixo4Caracteres() throws Exception {
		Cliente cliente = new Cliente(null, "Nom", "url", "resumo", null, null, null, null, null);
		when(service.atualizar(1L, cliente)).thenReturn(cliente);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);
		mockMvc.perform(put("/clientes/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).atualizar(1L, cliente);
	}
}
