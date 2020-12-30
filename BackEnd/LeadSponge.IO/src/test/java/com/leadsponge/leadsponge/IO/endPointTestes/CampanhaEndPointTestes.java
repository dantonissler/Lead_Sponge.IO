package com.leadsponge.leadsponge.IO.endPointTestes;

import static org.mockito.Mockito.when;
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
public class CampanhaEndPointTestes {

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
	public void quandoCriarCampanhaSemInformarUmNome_RetornarCodigoStatus400PedidoRuim() throws Exception {
		Campanha campanha = new Campanha(null, "", "campanha descricao");
		when(service.save(Mockito.any(Campanha.class))).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(post("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void quandoCriarCampanhaSemInformarUmaDescricao_RetornarCodigoStatus201() throws Exception {
		Campanha campanha = new Campanha(null, "campanha nome", "");
		when(service.save(Mockito.any(Campanha.class))).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(post("/campanhas").header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print());
	}

	@Test
	public void quandoAtualizarCampanhaSemInformarUmNome_RetornarCodigoStatus400PedidoRuim() throws Exception {
		Campanha campanha = new Campanha(1L, "", "campanha descrição");
		when(service.atualizar(1L, campanha)).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(put("/campanhas/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void quandoAtualizarCampanhaSemInformarUmaDescricao_RetornarCodigoStatus201() throws Exception {
		Campanha campanha = new Campanha(1L, "campanha nome", "");
		when(service.atualizar(1L, campanha)).thenReturn(campanha);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(campanha);
		mockMvc.perform(put("/campanhas/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "admin", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print());
	}

	// Converter uma string para base64: new String(Base64Utils.encode(("danton:214255kjukii").getBytes()))
}
