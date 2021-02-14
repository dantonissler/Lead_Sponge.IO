package com.leadsponge.leadsponge.IO.contatoTestes;

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
import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.repository.Filter.ContatoFilter;
import com.leadsponge.IO.services.ContatoService;
import com.leadsponge.leadsponge.IO.Util;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class)
@AutoConfigureMockMvc
@DisplayName("Testando token do EndPoint Contato")
public class EndPoint {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@MockBean
	private ContatoService service;

	@Mock
	private Contato contato;

	@Mock
	private Page<Contato> page;

	private final MediaType contentType = new MediaType("application", "json");

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
	}

	@Test
	@DisplayName("Listar Contatos, retornar a Contatos e status 200")
	public void listar() throws Exception {
		ContatoFilter contatoFilter = new ContatoFilter();
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(contatoFilter, pageable)).thenReturn(page);
		mockMvc.perform(get("/contatos").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).filtrar(contatoFilter, pageable);
	}

	@Test
	@DisplayName("Listar Contatos usando filtro pelo nome, retornar a Contatos e status 200")
	public void listarNome() throws Exception {
		ContatoFilter contatoFilter = new ContatoFilter("nome", null);
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(contatoFilter, pageable)).thenReturn(page);
		mockMvc.perform(get("/contatos?nome=nome").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).filtrar(contatoFilter, pageable);
	}

	@Test
	@DisplayName("Listar Contatos usando filtro pela cargo, retornar a Contatos e status 200")
	public void listarCargo() throws Exception {
		ContatoFilter contatoFilter = new ContatoFilter(null, "cargo");
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(contatoFilter, pageable)).thenReturn(page);
		mockMvc.perform(get("/contatos?cargo=cargo").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).filtrar(contatoFilter, pageable);
	}

	@Test
	@DisplayName("Buscar Contatos usando o id, retornar a Contatos e status 200 sucesso")
	public void buscar() throws Exception {
		Contato contato = new Contato(3L, "nome", "cargo", null, null, null);
		when(service.detalhar(3L)).thenReturn(contato);
		mockMvc.perform(get("/contatos/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
				.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.nome").value("nome")).andExpect(jsonPath("$.cargo").value("cargo"));
		verify(service, times(1)).detalhar(3L);
	}

	@Test
	@DisplayName("Deletar Contatos, retornar a Contato e status 200")
	public void deletar() throws Exception {
		mockMvc.perform(delete("/contatos/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).deletar(3L);
	}

	@Test
	@DisplayName("Criar Contatos, retornar a Contatos e status 201")
	public void criar() throws Exception {
		Contato contato = new Contato(3L, "nome", "cargo", null, null, null);
		when(service.salvar(contato)).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(post("/contatos").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.nome").value("nome")).andExpect(jsonPath("$.cargo").value("cargo"));
		verify(service, times(1)).salvar(Mockito.any(Contato.class));
	}

	@Test
	@DisplayName("Atualizar Contatos, retornar a Contatos e status 201")
	public void atualizar() throws Exception {
		Contato contato = new Contato(3L, "nome", "cargo", null, null, null);
		when(service.atualizar(1L, contato)).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(put("/contatos/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.nome").value("nome")).andExpect(jsonPath("$.cargo").value("cargo"));
		verify(service, times(1)).atualizar(1L, contato);
	}

	// Permissão de acesso

	@Test
	@DisplayName("Listar Contatos sem permissão de acesso, retornar o status 403")
	public void permissaoListar() throws Exception {
		ContatoFilter contatoFilter = new ContatoFilter();
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(contatoFilter, pageable)).thenReturn(page);
		mockMvc.perform(get("/contatos").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).filtrar(contatoFilter, pageable);
	}

	@Test
	@DisplayName("Listar Contatos usando filtro pelo nome sem permissão de acesso, retornar o status 403")
	public void permissaoListarsNome() throws Exception {
		ContatoFilter contatoFilter = new ContatoFilter("nome", null);
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(contatoFilter, pageable)).thenReturn(page);
		mockMvc.perform(get("/contatos?nome=nome").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).filtrar(contatoFilter, pageable);
	}

	@Test
	@DisplayName("Listar Contatos usando filtro pela cargo sem permissão de acesso, retornar o status 403")
	public void permissaoListarCargo() throws Exception {
		ContatoFilter campanhaFilter = new ContatoFilter(null, "cargo");
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(campanhaFilter, pageable)).thenReturn(page);
		mockMvc.perform(get("/contatos?cargo=cargo").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).filtrar(campanhaFilter, pageable);
	}

	@Test
	@DisplayName("Buscar Contatos usando o id sem permissão de acesso, retornar o status 403")
	public void permissaoBuscar() throws Exception {
		Contato contato = new Contato(3L, "nome", "cargo", null, null, null);
		when(service.detalhar(3L)).thenReturn(contato);
		mockMvc.perform(get("/contatos/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
				.andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).detalhar(3L);
	}

	@Test
	@DisplayName("Deletar Contato sem permissão de acesso, retornar a Contato e status 403")
	public void permissaoDeletar() throws Exception {
		mockMvc.perform(delete("/contatos/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).deletar(3L);
	}

	@Test
	@DisplayName("Criar Contato sem permissão de acesso, retornar o status 403")
	public void permissaoCriar() throws Exception {
		Contato contato = new Contato(1L, "nome", "cargo", null, null, null);
		when(service.salvar(contato)).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(post("/contatos").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).salvar(Mockito.any(Contato.class));
	}

	@Test
	@DisplayName("Atualizar Contato sem permissão de acesso, retornar o status 403")
	public void permissaoAtualizar() throws Exception {
		Contato contato = new Contato(1L, "nome", "cargo", null, null, null);
		when(service.atualizar(1L, contato)).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(put("/contatos/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).atualizar(1L, contato);
	}

	// testando o Token de acesso

	@Test
	@DisplayName("Listar Contato com usuario e senha incorretos, retornar status 401")
	public void listamosTokenIncorreto() throws Exception {
		ContatoFilter contatoFilter = new ContatoFilter();
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(contatoFilter, pageable)).thenReturn(page);
		mockMvc.perform(get("/contatos").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
				.andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
		verify(service, times(0)).filtrar(contatoFilter, pageable);
	}

	@Test
	@DisplayName("Buscar Contato usando usuario e senha incorretos, retornar status 401")
	public void buscarTokenIncorreto() throws Exception {
		Contato contato = new Contato(1L, "nome", "cargo", null, null, null);
		when(service.detalhar(3L)).thenReturn(contato);
		mockMvc.perform(get("/contatos/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
				.andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
		verify(service, times(0)).detalhar(3L);
	}

	@Test
	@DisplayName("Deletar Contato com usuario e senha incorretos, retornar status 401")
	public void deletarTokenIncorreto() throws Exception {
		Contato contato = new Contato(1L, "nome", "cargo", null, null, null);
		when(service.deletar(3L)).thenReturn(contato);
		mockMvc.perform(delete("/contatos/1").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
				.andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
		verify(service, times(0)).deletar(1L);
	}

	@Test
	@DisplayName("Criar Contato com usuario e senha incorretos, retornar status 401")
	public void criarTokenIncorreto() throws Exception {
		Contato contato = new Contato(1L, "nome", "cargo", null, null, null);
		when(service.salvar(contato)).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(post("/contatos").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
		verify(service, times(0)).atualizar(1L, contato);
	}

	@Test
	@DisplayName("Atualizar Contato com usuario e senha incorretos, retornar status 401")
	public void atualizarTokenIncorreto() throws Exception {
		Contato contato = new Contato(1L, "nome", "cargo", null, null, null);
		when(service.atualizar(1L, contato)).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(put("/contatos/1").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
		verify(service, times(0)).atualizar(1L, contato);
	}

	@Test
	@DisplayName("Listar Contatos sem token, retornar status 401")
	public void listamosSemToken() throws Exception {
		ContatoFilter contatoFilter = new ContatoFilter();
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(contatoFilter, pageable)).thenReturn(page);
		mockMvc.perform(get("/contatos")).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
		verify(service, times(0)).filtrar(contatoFilter, pageable);
	}

	@Test
	@DisplayName("Buscar Contatos sem token, retornar status 401")
	public void buscarSemToken() throws Exception {
		Contato contato = new Contato(1L, "nome", "cargo", null, null, null);
		when(service.detalhar(3L)).thenReturn(contato);
		mockMvc.perform(get("/contatos/{id}", 3L)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
		verify(service, times(0)).detalhar(3L);
	}

	@Test
	@DisplayName("Deletar Contato sem token, retornar status 401")
	public void deletarSemToken() throws Exception {
		when(service.deletar(3L)).thenReturn(contato);
		mockMvc.perform(delete("/contatos/1")).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
		verify(service, times(0)).deletar(1L);
	}

	@Test
	@DisplayName("Criar Contato sem token, retornar status 401")
	public void criarSemToken() throws Exception {
		Contato contato = new Contato(1L, "nome", "cargo", null, null, null);
		when(service.salvar(contato)).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(post("/contatos").accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print())
				.andExpect(jsonPath("$.error").value("unauthorized"));
		verify(service, times(0)).salvar(contato);
	}

	@Test
	@DisplayName("Atualizar Contato sem token, retornar status 401")
	public void atualizarSemToken() throws Exception {
		Contato contato = new Contato(1L, "nome", "cargo", null, null, null);
		when(service.atualizar(1L, contato)).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(put("/contatos/1").accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print())
				.andExpect(jsonPath("$.error").value("unauthorized"));
		verify(service, times(0)).atualizar(1L, contato);
	}

	// validar a entidade ate aqui

	@Test
	@DisplayName("Criar Contato informando nome e descricao, retornar as informações enviadas e Status 201")
	public void criarSemInformarUmaDescricao() throws Exception {
		Contato contato = new Contato(1L, "nome", "cargo", null, null, null);
		when(service.salvar(Mockito.any(Contato.class))).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(post("/contatos").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print());
		verify(service, times(1)).salvar(contato);
	}

	@Test
	@DisplayName("Atualizar Contato sem informar uma descricao, retornar as informações e status 201")
	public void atualizarSemInformarUmaDescricao() throws Exception {
		Contato contatoNovo = new Contato(1L, "nome", "", null, null, null);
		when(service.atualizar(1L, contatoNovo)).thenReturn(contatoNovo);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contatoNovo);
		mockMvc.perform(put("/contatos/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.nome").value("nome")).andExpect(jsonPath("$.cargo").value(""));
		verify(service, times(1)).atualizar(1L, contatoNovo);
	}

	@Test
	@DisplayName("Criar Contato informando um nome null, retornar mensagem de erro e status 400.")
	public void criarNomeNull() throws Exception {
		Contato contato = new Contato(1L, null, "cargo", null, null, null);
		when(service.salvar(Mockito.any(Contato.class))).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(post("/contatos").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome")).andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null"))
				.andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).salvar(contato);
	}

	@Test
	@DisplayName("Atualizar Contato informando um nome null, retornar mensagem de erro e status 400.")
	public void atualizarNomeNull() throws Exception {
		Contato contato = new Contato(1L, null, "cargo", null, null, null);
		when(service.atualizar(1L, contato)).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(put("/contatos/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome")).andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null"))
				.andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).atualizar(1L, contato);
	}

	@Test
	@DisplayName("Criar Contato informando um nome vazio, retornar mensagem de erro e status 400.")
	public void criarNomeVazio() throws Exception {
		Contato contato = new Contato(1L, "", "cargo", null, null, null);
		when(service.salvar(Mockito.any(Contato.class))).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(post("/contatos").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).salvar(contato);
	}

	@Test
	@DisplayName("Atualizar Contato informando um nome vazio, retornar mensagem de erro e status 400.")
	public void atualizarNomeVazio() throws Exception {
		Contato contato = new Contato(1L, "", "cargo", null, null, null);
		when(service.atualizar(1L, contato)).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(put("/contatos/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).atualizar(1L, contato);
	}

	@Test
	@DisplayName("Criar Contato informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
	public void criarNomeAcima50Caracteres() throws Exception {
		Contato contato = new Contato(null, "nome nome nome nome nome nome nome nome nome nome nome nome", "cargo", null, null, null);
		when(service.salvar(Mockito.any(Contato.class))).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(post("/contatos").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).salvar(contato);
	}

	@Test
	@DisplayName("Atualizar Contato informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
	public void atualizarNomeAcima50Caracteres() throws Exception {
		Contato contato = new Contato(null, "nome nome nome nome nome nome nome nome nome nome nome nome", "cargo", null, null, null);
		when(service.atualizar(1L, contato)).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(put("/contatos/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).atualizar(1L, contato);
	}

	@Test
	@DisplayName("Criar Contato informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
	public void criarNomeAbaixo4Caracteres() throws Exception {
		Contato contato = new Contato(null, "nom", "cargo", null, null, null);
		when(service.salvar(Mockito.any(Contato.class))).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(post("/contatos").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).salvar(contato);
	}

	@Test
	@DisplayName("Atualizar Contato informando um nome abaixo 4 caracteres, retornar mensagem de erro e status 400.")
	public void atualizarNomeAbaixo4Caracteres() throws Exception {
		Contato contato = new Contato(null, "nom", "cargo", null, null, null);
		when(service.atualizar(1L, contato)).thenReturn(contato);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contato);
		mockMvc.perform(put("/contatos/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
		verify(service, times(0)).atualizar(1L, contato);
	}

}
