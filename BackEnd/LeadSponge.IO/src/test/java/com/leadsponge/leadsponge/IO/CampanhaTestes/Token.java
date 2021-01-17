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

import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.Filter.CampanhaFilter;
import com.leadsponge.IO.services.CampanhaService;
import com.leadsponge.leadsponge.IO.Util;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class)
@AutoConfigureMockMvc
@DisplayName("Testando token do EndPoint Campanha")
public class Token {

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
	@DisplayName("Listar Campanha usando token valido e filtrando pelo nome e descrição, retornar status 200")
	public void listarCampanhasNomeDescricao() throws Exception {
		CampanhaFilter campanhaFilter = new CampanhaFilter("nome", "descrição");
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/campanhas?nome=nome&descricao=descrição").header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).filtrar(campanhaFilter, pageable);
	}

	@Test
	@DisplayName("Listar Campanha usando token valido e filtrando pelo nome, retornar status 401")
	public void listarCampanhasNomeIncorreto() throws Exception {
		CampanhaFilter campanhaFilter = new CampanhaFilter("nome", null);
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/campanhas?nome=nome").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized()).andDo(print())
				.andExpect(jsonPath("$.error").value("invalid_token"));
	}

	@Test
	@DisplayName("Listar Campanha usando token valido e filtrando pela descrição, retornar status 401")
	public void listarCampanhasDescricaoIncorreto() throws Exception {
		CampanhaFilter campanhaFilter = new CampanhaFilter(null, "descrição");
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/campanhas?descricao=descrição").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized()).andDo(print())
				.andExpect(jsonPath("$.error").value("invalid_token"));
	}

	@Test
	@DisplayName("Listar Campanha usando token valido e filtrando pelo nome e descrição, retornar status 401")
	public void listarCampanhasNomeDescricaoIncorreto() throws Exception {
		CampanhaFilter campanhaFilter = new CampanhaFilter("nome", "descrição");
		Pageable pageable = PageRequest.of(0, 10);
		when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
		mockMvc.perform(get("/campanhas?nome=nome&descricao=descrição").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized()).andDo(print())
				.andExpect(jsonPath("$.error").value("invalid_token"));
	}

	@Test
	@DisplayName("Listar Campanhas token invalido, retornar status 401")
	public void listamosCampanhasIncorreto() throws Exception {
		mockMvc.perform(get("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
				.andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
	}

	@Test
	@DisplayName("Buscar Campanha usando usuario e senha incorretos, retornar status 401")
	public void buscarCampanhasIncorreto() throws Exception {
		Campanha campanha = new Campanha(3L, "campanha nome", "campanha descrição");
		when(service.detalhar(3L)).thenReturn(campanha);
		mockMvc.perform(get("/campanhas/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
				.andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
	}

	@Test
	@DisplayName("Deletar Campanha token invalido, retornar status 401")
	public void deletarCampanhasIncorreto() throws Exception {
		mockMvc.perform(delete("/campanhas/1").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
				.andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
	}

	@Test
	@DisplayName("Criar Campanha token invalido, retornar status 401")
	public void criarCampanhasIncorreto() throws Exception {
		mockMvc.perform(post("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
				.andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
	}

	@Test
	@DisplayName("Atualizar Campanha token invalido, retornar status 401")
	public void atualizarCampanhasIncorreto() throws Exception {
		mockMvc.perform(put("/campanhas/1").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
				.andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
	}
}
