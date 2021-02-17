package com.leadsponge.leadsponge.IO.campanhaTestes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.Filter.CampanhaFilter;
import com.leadsponge.IO.services.CampanhaService;
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
@DisplayName("Testando token do EndPoint Campanha")
public class EndPoint {

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

    private final MediaType contentType = new MediaType("application", "json");

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
    }

    @Test
    @DisplayName("Listar Campanha, retornar a Campanha e status 200")
    public void listarCampanhas() throws Exception {
        CampanhaFilter campanhaFilter = new CampanhaFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
        mockMvc.perform(get("/campanhas")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Campanha usando filtro pelo nome, retornar a Campanha e status 200")
    public void listarCampanhasNome() throws Exception {
        CampanhaFilter campanhaFilter = new CampanhaFilter("nome", null);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable)).thenReturn(campanhaPage);
        mockMvc.perform(get("/campanhas?nome=nome")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1)).filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Campanha usando filtro pela descrição, retornar a Campanha e status 200")
    public void listarCampanhasDescricao() throws Exception {
        CampanhaFilter campanhaFilter = new CampanhaFilter(null, "descrição");
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable))
                .thenReturn(campanhaPage);
        mockMvc.perform(get("/campanhas?descricao=descrição")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Campanha usando o id, retornar a Campanha e status 200 sucesso")
    public void buscarCampanha() throws Exception {
        Campanha campanha = new Campanha(3L, "nome", "descrição");
        when(service.detalhar(3L))
                .thenReturn(campanha);
        mockMvc.perform(get("/campanhas/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"))
                .andExpect(jsonPath("$.descricao").value("descrição"));
        verify(service, times(1))
                .detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Campanha, retornar a Campanha e status 200")
    public void deletarCampanha() throws Exception {
        mockMvc.perform(delete("/campanhas/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .deletar(3L);
    }

    @Test
    @DisplayName("Criar Campanha, retornar a Campanha e status 201")
    public void criarCampanha() throws Exception {
        Campanha campanha = new Campanha(null, "campanha nome", "campanha descricao");
        when(service.salvar(campanha))
                .thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(post("/campanhas")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("campanha nome"))
                .andExpect(jsonPath("$.descricao").value("campanha descricao"));
        verify(service, times(1))
                .salvar(Mockito.any(Campanha.class));
    }

    @Test
    @DisplayName("Atualizar Campanha, retornar a Campanha e status 201")
    public void atualizarCampanha() throws Exception {
        Campanha campanha = new Campanha(1L, "nome novo", "descrição nova");
        when(service.atualizar(1L, campanha))
                .thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(put("/campanhas/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome novo"))
                .andExpect(jsonPath("$.descricao").value("descrição nova"));
        verify(service, times(1))
                .atualizar(1L, campanha);
    }

    // Permissão de acesso

    @Test
    @DisplayName("Listar Campanha sem permissão de acesso, retornar a Campanha o status 403")
    public void permissaoListarCampanhas() throws Exception {
        CampanhaFilter campanhaFilter = new CampanhaFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable))
                .thenReturn(campanhaPage);
        mockMvc.perform(get("/campanhas")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Campanha usando filtro pelo nome sem permissão de acesso, retornar o status 403")
    public void permissaoListarCampanhasNome() throws Exception {
        CampanhaFilter campanhaFilter = new CampanhaFilter("nome", null);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable))
                .thenReturn(campanhaPage);
        mockMvc.perform(get("/campanhas?nome=nome")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Campanha usando filtro pela descrição sem permissão de acesso, retornar o status 403")
    public void permissaoListarCampanhasDescricao() throws Exception {
        CampanhaFilter campanhaFilter = new CampanhaFilter(null, "descrição");
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable))
                .thenReturn(campanhaPage);
        mockMvc.perform(get("/campanhas?descricao=descrição")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Campanha usando o id sem permissão de acesso, retornar o status 403")
    public void permissaoBuscarCampanha() throws Exception {
        Campanha campanha = new Campanha(3L, "nome", "descrição");
        when(service.detalhar(3L))
                .thenReturn(campanha);
        mockMvc.perform(get("/campanhas/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Campanha sem permissão de acesso, retornar a Campanha e status 403")
    public void permissaoDeletarCampanha() throws Exception {
        mockMvc.perform(delete("/campanhas/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .deletar(3L);
    }

    @Test
    @DisplayName("Criar Campanha sem permissão de acesso, retornar o status 403")
    public void permissaoCriarCampanha() throws Exception {
        Campanha campanha = new Campanha(null, "campanha nome", "campanha descricao");
        when(service.salvar(campanha))
                .thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(post("/campanhas")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .salvar(Mockito.any(Campanha.class));
    }

    @Test
    @DisplayName("Atualizar Campanha sem permissão de acesso, retornar o status 403")
    public void permissaoAtualizarCampanha() throws Exception {
        Campanha campanha = new Campanha(1L, "nome novo", "descrição nova");
        when(service.atualizar(1L, campanha))
                .thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(put("/campanhas/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .atualizar(1L, campanha);
    }

    // testando o Token de acesso

    @Test
    @DisplayName("Listar Campanhas com usuario e senha incorretos, retornar status 401")
    public void listamosCampanhasTokenIncorreto() throws Exception {
        CampanhaFilter campanhaFilter = new CampanhaFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable))
                .thenReturn(campanhaPage);
        mockMvc.perform(get("/campanhas")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Campanha usando usuario e senha incorretos, retornar status 401")
    public void buscarCampanhasTokenIncorreto() throws Exception {
        Campanha campanha = new Campanha(3L, "campanha nome", "campanha descrição");
        when(service.detalhar(3L))
                .thenReturn(campanha);
        mockMvc.perform(get("/campanhas/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Campanha com usuario e senha incorretos, retornar status 401")
    public void deletarCampanhasTokenIncorreto() throws Exception {
        when(service.deletar(3L))
                .thenReturn(campanha);
        mockMvc.perform(delete("/campanhas/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .deletar(1L);
    }

    @Test
    @DisplayName("Criar Campanha com usuario e senha incorretos, retornar status 401")
    public void criarCampanhasTokenIncorreto() throws Exception {
        Campanha campanha = new Campanha(1L, "nome novo", "descrição nova");
        when(service.salvar(campanha))
                .thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(post("/campanhas")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, campanha);
    }

    @Test
    @DisplayName("Atualizar Campanha com usuario e senha incorretos, retornar status 401")
    public void atualizarCampanhasTokenIncorreto() throws Exception {
        Campanha campanha = new Campanha(1L, "nome novo", "descrição nova");
        when(service.atualizar(1L, campanha))
                .thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(put("/campanhas/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, campanha);
    }

    @Test
    @DisplayName("Listar Campanhas sem token, retornar status 401")
    public void listamosSemToken() throws Exception {
        CampanhaFilter campanhaFilter = new CampanhaFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable))
                .thenReturn(campanhaPage);
        mockMvc.perform(get("/campanhas"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Campanha sem token, retornar status 401")
    public void buscarSemToken() throws Exception {
        Campanha campanha = new Campanha(3L, "campanha nome", "campanha descrição");
        when(service.detalhar(3L))
                .thenReturn(campanha);
        mockMvc.perform(get("/campanhas/{id}", 3L))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Campanha sem token, retornar status 401")
    public void deletarSemToken() throws Exception {
        when(service.deletar(3L))
                .thenReturn(campanha);
        mockMvc.perform(delete("/campanhas/1"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .deletar(1L);
    }

    @Test
    @DisplayName("Criar Campanha sem token, retornar status 401")
    public void criarSemToken() throws Exception {
        Campanha campanha = new Campanha(1L, "nome novo", "descrição nova");
        when(service.salvar(campanha))
                .thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(post("/campanhas")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .salvar(campanha);
    }

    @Test
    @DisplayName("Atualizar Campanha sem token, retornar status 401")
    public void atualizarSemToken() throws Exception {
        Campanha campanha = new Campanha(1L, "nome novo", "descrição nova");
        when(service.atualizar(1L, campanha))
                .thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(put("/campanhas/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .atualizar(1L, campanha);
    }

    // validar a entidade

    @Test
    @DisplayName("Criar Campanha informando nome e descricao, retornar as informações enviadas e Status 201")
    public void criarCampanhaSemInformarUmaDescricao() throws Exception {
        Campanha campanha = new Campanha(null, "campanha nome", "");
        when(service.salvar(Mockito.any(Campanha.class)))
                .thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(post("/campanhas")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(service, times(1))
                .salvar(campanha);
    }

    @Test
    @DisplayName("Atualizar Campanha sem informar uma descricao, retornar as informações e status 201")
    public void atualizarCampanhaSemInformarUmaDescricao() throws Exception {
        Campanha campanhaNovo = new Campanha(1L, "campanha nome", "");
        when(service.atualizar(1L, campanhaNovo)).thenReturn(campanhaNovo);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanhaNovo);
        mockMvc.perform(put("/campanhas/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("campanha nome"))
                .andExpect(jsonPath("$.descricao").value(""));
        verify(service, times(1))
                .atualizar(1L, campanhaNovo);
    }

    @Test
    @DisplayName("Criar Campanha informando um nome null, retornar mensagem de erro e status 400.")
    public void criarNomeNull() throws Exception {
        Campanha campanha = new Campanha(null, null, "campanha descricao");
        when(service.salvar(Mockito.any(Campanha.class))).thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(post("/campanhas")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null"));
        verify(service, times(0))
                .salvar(campanha);
    }

    @Test
    @DisplayName("Atualizar Campanha informando um nome null, retornar mensagem de erro e status 400.")
    public void atualizarNomeNull() throws Exception {
        Campanha campanha = new Campanha(1L, null, "campanha descrição");
        when(service.atualizar(1L, campanha))
                .thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(put("/campanhas/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("nome"))
                .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null"));
        verify(service, times(0))
                .atualizar(1L, campanha);
    }

    @Test
    @DisplayName("Criar Campanha informando um nome vazio, retornar mensagem de erro e status 400.")
    public void criarNomeVazio() throws Exception {
        Campanha campanha = new Campanha(null, "", "campanha descricao");
        when(service.salvar(Mockito.any(Campanha.class))).thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(post("/campanhas")
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
                .salvar(campanha);
    }

    @Test
    @DisplayName("Atualizar Campanha informando um nome vazio, retornar mensagem de erro e status 400.")
    public void atualizarNomeVazio() throws Exception {
        Campanha campanha = new Campanha(1L, "", "campanha descrição");
        when(service.atualizar(1L, campanha)).thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(put("/campanhas/{id}", 1L)
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
                .atualizar(1L, campanha);
    }

    @Test
    @DisplayName("Criar Campanha informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
    public void criarNomeAcima50Caracteres() throws Exception {
        Campanha campanha = new Campanha(null, "Nome campanha, nome campanha, nome campanha e nomes", "campanha descricao");
        when(service.salvar(Mockito.any(Campanha.class))).thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(post("/campanhas")
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
                .salvar(campanha);
    }

    @Test
    @DisplayName("Atualizar Campanha informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
    public void atualizarNomeAcima50Caracteres() throws Exception {
        Campanha campanha = new Campanha(1L, "Nome campanha, nome campanha, nome campanha e nomes", "campanha descrição");
        when(service.atualizar(1L, campanha)).thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(put("/campanhas/{id}", 1L)
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
                .atualizar(1L, campanha);
    }

    @Test
    @DisplayName("Criar Campanha informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
    public void criarNomeAbaixo4Caracteres() throws Exception {
        Campanha campanha = new Campanha(null, "Nom", "campanha descricao");
        when(service.salvar(Mockito.any(Campanha.class)))
                .thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(post("/campanhas")
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
                .salvar(campanha);
    }

    @Test
    @DisplayName("Atualizar Campanha informando um nome abaixo 4 caracteres, retornar mensagem de erro e status 400.")
    public void atualizarNomeAbaixo4Caracteres() throws Exception {
        Campanha campanha = new Campanha(1L, "Nom", "campanha descrição");
        when(service.atualizar(1L, campanha)).thenReturn(campanha);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(campanha);
        mockMvc.perform(put("/campanhas/{id}", 1L)
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
                .atualizar(1L, campanha);
    }
}
