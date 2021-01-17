package com.leadsponge.leadsponge.IO.CampanhaTestes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.services.CampanhaService;
import com.leadsponge.leadsponge.IO.Util;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Testando as validações da entidade do EndPoint Campanha")
public class ValidateEntity {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@MockBean
	private CampanhaService service;

	@Before
	private void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
	}

	@Test
	@DisplayName("Criar Campanha informando nome e descricao, retornar as informações enviadas e Status 201")
	public void criarCampanhaSemInformarUmaDescricao() throws Exception {
		Campanha campanha = new Campanha(null, "campanha nome", "");
		when(service.salvar(Mockito.any(Campanha.class))).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(post("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print());
	}

	@Test
	@DisplayName("Atualizar Campanha sem informar uma descricao, retornar as informações e status 201")
	public void atualizarCampanhaSemInformarUmaDescricao() throws Exception {
		Campanha campanhaNovo = new Campanha(1L, "campanha nome", "");
		when(service.atualizar(1L, campanhaNovo)).thenReturn(campanhaNovo);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanhaNovo);
		mockMvc.perform(put("/campanhas/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.nome").value("campanha nome")).andExpect(jsonPath("$.descricao").value(""));
		verify(service, times(1)).atualizar(1L, campanhaNovo);
		assertThat(campanhaNovo.getId()).isNotNull();
	}

	@Test
	@DisplayName("Criar Campanha informando um nome null, retornar mensagem de erro e status 400.")
	public void criarNomeNull() throws Exception {
		Campanha campanha = new Campanha(null, null, "campanha descricao");
		when(service.salvar(Mockito.any(Campanha.class))).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(post("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome")).andExpect(jsonPath("$.fieldMessage").value("Não pode ser null"))
				.andExpect(jsonPath("$.field").value("nome"));
	}

	@Test
	@DisplayName("Atualizar Campanha informando um nome null, retornar mensagem de erro e status 400.")
	public void atualizarNomeNull() throws Exception {
		Campanha campanha = new Campanha(1L, null, "campanha descrição");
		when(service.atualizar(1L, campanha)).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(put("/campanhas/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome")).andExpect(jsonPath("$.fieldMessage").value("Não pode ser null"))
				.andExpect(jsonPath("$.field").value("nome"));
	}

	@Test
	@DisplayName("Criar Campanha informando um nome vazio, retornar mensagem de erro e status 400.")
	public void criarNomeVazio() throws Exception {
		Campanha campanha = new Campanha(null, "", "campanha descricao");
		when(service.salvar(Mockito.any(Campanha.class))).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(post("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
	}

	@Test
	@DisplayName("Atualizar Campanha informando um nome vazio, retornar mensagem de erro e status 400.")
	public void atualizarNomeVazio() throws Exception {
		Campanha campanha = new Campanha(1L, "", "campanha descrição");
		when(service.atualizar(1L, campanha)).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(put("/campanhas/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
	}

	@Test
	@DisplayName("Criar Campanha informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
	public void criarNomeAcima50Caracteres() throws Exception {
		Campanha campanha = new Campanha(null, "Nome campanha, nome campanha, nome campanha e nomes", "campanha descricao");
		when(service.salvar(Mockito.any(Campanha.class))).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(post("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
	}

	@Test
	@DisplayName("Atualizar Campanha informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
	public void atualizarNomeAcima50Caracteres() throws Exception {
		Campanha campanha = new Campanha(1L, "Nome campanha, nome campanha, nome campanha e nomes", "campanha descrição");
		when(service.atualizar(1L, campanha)).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(put("/campanhas/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
	}

	@Test
	@DisplayName("Criar Campanha informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
	public void criarNomeAbaixo4Caracteres() throws Exception {
		Campanha campanha = new Campanha(null, "Nom", "campanha descricao");
		when(service.salvar(Mockito.any(Campanha.class))).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(post("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
	}

	@Test
	@DisplayName("Atualizar Campanha informando um nome abaixo 4 caracteres, retornar mensagem de erro e status 400.")
	public void atualizarNomeAbaixo4Caracteres() throws Exception {
		Campanha campanha = new Campanha(1L, "Nom", "campanha descrição");
		when(service.atualizar(1L, campanha)).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(put("/campanhas/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
				.andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
	}

}
