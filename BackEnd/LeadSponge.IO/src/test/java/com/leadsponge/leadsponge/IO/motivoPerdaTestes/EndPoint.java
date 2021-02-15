package com.leadsponge.leadsponge.IO.motivoPerdaTestes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.motivoPerda.MotivoPerda;
import com.leadsponge.IO.repository.Filter.MotivoPerdaFilter;
import com.leadsponge.IO.services.MotivoPerdaService;
import com.leadsponge.leadsponge.IO.Util;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class)
@AutoConfigureMockMvc
@DisplayName("Testando token do EndPoint Motivo Perda")
public class EndPoint {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @MockBean
    private MotivoPerdaService service;

    @Mock
    private MotivoPerda motivoPerda;

    @Mock
    private Page<MotivoPerda> page;

    private final MediaType contentType = new MediaType("application", "json");

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
    }

    @Test
    @DisplayName("Listar Motivo Perdas, retornar a MotivoPerdas e status 200")
    public void listar() throws Exception {
        MotivoPerdaFilter motivoPerdaFilter = new MotivoPerdaFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(motivoPerdaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/motivoperda").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
        verify(service, times(1)).filtrar(motivoPerdaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Motivo Perdas usando filtro pelo nome, retornar a MotivoPerdas e status 200")
    public void listarNome() throws Exception {
        MotivoPerdaFilter motivoPerdaFilter = new MotivoPerdaFilter("nome");
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(motivoPerdaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/motivoperda?nome=nome").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
        verify(service, times(1)).filtrar(motivoPerdaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Motivo Perdas usando o id, retornar a MotivoPerdas e status 200 sucesso")
    public void buscar() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(3L, "nome", null);
        when(service.detalhar(3L)).thenReturn(motivoPerda);
        mockMvc.perform(get("/motivoperda/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
                .andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.nome").value("nome"));
        verify(service, times(1)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Motivo Perdas, retornar a MotivoPerda e status 200")
    public void deletar() throws Exception {
        mockMvc.perform(delete("/motivoperda/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
        verify(service, times(1)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Motivo Perdas, retornar a MotivoPerdas e status 201")
    public void criar() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(3L, "nome", null);
        when(service.salvar(motivoPerda)).thenReturn(motivoPerda);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(motivoPerda);
        mockMvc.perform(post("/motivoperda").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.nome").value("nome"));
        verify(service, times(1)).salvar(Mockito.any(MotivoPerda.class));
    }

    @Test
    @DisplayName("Atualizar Motivo Perdas, retornar a MotivoPerdas e status 201")
    public void atualizar() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(3L, "nome", null);
        when(service.atualizar(1L, motivoPerda)).thenReturn(motivoPerda);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(motivoPerda);
        mockMvc.perform(put("/motivoperda/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.nome").value("nome"));
        verify(service, times(1)).atualizar(1L, motivoPerda);
    }

    // Permissão de acesso

    @Test
    @DisplayName("Listar Motivo Perdas sem permissão de acesso, retornar o status 403")
    public void permissaoListar() throws Exception {
        MotivoPerdaFilter motivoPerdaFilter = new MotivoPerdaFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(motivoPerdaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/motivoperda").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
        verify(service, times(0)).filtrar(motivoPerdaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Motivo Perdas usando filtro pelo nome sem permissão de acesso, retornar o status 403")
    public void permissaoListarsNome() throws Exception {
        MotivoPerdaFilter motivoPerdaFilter = new MotivoPerdaFilter("nome");
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(motivoPerdaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/motivoperda?nome=nome").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
        verify(service, times(0)).filtrar(motivoPerdaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Motivo Perdas usando o id sem permissão de acesso, retornar o status 403")
    public void permissaoBuscar() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(3L, "nome", null);
        when(service.detalhar(3L)).thenReturn(motivoPerda);
        mockMvc.perform(get("/motivoperda/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden()).andDo(print());
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Motivo Perda sem permissão de acesso, retornar a MotivoPerda e status 403")
    public void permissaoDeletar() throws Exception {
        mockMvc.perform(delete("/motivoperda/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
        verify(service, times(0)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Motivo Perda sem permissão de acesso, retornar o status 403")
    public void permissaoCriar() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(1L, "nome", null);
        when(service.salvar(motivoPerda)).thenReturn(motivoPerda);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(motivoPerda);
        mockMvc.perform(post("/motivoperda").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType)).andExpect(status().isForbidden()).andDo(print());
        verify(service, times(0)).salvar(Mockito.any(MotivoPerda.class));
    }

    @Test
    @DisplayName("Atualizar Motivo Perda sem permissão de acesso, retornar o status 403")
    public void permissaoAtualizar() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(1L, "nome", null);
        when(service.atualizar(1L, motivoPerda)).thenReturn(motivoPerda);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(motivoPerda);
        mockMvc.perform(put("/motivoperda/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType)).andExpect(status().isForbidden()).andDo(print());
        verify(service, times(0)).atualizar(1L, motivoPerda);
    }

    // testando o Token de acesso

    @Test
    @DisplayName("Listar Motivo Perda com usuario e senha incorretos, retornar status 401")
    public void listamosTokenIncorreto() throws Exception {
        MotivoPerdaFilter motivoPerdaFilter = new MotivoPerdaFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(motivoPerdaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/motivoperda").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
                .andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).filtrar(motivoPerdaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Motivo Perda usando usuario e senha incorretos, retornar status 401")
    public void buscarTokenIncorreto() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(1L, "nome", null);
        when(service.detalhar(3L)).thenReturn(motivoPerda);
        mockMvc.perform(get("/motivoperda/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Motivo Perda com usuario e senha incorretos, retornar status 401")
    public void deletarTokenIncorreto() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(1L, "nome", null);
        when(service.deletar(3L)).thenReturn(motivoPerda);
        mockMvc.perform(delete("/motivoperda/1").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
                .andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Motivo Perda com usuario e senha incorretos, retornar status 401")
    public void criarTokenIncorreto() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(1L, "nome", null);
        when(service.salvar(motivoPerda)).thenReturn(motivoPerda);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(motivoPerda);
        mockMvc.perform(post("/motivoperda").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).atualizar(1L, motivoPerda);
    }

    @Test
    @DisplayName("Atualizar Motivo Perda com usuario e senha incorretos, retornar status 401")
    public void atualizarTokenIncorreto() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(1L, "nome", null);
        when(service.atualizar(1L, motivoPerda)).thenReturn(motivoPerda);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(motivoPerda);
        mockMvc.perform(put("/motivoperda/1").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).atualizar(1L, motivoPerda);
    }

    @Test
    @DisplayName("Listar Motivo Perdas sem token, retornar status 401")
    public void listamosSemToken() throws Exception {
        MotivoPerdaFilter motivoPerdaFilter = new MotivoPerdaFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(motivoPerdaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/motivoperda")).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).filtrar(motivoPerdaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Motivo Perdas sem token, retornar status 401")
    public void buscarSemToken() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(1L, "nome", null);
        when(service.detalhar(3L)).thenReturn(motivoPerda);
        mockMvc.perform(get("/motivoperda/{id}", 3L)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Motivo Perda sem token, retornar status 401")
    public void deletarSemToken() throws Exception {
        when(service.deletar(3L)).thenReturn(motivoPerda);
        mockMvc.perform(delete("/motivoperda/1")).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Motivo Perda sem token, retornar status 401")
    public void criarSemToken() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(1L, "nome", null);
        when(service.salvar(motivoPerda)).thenReturn(motivoPerda);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(motivoPerda);
        mockMvc.perform(post("/motivoperda").accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).salvar(motivoPerda);
    }

    @Test
    @DisplayName("Atualizar Motivo Perda sem token, retornar status 401")
    public void atualizarSemToken() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(1L, "nome", null);
        when(service.atualizar(1L, motivoPerda)).thenReturn(motivoPerda);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(motivoPerda);
        mockMvc.perform(put("/motivoperda/1").accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).atualizar(1L, motivoPerda);
    }

    // validar a entidade ate aqui
    @Test
    @DisplayName("Atualizar Motivo Perda informando um nome null, retornar mensagem de erro e status 400.")
    public void atualizarNomeNull() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(1L, null, null);
        when(service.atualizar(1L, motivoPerda)).thenReturn(motivoPerda);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(motivoPerda);
        mockMvc.perform(put("/motivoperda/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome")).andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null"))
                .andExpect(jsonPath("$.field").value("nome"));
        verify(service, times(0)).atualizar(1L, motivoPerda);
    }

    @Test
    @DisplayName("Atualizar Motivo Perda informando um nome vazio, retornar mensagem de erro e status 400.")
    public void atualizarNomeVazio() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(1L, "", null);
        when(service.atualizar(1L, motivoPerda)).thenReturn(motivoPerda);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(motivoPerda);
        mockMvc.perform(put("/motivoperda/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
        verify(service, times(0)).atualizar(1L, motivoPerda);
    }

    @Test
    @DisplayName("Atualizar Motivo Perda informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
    public void atualizarNomeAcima50Caracteres() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(null, "nome nome nome nome nome nome nome nome nome nome nome nome", null);
        when(service.atualizar(1L, motivoPerda)).thenReturn(motivoPerda);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(motivoPerda);
        mockMvc.perform(put("/motivoperda/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
        verify(service, times(0)).atualizar(1L, motivoPerda);
    }

    @Test
    @DisplayName("Atualizar Motivo Perda informando um nome abaixo 4 caracteres, retornar mensagem de erro e status 400.")
    public void atualizarNomeAbaixo4Caracteres() throws Exception {
        MotivoPerda motivoPerda = new MotivoPerda(null, "nom", null);
        when(service.atualizar(1L, motivoPerda)).thenReturn(motivoPerda);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(motivoPerda);
        mockMvc.perform(put("/motivoperda/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
        verify(service, times(0)).atualizar(1L, motivoPerda);

    }
}