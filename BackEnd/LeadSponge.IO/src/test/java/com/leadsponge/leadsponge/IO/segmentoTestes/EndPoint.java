package com.leadsponge.leadsponge.IO.segmentoTestes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.repository.Filter.SegmentoFilter;
import com.leadsponge.IO.services.SegmentoService;
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
@DisplayName("Testando token do EndPoint Segmento")
public class EndPoint {

    private final MediaType contentType = new MediaType("application", "json");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    @MockBean
    private SegmentoService service;
    @Mock
    private Segmento segmento;
    @Mock
    private Page<Segmento> page;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain).build();
    }

    @Test
    @DisplayName("Listar Segmentos, retornar a Segmentos e status 200")
    public void listar() throws Exception {
        SegmentoFilter segmentoFilter = new SegmentoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(segmentoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/segmentos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(segmentoFilter, pageable);
    }

    @Test
    @DisplayName("Listar Segmentos usando filtro pelo nome, retornar a Segmentos e status 200")
    public void listarNome() throws Exception {
        SegmentoFilter segmentoFilter = new SegmentoFilter("nome");
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(segmentoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/segmentos?nome=nome")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(segmentoFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Segmentos usando o id, retornar a Segmentos e status 200 sucesso")
    public void buscar() throws Exception {
        Segmento segmento = new Segmento(3L, "nome", null);
        when(service.detalhar(3L)).thenReturn(segmento);
        mockMvc.perform(get("/segmentos/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"))
                .andExpect(jsonPath("$.cargo").value("cargo"));
        verify(service, times(1)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Segmentos, retornar a Segmento e status 200")
    public void deletar() throws Exception {
        mockMvc.perform(delete("/segmentos/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Segmentos, retornar a Segmentos e status 201")
    public void criar() throws Exception {
        Segmento segmento = new Segmento(3L, "nome", null);
        when(service.salvar(segmento)).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(post("/segmentos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"))
                .andExpect(jsonPath("$.cargo").value("cargo"));
        verify(service, times(1)).salvar(Mockito.any(Segmento.class));
    }

    @Test
    @DisplayName("Atualizar Segmentos, retornar a Segmentos e status 201")
    public void atualizar() throws Exception {
        Segmento segmento = new Segmento(3L, "nome", null);
        when(service.atualizar(1L, segmento)).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(put("/segmentos/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"))
                .andExpect(jsonPath("$.cargo").value("cargo"));
        verify(service, times(1))
                .atualizar(1L, segmento);
    }

    // Permissão de acesso

    @Test
    @DisplayName("Listar Segmentos sem permissão de acesso, retornar o status 403")
    public void permissaoListar() throws Exception {
        SegmentoFilter segmentoFilter = new SegmentoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(segmentoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/segmentos")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(segmentoFilter, pageable);
    }

    @Test
    @DisplayName("Listar Segmentos usando filtro pelo nome sem permissão de acesso, retornar o status 403")
    public void permissaoListarsNome() throws Exception {
        SegmentoFilter segmentoFilter = new SegmentoFilter("nome");
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(segmentoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/segmentos?nome=nome")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(segmentoFilter, pageable);
    }


    @Test
    @DisplayName("Buscar Segmentos usando o id sem permissão de acesso, retornar o status 403")
    public void permissaoBuscar() throws Exception {
        Segmento segmento = new Segmento(3L, "nome", null);
        when(service.detalhar(3L)).thenReturn(segmento);
        mockMvc.perform(get("/segmentos/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Segmento sem permissão de acesso, retornar a Segmento e status 403")
    public void permissaoDeletar() throws Exception {
        mockMvc.perform(delete("/segmentos/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print());
        verify(service, times(0)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Segmento sem permissão de acesso, retornar o status 403")
    public void permissaoCriar() throws Exception {
        Segmento segmento = new Segmento(1L, "nome", null);
        when(service.salvar(segmento)).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(post("/segmentos")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).salvar(Mockito.any(Segmento.class));
    }

    @Test
    @DisplayName("Atualizar Segmento sem permissão de acesso, retornar o status 403")
    public void permissaoAtualizar() throws Exception {
        Segmento segmento = new Segmento(1L, "nome", null);
        when(service.atualizar(1L, segmento)).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(put("/segmentos/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .atualizar(1L, segmento);
    }

    // testando o Token de acesso

    @Test
    @DisplayName("Listar Segmento com usuario e senha incorretos, retornar status 401")
    public void listamosTokenIncorreto() throws Exception {
        SegmentoFilter segmentoFilter = new SegmentoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(segmentoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/segmentos")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .filtrar(segmentoFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Segmento usando usuario e senha incorretos, retornar status 401")
    public void buscarTokenIncorreto() throws Exception {
        Segmento segmento = new Segmento(1L, "nome", null);
        when(service.detalhar(3L)).thenReturn(segmento);
        mockMvc.perform(get("/segmentos/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Segmento com usuario e senha incorretos, retornar status 401")
    public void deletarTokenIncorreto() throws Exception {
        Segmento segmento = new Segmento(1L, "nome", null);
        when(service.deletar(3L)).thenReturn(segmento);
        mockMvc.perform(delete("/segmentos/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Segmento com usuario e senha incorretos, retornar status 401")
    public void criarTokenIncorreto() throws Exception {
        Segmento segmento = new Segmento(1L, "nome", null);
        when(service.salvar(segmento)).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(post("/segmentos")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, segmento);
    }

    @Test
    @DisplayName("Atualizar Segmento com usuario e senha incorretos, retornar status 401")
    public void atualizarTokenIncorreto() throws Exception {
        Segmento segmento = new Segmento(1L, "nome", null);
        when(service.atualizar(1L, segmento)).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(put("/segmentos/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, segmento);
    }

    @Test
    @DisplayName("Listar Segmentos sem token, retornar status 401")
    public void listamosSemToken() throws Exception {
        SegmentoFilter segmentoFilter = new SegmentoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(segmentoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/segmentos"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .filtrar(segmentoFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Segmentos sem token, retornar status 401")
    public void buscarSemToken() throws Exception {
        Segmento segmento = new Segmento(1L, "nome", null);
        when(service.detalhar(3L)).thenReturn(segmento);
        mockMvc.perform(get("/segmentos/{id}", 3L))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Segmento sem token, retornar status 401")
    public void deletarSemToken() throws Exception {
        when(service.deletar(3L)).thenReturn(segmento);
        mockMvc.perform(delete("/segmentos/1"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Segmento sem token, retornar status 401")
    public void criarSemToken() throws Exception {
        Segmento segmento = new Segmento(1L, "nome", null);
        when(service.salvar(segmento)).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(post("/segmentos")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).salvar(segmento);
    }

    @Test
    @DisplayName("Atualizar Segmento sem token, retornar status 401")
    public void atualizarSemToken() throws Exception {
        Segmento segmento = new Segmento(1L, "nome", null);
        when(service.atualizar(1L, segmento)).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(put("/segmentos/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .atualizar(1L, segmento);
    }

    // validar a entidade ate aqui

    @Test
    @DisplayName("Criar Segmento informando nome e descricao, retornar as informações enviadas e Status 201")
    public void criarSemInformarUmaDescricao() throws Exception {
        Segmento segmento = new Segmento(1L, "nome", null);
        when(service.salvar(Mockito.any(Segmento.class))).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(post("/segmentos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(service, times(1)).salvar(segmento);
    }

    @Test
    @DisplayName("Atualizar Segmento informando nome e descricao, retornar as informações enviadas e Status 201")
    public void atualizarSemInformarUmaDescricao() throws Exception {
        Segmento segmento = new Segmento(1L, "nome", null);
        when(service.atualizar(1L, segmento)).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(put("/segmentos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(service, times(1)).atualizar(1L, segmento);
    }

    @Test
    @DisplayName("Criar Segmento informando um nome null, retornar mensagem de erro e status 400.")
    public void criarNomeNull() throws Exception {
        Segmento segmento = new Segmento(1L, null, null);
        when(service.salvar(Mockito.any(Segmento.class))).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(post("/segmentos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("Não pode ser null"));
        verify(service, times(0)).salvar(segmento);
    }

    @Test
    @DisplayName("Atualizar Segmento informando um nome null, retornar mensagem de erro e status 400.")
    public void atualizarNomeNull() throws Exception {
        Segmento segmento = new Segmento(1L, null, null);
        when(service.atualizar(1L, Mockito.any(Segmento.class))).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(put("/segmentos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("Não pode ser null"));
        verify(service, times(0)).atualizar(1L, segmento);
    }


    @Test
    @DisplayName("Criar Segmento informando um nome vazio, retornar mensagem de erro e status 400.")
    public void criarNomeVazio() throws Exception {
        Segmento segmento = new Segmento(1L, "", null);
        when(service.salvar(Mockito.any(Segmento.class))).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(post("/segmentos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
        verify(service, times(0)).salvar(segmento);
    }

    @Test
    @DisplayName("Atualizar Segmento informando um nome vazio, retornar mensagem de erro e status 400.")
    public void atualizarNomeVazio() throws Exception {
        Segmento segmento = new Segmento(1L, "", null);
        when(service.atualizar(1L, Mockito.any(Segmento.class))).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(put("/segmentos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
        verify(service, times(0)).atualizar(1L, segmento);
    }

    @Test
    @DisplayName("Criar Segmento informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
    public void criarNomeAcima50Caracteres() throws Exception {
        Segmento segmento = new Segmento(null, "nome nome nome nome nome nome nome nome nome nome nome nome", null);
        when(service.salvar(segmento)).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(post("/segmentos/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
        verify(service, times(0))
                .salvar(segmento);
    }

    @Test
    @DisplayName("Atualizar Segmento informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
    public void atualizarNomeAcima50Caracteres() throws Exception {
        Segmento segmento = new Segmento(null, "nome nome nome nome nome nome nome nome nome nome nome nome", null);
        when(service.atualizar(1L, segmento)).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(put("/segmentos/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
        verify(service, times(0))
                .atualizar(1L, segmento);
    }

    @Test
    @DisplayName("Criar Segmento informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
    public void criarNomeAbaixo4Caracteres() throws Exception {
        Segmento segmento = new Segmento(null, "nom", null);
        when(service.salvar(Mockito.any(Segmento.class))).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(post("/segmentos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
        verify(service, times(0)).salvar(segmento);
    }

    @Test
    @DisplayName("Atualizar Segmento informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
    public void atualizarNomeAbaixo4Caracteres() throws Exception {
        Segmento segmento = new Segmento(null, "nom", null);
        when(service.atualizar(1L, Mockito.any(Segmento.class))).thenReturn(segmento);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(segmento);
        mockMvc.perform(put("/segmentos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
        verify(service, times(0)).atualizar(1L, segmento);
    }
}
