package com.leadsponge.leadsponge.IO.estagioNegociacaoTestes;

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
import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.repository.Filter.EstagioNegociacaoFilter;
import com.leadsponge.IO.services.EstagioNegociacaoService;
import com.leadsponge.leadsponge.IO.Util;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class)
@AutoConfigureMockMvc
@DisplayName("Testando token do EndPoint Estagio Negociação")
public class EndPoint {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @MockBean
    private EstagioNegociacaoService service;

    @Mock
    private EstagioNegociacao estagioNegociacao;

    @Mock
    private Page<EstagioNegociacao> page;

    private final MediaType contentType = new MediaType("application", "json");

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain).build();
    }

    @Test
    @DisplayName("Listar Estagios da Negociacao, retornar a Estagio da Negociacao e status 200")
    public void listar() throws Exception {
        EstagioNegociacaoFilter estagioNegociacaoFilterFilter = new EstagioNegociacaoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(estagioNegociacaoFilterFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/estagios")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(estagioNegociacaoFilterFilter, pageable);
    }

    @Test
    @DisplayName("Listar Estagios da Negociacao usando filtro pelo nome, retornar a Estagio da Negociacao e status 200")
    public void listarNome() throws Exception {
        EstagioNegociacaoFilter estagioNegociacaoFilterFilter = new EstagioNegociacaoFilter("nome", null, null);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(estagioNegociacaoFilterFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/estagios?nome=nome")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(estagioNegociacaoFilterFilter, pageable);
    }

    @Test
    @DisplayName("Listar Estagios da Negociacao usando filtro pela apelido, retornar a Estagio da Negociacao e status 200")
    public void listarApelido() throws Exception {
        EstagioNegociacaoFilter estagioNegociacaoFilterFilter = new EstagioNegociacaoFilter(null, "apelido", null);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(estagioNegociacaoFilterFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/estagios?apelido=apelido")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(estagioNegociacaoFilterFilter, pageable);
    }

    @Test
    @DisplayName("Listar Estagios da Negociacao usando filtro pela posicao, retornar a Estagio da Negociacao e status 200")
    public void listarPosicao() throws Exception {
        EstagioNegociacaoFilter estagioNegociacaoFilterFilter = new EstagioNegociacaoFilter(null, null, 1);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(estagioNegociacaoFilterFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/estagios?posicao=1")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(estagioNegociacaoFilterFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Estagios da Negociacao usando o id, retornar a Estagio da Negociacao e status 200 sucesso")
    public void buscar() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "nome", "apelido", 1, null);
        when(service.detalhar(3L)).thenReturn(estagioNegociacao);
        mockMvc.perform(get("/estagios/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"))
                .andExpect(jsonPath("$.apelido").value("apelido"));
        verify(service, times(1)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Estagios da Negociacao, retornar a EstagioNegociacao e status 200")
    public void deletar() throws Exception {
        mockMvc.perform(delete("/estagios/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Estagios da Negociacao, retornar a EstagioNegociacao e status 201")
    public void criar() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "nome", "apelido", 1, null);
        when(service.salvar(estagioNegociacao)).thenReturn(estagioNegociacao);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(estagioNegociacao);
        mockMvc.perform(post("/estagios")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"))
                .andExpect(jsonPath("$.apelido").value("apelido"));
        verify(service, times(1)).salvar(Mockito.any(EstagioNegociacao.class));
    }

    @Test
    @DisplayName("Atualizar Estagios da Negociacao, retornar a Estagio da Negociacao e status 201")
    public void atualizar() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "nome", "apelido", 1, null);
        when(service.atualizar(1L, estagioNegociacao)).thenReturn(estagioNegociacao);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(estagioNegociacao);
        mockMvc.perform(put("/estagios/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"))
                .andExpect(jsonPath("$.apelido").value("apelido"));
        verify(service, times(1))
                .atualizar(1L, estagioNegociacao);
    }

    // Permissão de acesso

    @Test
    @DisplayName("Listar Estagios da Negociacao sem permissão de acesso, retornar o status 403")
    public void permissaoListar() throws Exception {
        EstagioNegociacaoFilter estagioNegociacaoFilterFilter = new EstagioNegociacaoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(estagioNegociacaoFilterFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/estagios")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(estagioNegociacaoFilterFilter, pageable);
    }

    @Test
    @DisplayName("Listar Estagios da Negociacao usando filtro pelo nome sem permissão de acesso, retornar o status 403")
    public void permissaoListarsNome() throws Exception {
        EstagioNegociacaoFilter estagioNegociacaoFilterFilter = new EstagioNegociacaoFilter("nome", null, null);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(estagioNegociacaoFilterFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/estagios?nome=nome")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(estagioNegociacaoFilterFilter, pageable);
    }

    @Test
    @DisplayName("Listar Estagios da Negociacao usando filtro pela apelido sem permissão de acesso, retornar o status 403")
    public void permissaoListarCargo() throws Exception {
        EstagioNegociacaoFilter campanhaFilter = new EstagioNegociacaoFilter(null, "apelido", null);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/estagios?apelido=apelido")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Estagios da Negociacao usando filtro pela posição sem permissão de acesso, retornar o status 403")
    public void permissaoListarPosicao() throws Exception {
        EstagioNegociacaoFilter campanhaFilter = new EstagioNegociacaoFilter(null, null, 1);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/estagios?posicao=1")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Estagios da Negociacao usando o id sem permissão de acesso, retornar o status 403")
    public void permissaoBuscar() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "nome", "apelido", 1, null);
        when(service.detalhar(3L)).thenReturn(estagioNegociacao);
        mockMvc.perform(get("/estagios/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Estagio da Negociacao sem permissão de acesso, retornar a Estagio da Negociacao e status 403")
    public void permissaoDeletar() throws Exception {
        mockMvc.perform(delete("/estagios/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print());
        verify(service, times(0)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Estagio da Negociacao sem permissão de acesso, retornar o status 403")
    public void permissaoCriar() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "nome", "posição", 1, null);
        when(service.salvar(estagioNegociacao)).thenReturn(estagioNegociacao);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(estagioNegociacao);
        mockMvc.perform(post("/estagios")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).salvar(Mockito.any(EstagioNegociacao.class));
    }

    @Test
    @DisplayName("Atualizar Estagio da Negociacao sem permissão de acesso, retornar o status 403")
    public void permissaoAtualizar() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "nome", "posição", 1, null);
        when(service.atualizar(1L, estagioNegociacao)).thenReturn(estagioNegociacao);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(estagioNegociacao);
        mockMvc.perform(put("/estagios/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .atualizar(1L, estagioNegociacao);
    }

    // testando o Token de acesso

    @Test
    @DisplayName("Listar Estagio da Negociacao com usuario e senha incorretos, retornar status 401")
    public void listamosTokenIncorreto() throws Exception {
        EstagioNegociacaoFilter estagioNegociacaoFilterFilter = new EstagioNegociacaoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(estagioNegociacaoFilterFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/estagios")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .filtrar(estagioNegociacaoFilterFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Estagio da Negociacao usando usuario e senha incorretos, retornar status 401")
    public void buscarTokenIncorreto() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "nome", "posição", 1, null);
        when(service.detalhar(3L)).thenReturn(estagioNegociacao);
        mockMvc.perform(get("/estagios/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Estagio da Negociacao com usuario e senha incorretos, retornar status 401")
    public void deletarTokenIncorreto() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "nome", "posição", 1, null);
        when(service.deletar(3L)).thenReturn(estagioNegociacao);
        mockMvc.perform(delete("/estagios/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Estagio da Negociacao com usuario e senha incorretos, retornar status 401")
    public void criarTokenIncorreto() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "nome", "posição", 1, null);
        when(service.salvar(estagioNegociacao)).thenReturn(estagioNegociacao);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(estagioNegociacao);
        mockMvc.perform(post("/estagios")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, estagioNegociacao);
    }

    @Test
    @DisplayName("Atualizar Estagio da Negociacao com usuario e senha incorretos, retornar status 401")
    public void atualizarTokenIncorreto() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "nome", "posição", 1, null);
        when(service.atualizar(1L, estagioNegociacao)).thenReturn(estagioNegociacao);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(estagioNegociacao);
        mockMvc.perform(put("/estagios/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, estagioNegociacao);
    }

    @Test
    @DisplayName("Listar Estagio da Negociacao sem token, retornar status 401")
    public void listamosSemToken() throws Exception {
        EstagioNegociacaoFilter estagioNegociacaoFilterFilter = new EstagioNegociacaoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(estagioNegociacaoFilterFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/estagios"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .filtrar(estagioNegociacaoFilterFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Estagio da Negociacao sem token, retornar status 401")
    public void buscarSemToken() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "nome", "posição", 1, null);
        when(service.detalhar(3L)).thenReturn(estagioNegociacao);
        mockMvc.perform(get("/estagios/{id}", 3L))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Estagio da Negociacao sem token, retornar status 401")
    public void deletarSemToken() throws Exception {
        when(service.deletar(3L)).thenReturn(estagioNegociacao);
        mockMvc.perform(delete("/estagios/1"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Estagio da Negociacao sem token, retornar status 401")
    public void criarSemToken() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "nome", "posição", 1, null);
        when(service.salvar(estagioNegociacao)).thenReturn(estagioNegociacao);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(estagioNegociacao);
        mockMvc.perform(post("/estagios")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).salvar(estagioNegociacao);
    }

    @Test
    @DisplayName("Atualizar Estagio da Negociacao sem token, retornar status 401")
    public void atualizarSemToken() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "nome", "posição", 1, null);
        when(service.atualizar(1L, estagioNegociacao)).thenReturn(estagioNegociacao);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(estagioNegociacao);
        mockMvc.perform(put("/estagios/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .atualizar(1L, estagioNegociacao);
    }

    // validar a entidade ate aqui

    @Test
    @DisplayName("Criar Estagio da Negociacao informando nome, descricao e apelido, retornar as informações enviadas e Status 201")
    public void criarSemInformarUmaDescricao() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "nome", "apelido", 1, null);
        when(service.salvar(Mockito.any(EstagioNegociacao.class))).thenReturn(estagioNegociacao);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(estagioNegociacao);
        mockMvc.perform(post("/estagios")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(service, times(1)).salvar(estagioNegociacao);
    }

    @Test
    @DisplayName("Criar Estagio da Negociacao informando um nome null, retornar mensagem de erro e status 400.")
    public void criarNomeNull() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, null, "apelido", 1, null);
        when(service.salvar(Mockito.any(EstagioNegociacao.class))).thenReturn(estagioNegociacao);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(estagioNegociacao);
        mockMvc.perform(post("/estagios")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null"))
                .andExpect(jsonPath("$.field").value("nome"));
        verify(service, times(0)).salvar(estagioNegociacao);
    }

    @Test
    @DisplayName("Criar Estagio da Negociacao informando um nome vazio, retornar mensagem de erro e status 400.")
    public void criarNomeVazio() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(1L, "", "cargo", 1, null);
        when(service.salvar(Mockito.any(EstagioNegociacao.class))).thenReturn(estagioNegociacao);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(estagioNegociacao);
        mockMvc.perform(post("/estagios")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."))
                .andExpect(jsonPath("$.field").value("nome"));
        verify(service, times(0)).salvar(estagioNegociacao);
    }

    @Test
    @DisplayName("Criar Estagio da Negociacao informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
    public void criarNomeAcima50Caracteres() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(null, "nome nome nome nome nome nome nome nome nome nome nome nome", "apelido", 1, null);
        when(service.salvar(Mockito.any(EstagioNegociacao.class))).thenReturn(estagioNegociacao);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(estagioNegociacao);
        mockMvc.perform(post("/estagios")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
        verify(service, times(0)).salvar(estagioNegociacao);
    }

    @Test
    @DisplayName("Atualizar Estagio da Negociacao informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
    public void atualizarNomeAcima50Caracteres() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(null, "nome nome nome nome nome nome nome nome nome nome nome nome", "cargo", 1, null);
        when(service.atualizar(1L, estagioNegociacao)).thenReturn(estagioNegociacao);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(estagioNegociacao);
        mockMvc.perform(put("/estagios/{id}", 1L)
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
                .atualizar(1L, estagioNegociacao);
    }

    @Test
    @DisplayName("Criar Estagio da Negociacao informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
    public void criarNomeAbaixo4Caracteres() throws Exception {
        EstagioNegociacao estagioNegociacao = new EstagioNegociacao(null, "nom", "cargo", 1, null);
        when(service.salvar(Mockito.any(EstagioNegociacao.class))).thenReturn(estagioNegociacao);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(estagioNegociacao);
        mockMvc.perform(post("/estagios")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
        verify(service, times(0)).salvar(estagioNegociacao);
    }
}
