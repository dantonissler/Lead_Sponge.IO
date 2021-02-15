package com.leadsponge.leadsponge.IO.negociacaoProdutoTestes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.negociacao.TipoReincidencia;
import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;
import com.leadsponge.IO.models.produto.Produto;
import com.leadsponge.IO.repository.Filter.NegociacaoProdutoFilter;
import com.leadsponge.IO.services.NegociacaoProdutoService;
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

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class)
@AutoConfigureMockMvc
@DisplayName("Testando token do EndPoint Negociacao Produto")
public class EndPoint {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @MockBean
    private NegociacaoProdutoService service;

    @Mock
    private NegociacaoProduto negociacaoProduto;

    @Mock
    private Page<NegociacaoProduto> page;

    private final MediaType contentType = new MediaType("application", "json");

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
    }

    @Test
    @DisplayName("Listar Negociacao Produtos, retornar a Negociacao Produtos e status 200")
    public void listar() throws Exception {
        NegociacaoProdutoFilter negociacaoProdutoFilter = new NegociacaoProdutoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(negociacaoProdutoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/negociacaoProdutos").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
        verify(service, times(1)).filtrar(negociacaoProdutoFilter, pageable);
    }

    @Test
    @DisplayName("Listar Negociacao Produtos usando filtro pela quantidade, retornar a Negociacao Produtos e status 200")
    public void listarQuantidade() throws Exception {
        NegociacaoProdutoFilter negociacaoProdutoFilter = new NegociacaoProdutoFilter(1, null, null);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(negociacaoProdutoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/negociacaoProdutos?quantidade=1").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
        verify(service, times(1)).filtrar(negociacaoProdutoFilter, pageable);
    }

    @Test
    @DisplayName("Listar Negociacao Produtos usando filtro pelo valor, retornar a Negociacao Produtos e status 200")
    public void listarValor() throws Exception {
        NegociacaoProdutoFilter negociacaoProdutoFilter = new NegociacaoProdutoFilter(null, new BigDecimal(1), null);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(negociacaoProdutoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/negociacaoProdutos?valor=1").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
        verify(service, times(1)).filtrar(negociacaoProdutoFilter, pageable);
    }

    @Test
    @DisplayName("Listar Negociacao Produtos usando filtro pelo total, retornar a Negociacao Produtos e status 200")
    public void listarTotal() throws Exception {
        NegociacaoProdutoFilter negociacaoProdutoFilter = new NegociacaoProdutoFilter(null, null, new BigDecimal(1));
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(negociacaoProdutoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/negociacaoProdutos?total=1").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
        verify(service, times(1)).filtrar(negociacaoProdutoFilter, pageable);
    }

    @Test
    @DisplayName("Listar Negociacao Produtos usando filtro por todos os filtros, retornar a NegociacaoProdutos e status 200")
    public void listarTodosFiltros() throws Exception {
        NegociacaoProdutoFilter negociacaoProdutoFilter = new NegociacaoProdutoFilter(1, new BigDecimal(1), new BigDecimal(1));
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(negociacaoProdutoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/negociacaoProdutos?quantidade=1&valor=1&total=1").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
        verify(service, times(1)).filtrar(negociacaoProdutoFilter, pageable);
    }


    @Test
    @DisplayName("Deletar Negociacao Produtos, retornar a NegociacaoProduto e status 200")
    public void deletar() throws Exception {
        mockMvc.perform(delete("/negociacaoProdutos/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
        verify(service, times(1)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Negociacao Produtos, retornar a NegociacaoProdutos e status 201")
    public void criar() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, new Produto(1L), new Negociacao(1L));
        when(service.salvar(negociacaoProduto)).thenReturn(negociacaoProduto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(negociacaoProduto);
        mockMvc.perform(post("/negociacaoProdutos").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.quantidade").value(1)).andExpect(jsonPath("$.valor").value(1));
        verify(service, times(1)).salvar(Mockito.any(NegociacaoProduto.class));
    }

    @Test
    @DisplayName("Atualizar Negociacao Produtos, retornar a Negociacao Produtos e status 201")
    public void atualizar() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, new Produto(1L), new Negociacao(1L));
        when(service.atualizar(1L, negociacaoProduto)).thenReturn(negociacaoProduto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(negociacaoProduto);
        mockMvc.perform(put("/negociacaoProdutos/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.quantidade").value(1)).andExpect(jsonPath("$.valor").value(1));
        verify(service, times(1)).atualizar(1L, negociacaoProduto);
    }

    // Permissão de acesso

    @Test
    @DisplayName("Listar Negociacao Produtos sem permissão de acesso, retornar o status 403")
    public void permissaoListar() throws Exception {
        NegociacaoProdutoFilter negociacaoProdutoFilter = new NegociacaoProdutoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(negociacaoProdutoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/negociacaoProdutos").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
        verify(service, times(0)).filtrar(negociacaoProdutoFilter, pageable);
    }

    @Test
    @DisplayName("Listar Negociacao Produtos usando filtro pela quantidade sem permissão de acesso, retornar o status 403")
    public void permissaoListarsQuantidade() throws Exception {
        NegociacaoProdutoFilter negociacaoProdutoFilter = new NegociacaoProdutoFilter(1, null, null);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(negociacaoProdutoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/negociacaoProdutos?quantidade=1").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
        verify(service, times(0)).filtrar(negociacaoProdutoFilter, pageable);
    }

    @Test
    @DisplayName("Listar Negociacao Produtos usando filtro pela valor sem permissão de acesso, retornar o status 403")
    public void permissaoListarValor() throws Exception {
        NegociacaoProdutoFilter campanhaFilter = new NegociacaoProdutoFilter(null, new BigDecimal(1), null);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/negociacaoProdutos?valor=1").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
        verify(service, times(0)).filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Negociacao Produtos usando filtro pelo total sem permissão de acesso, retornar o status 403")
    public void permissaoListarTotal() throws Exception {
        NegociacaoProdutoFilter campanhaFilter = new NegociacaoProdutoFilter(null, null, new BigDecimal(1));
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/negociacaoProdutos?total=1").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
        verify(service, times(0)).filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Negociacao Produtos usando o id sem permissão de acesso, retornar o status 403")
    public void permissaoBuscar() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, null, null, null, null, Mockito.mock(Produto.class), Mockito.mock(Negociacao.class));
        when(service.detalhar(3L)).thenReturn(negociacaoProduto);
        mockMvc.perform(get("/negociacaoProdutos/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden()).andDo(print());
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Negociacao Produto sem permissão de acesso, retornar a NegociacaoProduto e status 403")
    public void permissaoDeletar() throws Exception {
        mockMvc.perform(delete("/negociacaoProdutos/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
        verify(service, times(0)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Negociacao Produto sem permissão de acesso, retornar o status 403")
    public void permissaoCriar() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, new Produto(1L), new Negociacao(1L));
        when(service.salvar(negociacaoProduto)).thenReturn(negociacaoProduto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(negociacaoProduto);
        mockMvc.perform(post("/negociacaoProdutos").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType)).andExpect(status().isForbidden()).andDo(print());
        verify(service, times(0)).salvar(Mockito.any(NegociacaoProduto.class));
    }

    @Test
    @DisplayName("Atualizar Negociacao Produto sem permissão de acesso, retornar o status 403")
    public void permissaoAtualizar() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, new Produto(1L), new Negociacao(1L));
        when(service.atualizar(1L, negociacaoProduto)).thenReturn(negociacaoProduto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(negociacaoProduto);
        mockMvc.perform(put("/negociacaoProdutos/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType)).andExpect(status().isForbidden()).andDo(print());
        verify(service, times(0)).atualizar(3L, negociacaoProduto);
    }

    // testando o Token de acesso

    @Test
    @DisplayName("Listar Negociacao Produto com usuario e senha incorretos, retornar status 401")
    public void listamosTokenIncorreto() throws Exception {
        NegociacaoProdutoFilter negociacaoProdutoFilter = new NegociacaoProdutoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(negociacaoProdutoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/negociacaoProdutos").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
                .andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).filtrar(negociacaoProdutoFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Negociacao Produto usando usuario e senha incorretos, retornar status 401")
    public void buscarTokenIncorreto() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, null, null, null, null, Mockito.mock(Produto.class), Mockito.mock(Negociacao.class));
        when(service.detalhar(3L)).thenReturn(negociacaoProduto);
        mockMvc.perform(get("/negociacaoProdutos/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Negociacao Produto com usuario e senha incorretos, retornar status 401")
    public void deletarTokenIncorreto() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, null, null, null, null, Mockito.mock(Produto.class), Mockito.mock(Negociacao.class));
        when(service.deletar(3L)).thenReturn(negociacaoProduto);
        mockMvc.perform(delete("/negociacaoProdutos/3").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
                .andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Negociacao Produto com usuario e senha incorretos, retornar status 401")
    public void criarTokenIncorreto() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, new Produto(1L), new Negociacao(1L));
        when(service.salvar(negociacaoProduto)).thenReturn(negociacaoProduto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(negociacaoProduto);
        mockMvc.perform(post("/negociacaoProdutos").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).atualizar(1L, negociacaoProduto);
    }

    @Test
    @DisplayName("Atualizar Negociacao Produto com usuario e senha incorretos, retornar status 401")
    public void atualizarTokenIncorreto() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, new Produto(1L), new Negociacao(1L));
        when(service.atualizar(1L, negociacaoProduto)).thenReturn(negociacaoProduto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(negociacaoProduto);
        mockMvc.perform(put("/negociacaoProdutos/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).atualizar(1L, negociacaoProduto);
    }

    @Test
    @DisplayName("Listar Negociacao Produtos sem token, retornar status 401")
    public void listamosSemToken() throws Exception {
        NegociacaoProdutoFilter negociacaoProdutoFilter = new NegociacaoProdutoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(negociacaoProdutoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/negociacaoProdutos")).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).filtrar(negociacaoProdutoFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Negociacao Produtos sem token, retornar status 401")
    public void buscarSemToken() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, null, null, null, null, Mockito.mock(Produto.class), Mockito.mock(Negociacao.class));
        when(service.detalhar(3L)).thenReturn(negociacaoProduto);
        mockMvc.perform(get("/negociacaoProdutos/{id}", 3L)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Negociacao Produto sem token, retornar status 401")
    public void deletarSemToken() throws Exception {
        when(service.deletar(3L)).thenReturn(negociacaoProduto);
        mockMvc.perform(delete("/negociacaoProdutos/1")).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Negociacao Produto sem token, retornar status 401")
    public void criarSemToken() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, new Produto(1L), new Negociacao(1L));
        when(service.salvar(negociacaoProduto)).thenReturn(negociacaoProduto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(negociacaoProduto);
        mockMvc.perform(post("/negociacaoProdutos").accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).salvar(negociacaoProduto);
    }

    @Test
    @DisplayName("Atualizar Negociacao Produto sem token, retornar status 401")
    public void atualizarSemToken() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, new Produto(1L), new Negociacao(1L));
        when(service.atualizar(1L, negociacaoProduto)).thenReturn(negociacaoProduto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(negociacaoProduto);
        mockMvc.perform(put("/negociacaoProdutos/{id}", 3L).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).atualizar(3L, negociacaoProduto);
    }

    @Test
    @DisplayName("Criar Negociacao Produto informando uma quantidade null, retornar mensagem de erro e status 400.")
    public void criarQuantidadeNull() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(null, null, new BigDecimal(1), TipoReincidencia.RECORRENTE, new Produto(1L), new Negociacao(1L));
        when(service.salvar(negociacaoProduto)).thenReturn(negociacaoProduto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(negociacaoProduto);
        mockMvc.perform(post("/negociacaoProdutos").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("quantidade")).andExpect(jsonPath("$.fieldMessage").value("A quantidade não pode ser null."));
        verify(service, times(0)).salvar(negociacaoProduto);
    }

    @Test
    @DisplayName("Criar Negociacao Produto informando um valor null, retornar mensagem de erro e status 400.")
    public void criarValorNull() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(null, 1, null, TipoReincidencia.RECORRENTE, new Produto(1L), new Negociacao(1L));
        when(service.salvar(Mockito.any(NegociacaoProduto.class))).thenReturn(negociacaoProduto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(negociacaoProduto);
        mockMvc.perform(post("/negociacaoProdutos").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("valor")).andExpect(jsonPath("$.fieldMessage").value("O valor não pode ser null."));
        verify(service, times(0)).salvar(negociacaoProduto);
    }

    @Test
    @DisplayName("Criar Negociacao Produto informando um total null, retornar mensagem de erro e status 400.")
    public void criarTipoReincidenciaNull() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(null, 1, new BigDecimal(1), null, new Produto(1L), new Negociacao(1L));
        when(service.salvar(Mockito.any(NegociacaoProduto.class))).thenReturn(negociacaoProduto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(negociacaoProduto);
        mockMvc.perform(post("/negociacaoProdutos").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("reincidencia")).andExpect(jsonPath("$.fieldMessage").value("A reicidência não pode ser null."));
        verify(service, times(0)).salvar(negociacaoProduto);
    }

    @Test
    @DisplayName("Criar Negociacao Produto informando um Produto null, retornar mensagem de erro e status 400.")
    public void criarProdutoNull() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(null, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, null, new Negociacao(1L));
        when(service.salvar(Mockito.any(NegociacaoProduto.class))).thenReturn(negociacaoProduto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(negociacaoProduto);
        mockMvc.perform(post("/negociacaoProdutos").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("produto")).andExpect(jsonPath("$.fieldMessage").value("É necessário ter um Produto vinculado."));
        verify(service, times(0)).salvar(negociacaoProduto);
    }

    @Test
    @DisplayName("Criar Negociacao Produto informando uma Negociação null, retornar mensagem de erro e status 400.")
    public void criarNegociacaoNull() throws Exception {
        NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, new Produto(1L), null);
        when(service.salvar(Mockito.any(NegociacaoProduto.class))).thenReturn(negociacaoProduto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(negociacaoProduto);
        mockMvc.perform(post("/negociacaoProdutos").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("negociacao")).andExpect(jsonPath("$.fieldMessage").value("É necessário ter uma Negociação vinculada."));
        verify(service, times(0)).salvar(negociacaoProduto);
    }
}
