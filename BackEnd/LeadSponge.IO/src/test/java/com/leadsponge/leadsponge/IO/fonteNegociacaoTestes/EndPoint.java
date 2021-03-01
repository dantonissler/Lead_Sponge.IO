package com.leadsponge.leadsponge.IO.fonteNegociacaoTestes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao;
import com.leadsponge.IO.repository.Filter.FonteNegociacaoFilter;
import com.leadsponge.IO.services.FonteNegociacaoService;
import com.leadsponge.leadsponge.IO.Util;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class)
@AutoConfigureMockMvc
@DisplayName("Testando token do EndPoint Fonte Negociação")
public class EndPoint {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FonteNegociacaoService service;

    @Mock
    private Page<FonteNegociacao> page;

    private final MediaType contentType = new MediaType("application", "json");
    private final FonteNegociacaoFilter filter = new FonteNegociacaoFilter();
    private final FonteNegociacaoFilter filterNome = new FonteNegociacaoFilter("nome");
    private final FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
    private ObjectMapper mapper;

    @BeforeEach
    void init() {
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Listar Fonte Negociacaos, retornar a Fonte Negociacaos e status 200")
    public void listar() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(filter, pageable)).thenReturn(page);
        mockMvc.perform(get("/fontes")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(filter, pageable);
    }

