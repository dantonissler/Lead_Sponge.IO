package com.leadsponge.leadsponge.IO.CampanhaTestes;

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
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.Filter.CampanhaFilter;
import com.leadsponge.IO.services.CampanhaService;
import com.leadsponge.leadsponge.IO.Util;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class)
@AutoConfigureMockMvc
@DisplayName("Testando token do EndPoint Campanha")
public class EndPoint {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@MockBean
	private CampanhaService service;

	@Mock
	private Campanha campanha;

	@Mock
	private Page<Campanha> campanhaPage;

	private MediaType contentType = new MediaType("application", "json");

	@Before
	private void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
	}

	@Test
	@DisplayName("Listar Campanha, retornar a Campanha e status 200")
	public void listarCampanhas() throws Exception {
		CampanhaFilter campanhaFilter = new CampanhaFilter();
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).filtrar(campanhaFilter, pageable);
	}

	@Test
	@DisplayName("Listar Campanha usando filtro pelo nome, retornar a Campanha e status 200")
	public void listarCampanhasNome() throws Exception {
		CampanhaFilter campanhaFilter = new CampanhaFilter("nome", null);
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/campanhas?nome=nome").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).filtrar(campanhaFilter, pageable);
	}

	@Test
	@DisplayName("Listar Campanha usando filtro pela descrição, retornar a Campanha e status 200")
	public void listarCampanhasDescricao() throws Exception {
		CampanhaFilter campanhaFilter = new CampanhaFilter(null, "descrição");
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/campanhas?descricao=descrição").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).filtrar(campanhaFilter, pageable);
	}

	@Test
	@DisplayName("Buscar Campanha usando o id, retornar a Campanha e status 200 sucesso")
	public void buscarCampanha() throws Exception {
		Campanha campanha = new Campanha(3L, "nome", "descrição");
		when(service.detalhar(3L)).thenReturn(campanha);
		mockMvc.perform(get("/campanhas/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
				.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.nome").value("nome")).andExpect(jsonPath("$.descricao").value("descrição"));
		verify(service, times(1)).detalhar(3L);
	}

	@Test
	@DisplayName("Deletar Campanha, retornar a Campanha e status 200")
	public void deletarCampanha() throws Exception {
		mockMvc.perform(delete("/campanhas/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).deletar(3L);
	}

	@Test
	@DisplayName("Criar Campanha, retornar a Campanha e status 201")
	public void criarCampanha() throws Exception {
		Campanha campanha = new Campanha(null, "campanha nome", "campanha descricao");
		when(service.salvar(campanha)).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(post("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.nome").value("campanha nome")).andExpect(jsonPath("$.descricao").value("campanha descricao"));
		verify(service, times(1)).salvar(Mockito.any(Campanha.class));
	}

	@Test
	@DisplayName("Atualizar Campanha, retornar a Campanha e status 201")
	public void atualizarCampanha() throws Exception {
		Campanha campanha = new Campanha(1L, "nome novo", "descrição nova");
		when(service.atualizar(1L, campanha)).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(put("/campanhas/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.nome").value("nome novo")).andExpect(jsonPath("$.descricao").value("descrição nova"));
		verify(service, times(1)).atualizar(1L, campanha);
	}

	// Sem permissão de acesso

	@Test
	@DisplayName("Listar Campanha sem permissão de acesso, retornar a Campanha o status 403")
	public void permissaoListarCampanhas() throws Exception {
		CampanhaFilter campanhaFilter = new CampanhaFilter();
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).filtrar(campanhaFilter, pageable);
	}

	@Test
	@DisplayName("Listar Campanha usando filtro pelo nome sem permissão de acesso, retornar o status 403")
	public void permissaoListarCampanhasNome() throws Exception {
		CampanhaFilter campanhaFilter = new CampanhaFilter("nome", null);
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/campanhas?nome=nome").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).filtrar(campanhaFilter, pageable);
	}

	@Test
	@DisplayName("Listar Campanha usando filtro pela descrição sem permissão de acesso, retornar o status 403")
	public void permissaoListarCampanhasDescricao() throws Exception {
		CampanhaFilter campanhaFilter = new CampanhaFilter(null, "descrição");
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/campanhas?descricao=descrição").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).filtrar(campanhaFilter, pageable);
	}

	@Test
	@DisplayName("Buscar Campanha usando o id sem permissão de acesso, retornar o status 403")
	public void permissaoBuscarCampanha() throws Exception {
		Campanha campanha = new Campanha(3L, "nome", "descrição");
		when(service.detalhar(3L)).thenReturn(campanha);
		mockMvc.perform(get("/campanhas/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
				.andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).detalhar(3L);
	}

	@Test
	@DisplayName("Deletar Campanha sem permissão de acesso, retornar a Campanha e status 403")
	public void permissaoDeletarCampanha() throws Exception {
		mockMvc.perform(delete("/campanhas/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).deletar(3L);
	}

	@Test
	@DisplayName("Criar Campanha sem permissão de acesso, retornar o status 403")
	public void permissaoCriarCampanha() throws Exception {
		Campanha campanha = new Campanha(null, "campanha nome", "campanha descricao");
		when(service.salvar(campanha)).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(post("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).salvar(Mockito.any(Campanha.class));
	}

	@Test
	@DisplayName("Atualizar Campanha sem permissão de acesso, retornar o status 403")
	public void permissaoAtualizarCampanha() throws Exception {
		Campanha campanha = new Campanha(1L, "nome novo", "descrição nova");
		when(service.atualizar(1L, campanha)).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(put("/campanhas/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isForbidden()).andDo(print());
		verify(service, times(0)).atualizar(1L, campanha);
	}

}
