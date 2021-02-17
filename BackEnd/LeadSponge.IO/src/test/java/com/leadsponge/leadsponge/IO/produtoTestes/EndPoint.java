package com.leadsponge.leadsponge.IO.produtoTestes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.produto.Produto;
import com.leadsponge.IO.repository.Filter.ProdutoFilter;
import com.leadsponge.IO.services.ProdutoService;
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
@DisplayName("Testando token do EndPoint Produto")
public class EndPoint {

    private final MediaType contentType = new MediaType("application", "json");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    @MockBean
    private ProdutoService service;
    @Mock
    private Produto produto;
    @Mock
    private Page<Produto> page;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain).build();
    }

    @Test
    @DisplayName("Listar Produtos, retornar a Produtos e status 200")
    public void listar() throws Exception {
        ProdutoFilter produtoFilter = new ProdutoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(produtoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/produtos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(produtoFilter, pageable);
    }

    @Test
    @DisplayName("Listar Produtos usando filtro pelo nome, retornar a Produtos e status 200")
    public void listarNome() throws Exception {
        ProdutoFilter produtoFilter = new ProdutoFilter("nome", null);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(produtoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/produtos?nome=nome")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(produtoFilter, pageable);
    }

    @Test
    @DisplayName("Listar Produtos usando filtro pela descricao, retornar a Produtos e status 200")
    public void listarDescricao() throws Exception {
        ProdutoFilter produtoFilter = new ProdutoFilter(null, "descricao");
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(produtoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/produtos?descricao=descricao")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(produtoFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Produtos usando o id, retornar a Produtos e status 200 sucesso")
    public void buscar() throws Exception {
        Produto produto = new Produto(3L, "nome", "descricao", null, null, null);
        when(service.detalhar(3L)).thenReturn(produto);
        mockMvc.perform(get("/produtos/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"))
                .andExpect(jsonPath("$.descricao").value("descricao"));
        verify(service, times(1)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Produtos, retornar a Produto e status 200")
    public void deletar() throws Exception {
        mockMvc.perform(delete("/produtos/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Produtos, retornar a Produtos e status 201")
    public void criar() throws Exception {
        Produto produto = new Produto(3L, "nome", "descricao", null, null, null);
        when(service.salvar(produto)).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(post("/produtos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"))
                .andExpect(jsonPath("$.descricao").value("descricao"));
        verify(service, times(1)).salvar(Mockito.any(Produto.class));
    }

    @Test
    @DisplayName("Atualizar Produtos, retornar a Produtos e status 201")
    public void atualizar() throws Exception {
        Produto produto = new Produto(3L, "nome", "descricao", null, null, null);
        when(service.atualizar(1L, produto)).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(put("/produtos/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"))
                .andExpect(jsonPath("$.descricao").value("descricao"));
        verify(service, times(1))
                .atualizar(1L, produto);
    }

    // Permissão de acesso

    @Test
    @DisplayName("Listar Produtos sem permissão de acesso, retornar o status 403")
    public void permissaoListar() throws Exception {
        ProdutoFilter produtoFilter = new ProdutoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(produtoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/produtos")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(produtoFilter, pageable);
    }

    @Test
    @DisplayName("Listar Produtos usando filtro pelo nome sem permissão de acesso, retornar o status 403")
    public void permissaoListarsNome() throws Exception {
        ProdutoFilter produtoFilter = new ProdutoFilter("nome", null);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(produtoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/produtos?nome=nome")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(produtoFilter, pageable);
    }

    @Test
    @DisplayName("Listar Produtos usando filtro pela descricao sem permissão de acesso, retornar o status 403")
    public void permissaoListarDescricao() throws Exception {
        ProdutoFilter campanhaFilter = new ProdutoFilter(null, "descricao");
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/produtos?descricao=descricao")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Produtos usando o id sem permissão de acesso, retornar o status 403")
    public void permissaoBuscar() throws Exception {
        Produto produto = new Produto(3L, "nome", "descricao", null, null, null);
        when(service.detalhar(3L)).thenReturn(produto);
        mockMvc.perform(get("/produtos/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Produto sem permissão de acesso, retornar a Produto e status 403")
    public void permissaoDeletar() throws Exception {
        mockMvc.perform(delete("/produtos/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Produto sem permissão de acesso, retornar o status 403")
    public void permissaoCriar() throws Exception {
        Produto produto = new Produto(1L, "nome", "descricao", null, null, null);
        when(service.salvar(produto)).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(post("/produtos")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).salvar(Mockito.any(Produto.class));
    }

    @Test
    @DisplayName("Atualizar Produto sem permissão de acesso, retornar o status 403")
    public void permissaoAtualizar() throws Exception {
        Produto produto = new Produto(1L, "nome", "descricao", null, null, null);
        when(service.atualizar(1L, produto)).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(put("/produtos/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .atualizar(1L, produto);
    }

    // testando o Token de acesso

    @Test
    @DisplayName("Listar Produto com usuario e senha incorretos, retornar status 401")
    public void listamosTokenIncorreto() throws Exception {
        ProdutoFilter produtoFilter = new ProdutoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(produtoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/produtos")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .filtrar(produtoFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Produto usando usuario e senha incorretos, retornar status 401")
    public void buscarTokenIncorreto() throws Exception {
        Produto produto = new Produto(1L, "nome", "descricao", null, null, null);
        when(service.detalhar(3L)).thenReturn(produto);
        mockMvc.perform(get("/produtos/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Produto com usuario e senha incorretos, retornar status 401")
    public void deletarTokenIncorreto() throws Exception {
        Produto produto = new Produto(1L, "nome", "descricao", null, null, null);
        when(service.deletar(3L)).thenReturn(produto);
        mockMvc.perform(delete("/produtos/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Produto com usuario e senha incorretos, retornar status 401")
    public void criarTokenIncorreto() throws Exception {
        Produto produto = new Produto(1L, "nome", "descricao", null, null, null);
        when(service.salvar(produto)).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(post("/produtos")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, produto);
    }

    @Test
    @DisplayName("Atualizar Produto com usuario e senha incorretos, retornar status 401")
    public void atualizarTokenIncorreto() throws Exception {
        Produto produto = new Produto(1L, "nome", "descricao", null, null, null);
        when(service.atualizar(1L, produto)).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(put("/produtos/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, produto);
    }

    @Test
    @DisplayName("Listar Produtos sem token, retornar status 401")
    public void listamosSemToken() throws Exception {
        ProdutoFilter produtoFilter = new ProdutoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(produtoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/produtos"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .filtrar(produtoFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Produtos sem token, retornar status 401")
    public void buscarSemToken() throws Exception {
        Produto produto = new Produto(1L, "nome", "descricao", null, null, null);
        when(service.detalhar(3L)).thenReturn(produto);
        mockMvc.perform(get("/produtos/{id}", 3L))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Produto sem token, retornar status 401")
    public void deletarSemToken() throws Exception {
        when(service.deletar(3L)).thenReturn(produto);
        mockMvc.perform(delete("/produtos/1"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Produto sem token, retornar status 401")
    public void criarSemToken() throws Exception {
        Produto produto = new Produto(1L, "nome", "descricao", null, null, null);
        when(service.salvar(produto)).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(post("/produtos")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).salvar(produto);
    }

    @Test
    @DisplayName("Atualizar Produto sem token, retornar status 401")
    public void atualizarSemToken() throws Exception {
        Produto produto = new Produto(1L, "nome", "descricao", null, null, null);
        when(service.atualizar(1L, produto)).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(put("/produtos/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .atualizar(1L, produto);
    }

    // validar a entidade ate aqui

    @Test
    @DisplayName("Criar Produto informando nome e descricao, retornar as informações enviadas e Status 201")
    public void criarSemInformarUmaDescricao() throws Exception {
        Produto produto = new Produto(1L, "nome", "descricao", null, null, null);
        when(service.salvar(Mockito.any(Produto.class))).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(post("/produtos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(service, times(1)).salvar(produto);
    }

    @Test
    @DisplayName("Atualizar Produto sem informar uma descricao, retornar as informações e status 201")
    public void atualizarSemInformarUmaDescricao() throws Exception {
        Produto produtoNovo = new Produto(1L, "nome", "", null, null, null);
        when(service.atualizar(1L, produtoNovo)).thenReturn(produtoNovo);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produtoNovo);
        mockMvc.perform(put("/produtos/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"))
                .andExpect(jsonPath("$.descricao").value(""));
        verify(service, times(1))
                .atualizar(1L, produtoNovo);
    }

    @Test
    @DisplayName("Criar Produto informando um nome null, retornar mensagem de erro e status 400.")
    public void criarNomeNull() throws Exception {
        Produto produto = new Produto(1L, null, "descricao", null, null, null);
        when(service.salvar(Mockito.any(Produto.class))).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(post("/produtos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("Não pode ser null"));
        verify(service, times(0)).salvar(produto);
    }

    @Test
    @DisplayName("Atualizar Produto informando um nome null, retornar mensagem de erro e status 400.")
    public void atualizarNomeNull() throws Exception {
        Produto produto = new Produto(1L, null, "descricao", null, null, null);
        when(service.atualizar(1L, produto)).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(put("/produtos/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("Não pode ser null"));
        verify(service, times(0))
                .atualizar(1L, produto);
    }

    @Test
    @DisplayName("Criar Produto informando um nome vazio, retornar mensagem de erro e status 400.")
    public void criarNomeVazio() throws Exception {
        Produto produto = new Produto(1L, "", "descricao", null, null, null);
        when(service.salvar(Mockito.any(Produto.class))).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(post("/produtos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
        verify(service, times(0)).salvar(produto);
    }

    @Test
    @DisplayName("Atualizar Produto informando um nome vazio, retornar mensagem de erro e status 400.")
    public void atualizarNomeVazio() throws Exception {
        Produto produto = new Produto(1L, "", "descricao", null, null, null);
        when(service.atualizar(1L, produto)).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(put("/produtos/{id}", 1L)
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
                .atualizar(1L, produto);
    }

    @Test
    @DisplayName("Criar Produto informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
    public void criarNomeAcima50Caracteres() throws Exception {
        Produto produto = new Produto(null, "nome nome nome nome nome nome nome nome nome nome nome nome", "descricao", null, null, null);
        when(service.salvar(Mockito.any(Produto.class))).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(post("/produtos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
        verify(service, times(0)).salvar(produto);
    }

    @Test
    @DisplayName("Atualizar Produto informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
    public void atualizarNomeAcima50Caracteres() throws Exception {
        Produto produto = new Produto(null, "nome nome nome nome nome nome nome nome nome nome nome nome", "descricao", null, null, null);
        when(service.atualizar(1L, produto)).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(put("/produtos/{id}", 1L)
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
                .atualizar(1L, produto);
    }

    @Test
    @DisplayName("Criar Produto informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
    public void criarNomeAbaixo4Caracteres() throws Exception {
        Produto produto = new Produto(null, "nom", "descricao", null, null, null);
        when(service.salvar(Mockito.any(Produto.class))).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(post("/produtos")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
        verify(service, times(0)).salvar(produto);
    }

    @Test
    @DisplayName("Atualizar Produto informando um nome abaixo 4 caracteres, retornar mensagem de erro e status 400.")
    public void atualizarNomeAbaixo4Caracteres() throws Exception {
        Produto produto = new Produto(null, "nom", "descricao", null, null, null);
        when(service.atualizar(1L, produto)).thenReturn(produto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);
        mockMvc.perform(put("/produtos/{id}", 1L)
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
                .atualizar(1L, produto);
    }
}