    @Test
    @DisplayName("Listar FonteNegociacaos usando filtro pelo nome, retornar a FonteNegociacaos e status 200")
    public void listarNome() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(filterNome, pageable)).thenReturn(page);
        mockMvc.perform(get("/fontes?nome=nome")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(filterNome, pageable);
    }

    @Test
    @DisplayName("Buscar Fonte Negociacaos usando o id, retornar a Fonte Negociacaos e status 200 sucesso")
    public void buscar() throws Exception {
        when(service.detalhar(1L)).thenReturn(fonteNegociacao);
        mockMvc.perform(get("/fontes/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"));
        verify(service, times(1)).detalhar(1L);
    }

    @Test
    @DisplayName("Deletar Fonte Negociacaos, retornar a Fonte Negociacao e status 200")
    public void deletar() throws Exception {
        mockMvc.perform(delete("/fontes/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Fonte Negociacaos, retornar a Fonte Negociacaos e status 201")
    public void criar() throws Exception {
        when(service.salvar(fonteNegociacao)).thenReturn(fonteNegociacao);
        String jsonString = mapper.writeValueAsString(fonteNegociacao);
        mockMvc.perform(post("/fontes")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"));
        verify(service, times(1)).salvar(Mockito.any(FonteNegociacao.class));
    }

    @Test
    @DisplayName("Atualizar Fonte Negociacaos, retornar a Fonte Negociacaos e status 201")
    public void atualizar() throws Exception {
        when(service.atualizar(1L, fonteNegociacao)).thenReturn(fonteNegociacao);
        String jsonString = mapper.writeValueAsString(fonteNegociacao);
        mockMvc.perform(put("/fontes/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"));
        verify(service, times(1))
                .atualizar(1L, fonteNegociacao);
    }

    // Permissão de acesso

    @Test
    @DisplayName("Listar Fonte Negociacaos sem permissão de acesso, retornar o status 403")
    public void permissaoListar() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(filter, pageable)).thenReturn(page);
        mockMvc.perform(get("/fontes")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(filter, pageable);
    }

    @Test
    @DisplayName("Listar Fonte Negociacaos usando filtro pelo nome sem permissão de acesso, retornar o status 403")
    public void permissaoListarsNome() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(filterNome, pageable)).thenReturn(page);
        mockMvc.perform(get("/fontes?nome=nome")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(filterNome, pageable);
    }

    @Test
    @DisplayName("Buscar Fonte Negociacaos usando o id sem permissão de acesso, retornar o status 403")
    public void permissaoBuscar() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
        when(service.detalhar(1L)).thenReturn(fonteNegociacao);
        mockMvc.perform(get("/fontes/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).detalhar(1L);
    }

    @Test
    @DisplayName("Deletar Fonte Negociacao sem permissão de acesso, retornar a Fonte Negociacao e status 403")
    public void permissaoDeletar() throws Exception {
        mockMvc.perform(delete("/fontes/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Fonte Negociacao sem permissão de acesso, retornar o status 403")
    public void permissaoCriar() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
        when(service.salvar(fonteNegociacao)).thenReturn(fonteNegociacao);
        String jsonString = mapper.writeValueAsString(fonteNegociacao);
        mockMvc.perform(post("/fontes")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).salvar(Mockito.any(FonteNegociacao.class));
    }

    @Test
    @DisplayName("Atualizar Fonte Negociacao sem permissão de acesso, retornar o status 403")
    public void permissaoAtualizar() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
        when(service.atualizar(1L, fonteNegociacao)).thenReturn(fonteNegociacao);
        String jsonString = mapper.writeValueAsString(fonteNegociacao);
        mockMvc.perform(put("/fontes/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .atualizar(1L, fonteNegociacao);
    }

    // testando o Token de acesso

    @Test
    @DisplayName("Listar Fonte Negociacao com usuario e senha incorretos, retornar status 401")
    public void listamosTokenIncorreto() throws Exception {
        FonteNegociacaoFilter fonteNegociacaoFilter = new FonteNegociacaoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(fonteNegociacaoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/fontes")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .filtrar(fonteNegociacaoFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Fonte Negociacao usando usuario e senha incorretos, retornar status 401")
    public void buscarTokenIncorreto() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
        when(service.detalhar(1L)).thenReturn(fonteNegociacao);
        mockMvc.perform(get("/fontes/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).detalhar(1L);
    }

    @Test
    @DisplayName("Deletar Fonte Negociacao com usuario e senha incorretos, retornar status 401")
    public void deletarTokenIncorreto() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
        when(service.deletar(1L)).thenReturn(fonteNegociacao);
        mockMvc.perform(delete("/fontes/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Fonte Negociacao com usuario e senha incorretos, retornar status 401")
    public void criarTokenIncorreto() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
        when(service.salvar(fonteNegociacao)).thenReturn(fonteNegociacao);
        String jsonString = mapper.writeValueAsString(fonteNegociacao);
        mockMvc.perform(post("/fontes")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, fonteNegociacao);
    }

    @Test
    @DisplayName("Atualizar Fonte Negociacao com usuario e senha incorretos, retornar status 401")
    public void atualizarTokenIncorreto() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
        when(service.atualizar(1L, fonteNegociacao)).thenReturn(fonteNegociacao);
        String jsonString = mapper.writeValueAsString(fonteNegociacao);
        mockMvc.perform(put("/fontes/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, fonteNegociacao);
    }

    @Test
    @DisplayName("Listar Fonte Negociacaos sem token, retornar status 401")
    public void listamosSemToken() throws Exception {
        FonteNegociacaoFilter fonteNegociacaoFilter = new FonteNegociacaoFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(fonteNegociacaoFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/fontes"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .filtrar(fonteNegociacaoFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Fonte Negociacaos sem token, retornar status 401")
    public void buscarSemToken() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
        when(service.detalhar(1L)).thenReturn(fonteNegociacao);
        mockMvc.perform(get("/fontes/{id}", 1L))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).detalhar(1L);
    }

    @Test
    @DisplayName("Deletar Fonte Negociacao sem token, retornar status 401")
    public void deletarSemToken() throws Exception {
        when(service.deletar(1L)).thenReturn(fonteNegociacao);
        mockMvc.perform(delete("/fontes/1"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Fonte Negociacao sem token, retornar status 401")
    public void criarSemToken() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
        when(service.salvar(fonteNegociacao)).thenReturn(fonteNegociacao);
        String jsonString = mapper.writeValueAsString(fonteNegociacao);
        mockMvc.perform(post("/fontes")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).salvar(fonteNegociacao);
    }

    @Test
    @DisplayName("Atualizar Fonte Negociacao sem token, retornar status 401")
    public void atualizarSemToken() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
        when(service.atualizar(1L, fonteNegociacao)).thenReturn(fonteNegociacao);
        String jsonString = mapper.writeValueAsString(fonteNegociacao);
        mockMvc.perform(put("/fontes/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .atualizar(1L, fonteNegociacao);
    }

    // validar a entidade ate aqui

    @Test
    @DisplayName("Criar Fonte Negociacao informando nome e descricao, retornar as informações enviadas e Status 201")
    public void criarSemInformarUmaDescricao() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
        when(service.salvar(Mockito.any(FonteNegociacao.class))).thenReturn(fonteNegociacao);
        String jsonString = mapper.writeValueAsString(fonteNegociacao);
        mockMvc.perform(post("/fontes")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(service, times(1)).salvar(fonteNegociacao);
    }

    @Test
    @DisplayName("Atualizar Fonte Negociacao sem informar uma descricao, retornar as informações e status 201")
    public void atualizarSemInformarUmaDescricao() throws Exception {
        FonteNegociacao fonteNegociacaoNovo = new FonteNegociacao(1L, "nome", null);
        when(service.atualizar(1L, fonteNegociacaoNovo)).thenReturn(fonteNegociacaoNovo);
        String jsonString = mapper.writeValueAsString(fonteNegociacaoNovo);
        mockMvc.perform(put("/fontes/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"))
                .andExpect(jsonPath("$.cargo").value(""));
        verify(service, times(1))
                .atualizar(1L, fonteNegociacaoNovo);
    }

    @Test
    @DisplayName("Atualizar Fonte Negociacao informando um nome null, retornar mensagem de erro e status 400.")
    public void atualizarNomeNull() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, null, null);
        when(service.atualizar(1L, fonteNegociacao)).thenReturn(fonteNegociacao);
        String jsonString = mapper.writeValueAsString(fonteNegociacao);
        mockMvc.perform(put("/fontes/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("Não pode ser null"))
                .andExpect(jsonPath("$.field").value("nome"));
        verify(service, times(0))
                .atualizar(1L, fonteNegociacao);
    }

    @Test
    @DisplayName("Criar Fonte Negociacao informando um nome vazio, retornar mensagem de erro e status 400.")
    public void criarNomeVazio() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "", null);
        when(service.salvar(Mockito.any(FonteNegociacao.class))).thenReturn(fonteNegociacao);
        String jsonString = mapper.writeValueAsString(fonteNegociacao);
        mockMvc.perform(post("/fontes")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
        verify(service, times(0)).salvar(fonteNegociacao);
    }

    @Test
    @DisplayName("Criar Fonte Negociacao informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
    public void criarNomeAcima50Caracteres() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(null, "nome nome nome nome nome nome nome nome nome nome nome nome", null);
        when(service.salvar(Mockito.any(FonteNegociacao.class))).thenReturn(fonteNegociacao);
        String jsonString = mapper.writeValueAsString(fonteNegociacao);
        mockMvc.perform(post("/fontes")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
        verify(service, times(0)).salvar(fonteNegociacao);
    }

    @Test
    @DisplayName("Criar Fonte Negociacao informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
    public void criarNomeAbaixo4Caracteres() throws Exception {
        FonteNegociacao fonteNegociacao = new FonteNegociacao(null, "nom", null);
        when(service.salvar(Mockito.any(FonteNegociacao.class))).thenReturn(fonteNegociacao);
        String jsonString = mapper.writeValueAsString(fonteNegociacao);
        mockMvc.perform(post("/fontes")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
        verify(service, times(0)).salvar(fonteNegociacao);
    }
}
