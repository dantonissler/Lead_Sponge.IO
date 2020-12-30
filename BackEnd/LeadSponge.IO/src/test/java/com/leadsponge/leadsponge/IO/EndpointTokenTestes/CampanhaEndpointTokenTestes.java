package com.leadsponge.leadsponge.IO.EndpointTokenTestes;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
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

import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.campanha.CampanhaRepository;
import com.leadsponge.IO.services.CampanhaService;
import com.leadsponge.leadsponge.IO.util.Util;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class)
@AutoConfigureMockMvc
public class CampanhaEndpointTokenTestes {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@MockBean
	private CampanhaRepository repository;

	@MockBean
	private CampanhaService service;

	private MediaType contentType = new MediaType("application", "json");

	@Before
	private void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
	}

	@Test
	public void quandolistamosCampanhaUsandoUsuarioSenhaIncorretos_RetornarCodigoStatus401NaoAutorizado() throws Exception {
		mockMvc.perform(get("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
				.andDo(print());
	}

	@Test
	public void quandoBuscamosCampanhaUsandoUsuarioSenhaIncorretos_RetornarCodigoStatus401NaoAutorizado() throws Exception {
		Campanha campanha = new Campanha(3L, "campanha nome", "campanha descrição");
		when(repository.findById(3L)).thenReturn(java.util.Optional.of(campanha));
		mockMvc.perform(get("/campanhas/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
				.andExpect(status().isUnauthorized()).andDo(print());
	}

	@Test
	public void quandoDeletarCampanhaUsandoUsuarioSenhaIncorretos_RetornarCodigoStatus401NaoAutorizado() throws Exception {
		mockMvc.perform(delete("/campanhas/1").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
				.andExpect(status().isUnauthorized()).andDo(print());
	}

	@Test
	public void quandoCriarCampanhaUsandoUsuarioSenhaIncorretos_RetornarCodigoStatus401NaoAutorizado() throws Exception {
		mockMvc.perform(post("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
				.andDo(print());
	}

	@Test
	public void quandoAtualizarCampanhaUsandoUsuarioSenhaIncorretos_RetornarCodigoStatus401NaoAutorizado() throws Exception {
		mockMvc.perform(put("/campanhas/1").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
				.andDo(print());
	}

	@Test
	public void quandoListarCampanhaUsandoUsuarioSenhaCorretos_RetornarCodigoStatus200Sucesso() throws Exception {
		mockMvc.perform(get("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void quandoBuscamosCampanhaUsandoUsuarioSenhaCorretos_RetornarCodigoStatus200Sucesso() throws Exception {
		Campanha campanha = new Campanha(3L, "campanha nome", "campanha descrição");
		when(repository.findById(3L)).thenReturn(java.util.Optional.of(campanha));
		mockMvc.perform(get("/campanhas/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
				.andExpect(status().isOk()).andDo(print());
		verify(repository).findById(3L);
	}

	@Test
	public void quandoDeletarCampanhaUsandoUsuarioSenhaCorretos_RetornarCodigoStatus200Sucesso() throws Exception {
		Campanha campanha = new Campanha(3L, "campanha nome", "campanha descrição");
		when(repository.findById(3L)).thenReturn(java.util.Optional.of(campanha));
		doNothing().when(repository).deleteById(3L);
		mockMvc.perform(delete("/campanhas/{id}", 3).header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(repository).deleteById(3L);
	}

	@Test
	public void quandoCriarCampanhaUsandoUsuarioSenhaCorretos_RetornarCodigoStatus201Criado() throws Exception {
		Campanha campanha = new Campanha(null, "campanha nome", "campanha descricao");
		when(service.save(Mockito.any(Campanha.class))).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(post("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print());
	}

	@Test
	public void quandoAtualizarCampanhaUsandoUsuarioSenhaCorretos_RetornarCodigoStatus201() throws Exception {
		Campanha campanha = new Campanha(1L, "campanha nome", "campanha descrição");
		when(service.atualizar(1L, campanha)).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(put("/campanhas/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print());
	}
}
