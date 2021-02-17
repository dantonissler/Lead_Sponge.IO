package com.leadsponge.leadsponge.IO.telefoneTestes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.models.telefone.Telefone;
import com.leadsponge.IO.models.telefone.TipoTelefone;
import com.leadsponge.IO.repository.Filter.TelefoneFilter;
import com.leadsponge.IO.services.TelefoneService;
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
@DisplayName("Testando token do EndPoint Telefone")
public class EndPoint {

    private final MediaType contentType = new MediaType("application", "json");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    @MockBean
    private TelefoneService service;
    @Mock
    private Telefone telefone;
    @Mock
    private Page<Telefone> page;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
    }

    @Test
    @DisplayName("Listar Telefones, retornar a Telefones e status 200")
    public void listar() throws Exception {
        TelefoneFilter telefoneFilter = new TelefoneFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(telefoneFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/telefones")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(telefoneFilter, pageable);
    }

    @Test
    @DisplayName("Listar Telefones usando filtro pelo numero, retornar a Telefones e status 200")
    public void listarnumero() throws Exception {
        TelefoneFilter telefoneFilter = new TelefoneFilter("99999999999", null);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(telefoneFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/telefones?numero=99999999999")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(telefoneFilter, pageable);
    }

    @Test
    @DisplayName("Listar Telefones usando filtro pela tipo, retornar a Telefones e status 200")
    public void listartipo() throws Exception {
        TelefoneFilter telefoneFilter = new TelefoneFilter(null, TipoTelefone.COMERCIAL);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(telefoneFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/telefones?tipo={tipo}",TipoTelefone.COMERCIAL)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(telefoneFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Telefones usando o id, retornar a Telefones e status 200 sucesso")
    public void buscar() throws Exception {
        Telefone telefone = new Telefone(3L, "99999999999", TipoTelefone.COMERCIAL, new Contato(1L));
        when(service.detalhar(3L)).thenReturn(telefone);
        mockMvc.perform(get("/telefones/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.numero").value("99999999999"))
                .andExpect(jsonPath("$.tipo").value("COMERCIAL"));
        verify(service, times(1)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Telefones, retornar a Telefone e status 200")
    public void deletar() throws Exception {
        mockMvc.perform(delete("/telefones/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Telefones, retornar a Telefones e status 201")
    public void criar() throws Exception {
        Telefone telefone = new Telefone(3L, "99999999999", TipoTelefone.COMERCIAL, new Contato(1L));
        when(service.salvar(telefone)).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(post("/telefones")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.numero").value("99999999999"))
                .andExpect(jsonPath("$.tipo").value("COMERCIAL"));
        verify(service, times(1)).salvar(Mockito.any(Telefone.class));
    }

    @Test
    @DisplayName("Atualizar Telefones, retornar a Telefones e status 201")
    public void atualizar() throws Exception {
        Telefone telefone = new Telefone(3L, "99999999999", TipoTelefone.COMERCIAL, new Contato(1L));
        when(service.atualizar(1L, telefone)).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(put("/telefones/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.numero").value("99999999999"))
                .andExpect(jsonPath("$.tipo").value("COMERCIAL"));
        verify(service, times(1))
                .atualizar(1L, telefone);
    }

    // Permissão de acesso

    @Test
    @DisplayName("Listar Telefones sem permissão de acesso, retornar o status 403")
    public void permissaoListar() throws Exception {
        TelefoneFilter telefoneFilter = new TelefoneFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(telefoneFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/telefones")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(telefoneFilter, pageable);
    }

    @Test
    @DisplayName("Listar Telefones usando filtro pelo numero sem permissão de acesso, retornar o status 403")
    public void permissaoListarsNumero() throws Exception {
        TelefoneFilter telefoneFilter = new TelefoneFilter("99999999999", null);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(telefoneFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/telefones?numero=99999999999")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(telefoneFilter, pageable);
    }

    @Test
    @DisplayName("Listar Telefones usando filtro pela tipo sem permissão de acesso, retornar o status 403")
    public void permissaoListarTipo() throws Exception {
        TelefoneFilter campanhaFilter = new TelefoneFilter(null, TipoTelefone.COMERCIAL);
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/telefones?tipo={tipo}",TipoTelefone.COMERCIAL)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Telefones usando o id sem permissão de acesso, retornar o status 403")
    public void permissaoBuscar() throws Exception {
        Telefone telefone = new Telefone(3L, "numero", TipoTelefone.COMERCIAL, new Contato(1L));
        when(service.detalhar(3L)).thenReturn(telefone);
        mockMvc.perform(get("/telefones/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Telefone sem permissão de acesso, retornar a Telefone e status 403")
    public void permissaoDeletar() throws Exception {
        mockMvc.perform(delete("/telefones/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Telefone sem permissão de acesso, retornar o status 403")
    public void permissaoCriar() throws Exception {
        Telefone telefone = new Telefone(3L, "99999999999", TipoTelefone.COMERCIAL, new Contato(1L));
        when(service.salvar(telefone)).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(post("/telefones")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).salvar(Mockito.any(Telefone.class));
    }

    @Test
    @DisplayName("Atualizar Telefone sem permissão de acesso, retornar o status 403")
    public void permissaoAtualizar() throws Exception {
        Telefone telefone = new Telefone(3L, "99999999999", TipoTelefone.COMERCIAL, new Contato(1L));
        when(service.atualizar(1L, telefone)).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(put("/telefones/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .atualizar(1L, telefone);
    }

    // testando o Token de acesso

    @Test
    @DisplayName("Listar Telefone com usuario e senha incorretos, retornar status 401")
    public void listamosTokenIncorreto() throws Exception {
        TelefoneFilter telefoneFilter = new TelefoneFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(telefoneFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/telefones")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .filtrar(telefoneFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Telefone usando usuario e senha incorretos, retornar status 401")
    public void buscarTokenIncorreto() throws Exception {
        Telefone telefone = new Telefone(3L, "99999999999", TipoTelefone.COMERCIAL, new Contato(1L));
        when(service.detalhar(3L)).thenReturn(telefone);
        mockMvc.perform(get("/telefones/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Telefone com usuario e senha incorretos, retornar status 401")
    public void deletarTokenIncorreto() throws Exception {
        Telefone telefone = new Telefone(3L, "99999999999", TipoTelefone.COMERCIAL, new Contato(1L));
        when(service.deletar(3L)).thenReturn(telefone);
        mockMvc.perform(delete("/telefones/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Telefone com usuario e senha incorretos, retornar status 401")
    public void criarTokenIncorreto() throws Exception {
        Telefone telefone = new Telefone(3L, "99999999999", TipoTelefone.COMERCIAL, new Contato(1L));
        when(service.salvar(telefone)).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(post("/telefones")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, telefone);
    }

    @Test
    @DisplayName("Atualizar Telefone com usuario e senha incorretos, retornar status 401")
    public void atualizarTokenIncorreto() throws Exception {
        Telefone telefone = new Telefone(3L, "99999999999", TipoTelefone.COMERCIAL, new Contato(1L));
        when(service.atualizar(1L, telefone)).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(put("/telefones/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, telefone);
    }

    @Test
    @DisplayName("Listar Telefones sem token, retornar status 401")
    public void listamosSemToken() throws Exception {
        TelefoneFilter telefoneFilter = new TelefoneFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(telefoneFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/telefones"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .filtrar(telefoneFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Telefones sem token, retornar status 401")
    public void buscarSemToken() throws Exception {
        Telefone telefone = new Telefone(3L, "99999999999", TipoTelefone.COMERCIAL, new Contato(1L));
        when(service.detalhar(3L)).thenReturn(telefone);
        mockMvc.perform(get("/telefones/{id}", 3L))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Telefone sem token, retornar status 401")
    public void deletarSemToken() throws Exception {
        when(service.deletar(3L)).thenReturn(telefone);
        mockMvc.perform(delete("/telefones/1"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Telefone sem token, retornar status 401")
    public void criarSemToken() throws Exception {
        Telefone telefone = new Telefone(3L, "99999999999", TipoTelefone.COMERCIAL, new Contato(1L));
        when(service.salvar(telefone)).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(post("/telefones")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).salvar(telefone);
    }

    @Test
    @DisplayName("Atualizar Telefone sem token, retornar status 401")
    public void atualizarSemToken() throws Exception {
        Telefone telefone = new Telefone(3L, "99999999999", TipoTelefone.COMERCIAL, new Contato(1L));
        when(service.atualizar(1L, telefone)).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(put("/telefones/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .atualizar(1L, telefone);
    }

    // validar a entidade ate aqui

    @Test
    @DisplayName("Criar Telefone informando numero e descricao, retornar as informações enviadas e Status 201")
    public void criarSemInformarUmaDescricao() throws Exception {
        Telefone telefone = new Telefone(3L, "99999999999", null, new Contato(1L));
        when(service.salvar(Mockito.any(Telefone.class))).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(post("/telefones")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(service, times(1)).salvar(telefone);
    }

    @Test
    @DisplayName("Atualizar Telefone sem informar uma descricao, retornar as informações e status 201")
    public void atualizarSemInformarUmaDescricao() throws Exception {
        Telefone telefoneNovo = new Telefone(3L, "99999999999", null, new Contato(1L));
        when(service.atualizar(1L, telefoneNovo)).thenReturn(telefoneNovo);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefoneNovo);
        mockMvc.perform(put("/telefones/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.numero").value("99999999999"));
        verify(service, times(1))
                .atualizar(1L, telefoneNovo);
    }

    @Test
    @DisplayName("Criar Telefone informando um numero null, retornar mensagem de erro e status 400.")
    public void criarnumeroNull() throws Exception {
        Telefone telefone = new Telefone(3L, null, null, new Contato(1L));
        when(service.salvar(Mockito.any(Telefone.class))).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(post("/telefones")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("numero"))
                .andExpect(jsonPath("$.fieldMessage").value("O número não pode ser null."));
        verify(service, times(0)).salvar(telefone);
    }

    @Test
    @DisplayName("Atualizar Telefone informando um numero null, retornar mensagem de erro e status 400.")
    public void atualizarnumeroNull() throws Exception {
        Telefone telefone = new Telefone(3L, null, null, new Contato(1L));
        when(service.atualizar(1L, telefone)).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(put("/telefones/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("numero"))
                .andExpect(jsonPath("$.fieldMessage").value("O número não pode ser null."));
        verify(service, times(0))
                .atualizar(1L, telefone);
    }

    @Test
    @DisplayName("Criar Telefone informando um numero vazio, retornar mensagem de erro e status 400.")
    public void criarnumeroVazio() throws Exception {
        Telefone telefone = new Telefone(3L, "", null, new Contato(1L));
        when(service.salvar(Mockito.any(Telefone.class))).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(post("/telefones")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("numero"))
                .andExpect(jsonPath("$.fieldMessage").value("O número deve ter 11 caracteres."));
        verify(service, times(0)).salvar(telefone);
    }

    @Test
    @DisplayName("Atualizar Telefone informando um numero vazio, retornar mensagem de erro e status 400.")
    public void atualizarnumeroVazio() throws Exception {
        Telefone telefone = new Telefone(3L, "", null, new Contato(1L));
        when(service.atualizar(1L, telefone)).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(put("/telefones/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("numero"))
                .andExpect(jsonPath("$.fieldMessage").value("O número deve ter 11 caracteres."));
        verify(service, times(0))
                .atualizar(1L, telefone);
    }

    @Test
    @DisplayName("Criar Telefone informando um numero acima de 11 caracteres, retornar mensagem de erro e status 400.")
    public void criarNumeroAcima11Caracteres() throws Exception {
        Telefone telefone = new Telefone(3L, "999999999999", null, new Contato(1L));
        when(service.salvar(Mockito.any(Telefone.class))).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(post("/telefones")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("numero"))
                .andExpect(jsonPath("$.fieldMessage").value("O número deve ter 11 caracteres."));
        verify(service, times(0)).salvar(telefone);
    }

    @Test
    @DisplayName("Atualizar Telefone informando um numero acima de 11 caracteres, retornar mensagem de erro e status 400.")
    public void atualizarNumeroAcima11Caracteres() throws Exception {
        Telefone telefone = new Telefone(3L, "999999999999", null, new Contato(1L));
        when(service.atualizar(1L, telefone)).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(put("/telefones/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("numero"))
                .andExpect(jsonPath("$.fieldMessage").value("O número deve ter 11 caracteres."));
        verify(service, times(0))
                .atualizar(1L, telefone);
    }

    @Test
    @DisplayName("Criar Telefone informando um numero abaixo de 11 caracteres, retornar mensagem de erro e status 400.")
    public void criarNumeroAbaixo1Caracteres() throws Exception {
        Telefone telefone = new Telefone(3L, "9999999999", null, new Contato(1L));
        when(service.salvar(Mockito.any(Telefone.class))).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(post("/telefones")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("numero"))
                .andExpect(jsonPath("$.fieldMessage").value("O número deve ter 11 caracteres."));
        verify(service, times(0)).salvar(telefone);
    }

    @Test
    @DisplayName("Atualizar Telefone informando um numero abaixo 11 caracteres, retornar mensagem de erro e status 400.")
    public void atualizarNumeroAbaixo11Caracteres() throws Exception {
        Telefone telefone = new Telefone(3L, "9999999999", null, new Contato(1L));
        when(service.atualizar(1L, telefone)).thenReturn(telefone);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(telefone);
        mockMvc.perform(put("/telefones/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("numero"))
                .andExpect(jsonPath("$.fieldMessage").value("O número deve ter 11 caracteres."));
        verify(service, times(0))
                .atualizar(1L, telefone);
    }
}
