package com.leadsponge.leadsponge.IO.emailTestes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.email.Email;
import com.leadsponge.IO.repository.Filter.EmailFilter;
import com.leadsponge.IO.services.EmailService;
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
@DisplayName("Testando token do EndPoint Email")
public class EndPoint {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @MockBean
    private EmailService service;

    @Mock
    private Email email;

    @Mock
    private Page<Email> page;

    private final MediaType contentType = new MediaType("application", "json");

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    @Test
    @DisplayName("Listar Emails, retornar a Emails e status 200")
    public void listar() throws Exception {
        EmailFilter emailFilter = new EmailFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(emailFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/emails")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(emailFilter, pageable);
    }

    @Test
    @DisplayName("Listar Emails usando filtro pelo email, retornar a Emails e status 200")
    public void listarEmail() throws Exception {
        EmailFilter emailFilter = new EmailFilter("email@gmail.com");
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(emailFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/emails?email=email@gmail.com")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(emailFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Emails usando o id, retornar a Emails e status 200 sucesso")
    public void buscar() throws Exception {
        Email email = new Email(3L, "email@gmail.com", null);
        when(service.detalhar(3L)).thenReturn(email);
        mockMvc.perform(get("/emails/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.email").value("email@gmail.com"));
        verify(service, times(1))
                .detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Emails, retornar a Email e status 200")
    public void deletar() throws Exception {
        mockMvc.perform(delete("/emails/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .deletar(3L);
    }

    @Test
    @DisplayName("Criar Emails, retornar a Emails e status 201")
    public void criar() throws Exception {
        Email email = new Email(3L, "email@gmail.com", null);
        when(service.salvar(email)).thenReturn(email);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(email);
        mockMvc.perform(post("/emails")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.email").value("email@gmail.com"));
        verify(service, times(1))
                .salvar(Mockito.any(Email.class));
    }

    @Test
    @DisplayName("Atualizar Emails, retornar a Emails e status 201")
    public void atualizar() throws Exception {
        Email email = new Email(1L, "email@gmail.com", null);
        when(service.atualizar(1L, email)).thenReturn(email);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(email);
        mockMvc.perform(put("/emails/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.email").value("email@gmail.com"));
        verify(service, times(1))
                .atualizar(1L, email);
    }

    // Permissão de acesso

    @Test
    @DisplayName("Listar Emails sem permissão de acesso, retornar o status 403")
    public void permissaoListar() throws Exception {
        EmailFilter emailFilter = new EmailFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(emailFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/emails")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(emailFilter, pageable);
    }

    @Test
    @DisplayName("Listar Emails usando filtro pela email sem permissão de acesso, retornar o status 403")
    public void permissaoListarEmail() throws Exception {
        EmailFilter campanhaFilter = new EmailFilter("email@gmail.com");
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(campanhaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/emails?cargo=cargo")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(campanhaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Emails usando o id sem permissão de acesso, retornar o status 403")
    public void permissaoBuscar() throws Exception {
        Email email = new Email(3L, "email@gmail.com", null);
        when(service.detalhar(3L)).thenReturn(email);
        mockMvc.perform(get("/emails/{id}", 3L)
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
    @DisplayName("Deletar Email sem permissão de acesso, retornar a Email e status 403")
    public void permissaoDeletar() throws Exception {
        mockMvc.perform(delete("/emails/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .deletar(3L);
    }

    @Test
    @DisplayName("Criar Email sem permissão de acesso, retornar o status 403")
    public void permissaoCriar() throws Exception {
        Email email = new Email(3L, "email@gmail.com", null);
        when(service.salvar(email)).thenReturn(email);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(email);
        mockMvc.perform(post("/emails")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .salvar(Mockito.any(Email.class));
    }

    @Test
    @DisplayName("Atualizar Email sem permissão de acesso, retornar o status 403")
    public void permissaoAtualizar() throws Exception {
        Email email = new Email(3L, "email@gmail.com", null);
        when(service.atualizar(1L, email)).thenReturn(email);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(email);
        mockMvc.perform(put("/emails/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .atualizar(1L, email);
    }

    // testando o Token de acesso

    @Test
    @DisplayName("Listar Email com usuario e senha incorretos, retornar status 401")
    public void listamosTokenIncorreto() throws Exception {
        EmailFilter emailFilter = new EmailFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(emailFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/emails")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .filtrar(emailFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Email usando usuario e senha incorretos, retornar status 401")
    public void buscarTokenIncorreto() throws Exception {
        Email email = new Email(3L, "email@gmail.com", null);
        when(service.detalhar(3L)).thenReturn(email);
        mockMvc.perform(get("/emails/{id}", 3L)
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
    @DisplayName("Deletar Email com usuario e senha incorretos, retornar status 401")
    public void deletarTokenIncorreto() throws Exception {
        Email email = new Email(1L, "email@gmail.com", null);
        when(service.deletar(3L)).thenReturn(email);
        mockMvc.perform(delete("/emails/1")
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
    @DisplayName("Criar Email com usuario e senha incorretos, retornar status 401")
    public void criarTokenIncorreto() throws Exception {
        Email email = new Email(1L, "email@gmail.com", null);
        when(service.salvar(email)).thenReturn(email);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(email);
        mockMvc.perform(post("/emails")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, email);
    }

    @Test
    @DisplayName("Atualizar Email com usuario e senha incorretos, retornar status 401")
    public void atualizarTokenIncorreto() throws Exception {
        Email email = new Email(1L, "email@gmail.com", null);
        when(service.atualizar(1L, email)).thenReturn(email);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(email);
        mockMvc.perform(put("/emails/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, email);
    }

    @Test
    @DisplayName("Listar Emails sem token, retornar status 401")
    public void listamosSemToken() throws Exception {
        EmailFilter emailFilter = new EmailFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(emailFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/emails"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .filtrar(emailFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Emails sem token, retornar status 401")
    public void buscarSemToken() throws Exception {
        Email email = new Email(1L, "email@gmail.com", null);
        when(service.detalhar(3L)).thenReturn(email);
        mockMvc.perform(get("/emails/{id}", 3L))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Email sem token, retornar status 401")
    public void deletarSemToken() throws Exception {
        when(service.deletar(3L)).thenReturn(email);
        mockMvc.perform(delete("/emails/1"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .deletar(1L);
    }

    @Test
    @DisplayName("Criar Email sem token, retornar status 401")
    public void criarSemToken() throws Exception {
        Email email = new Email(1L, "email@gmail.com", null);
        when(service.salvar(email)).thenReturn(email);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(email);
        mockMvc.perform(post("/emails")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .salvar(email);
    }

    @Test
    @DisplayName("Atualizar Email sem token, retornar status 401")
    public void atualizarSemToken() throws Exception {
        Email email = new Email(1L, "email@gmail.com", null);
        when(service.atualizar(1L, email)).thenReturn(email);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(email);
        mockMvc.perform(put("/emails/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .atualizar(1L, email);
    }

    // validar a entidade ate aqui

    @Test
    @DisplayName("Criar Email informando um email null, retornar mensagem de erro e status 400.")
    public void criarEmailNull() throws Exception {
        Email email = new Email(1L, null, null);
        when(service.salvar(Mockito.any(Email.class))).thenReturn(email);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(email);
        mockMvc.perform(post("/emails")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("email,email"))
                .andExpect(jsonPath("$.fieldMessage").value("O email não pode ser null"));
        verify(service, times(0))
                .salvar(email);
    }

    @Test
    @DisplayName("Criar Email informando um email vazio, retornar mensagem de erro e status 400.")
    public void criarEmailVazio() throws Exception {
        Email email = new Email(1L, "", null);
        when(service.salvar(Mockito.any(Email.class))).thenReturn(email);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(email);
        mockMvc.perform(post("/emails")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("email"))
                .andExpect(jsonPath("$.fieldMessage").value("O email não pode ser vazio."))
                .andExpect(jsonPath("$.field").value("email"));
        verify(service, times(0))
                .salvar(email);
    }

    @Test
    @DisplayName("Criar Email informando um email invalido, retornar mensagem de erro e status 400.")
    public void criarEmailInvalido() throws Exception {
        Email email = new Email(1L, "emailgmail.com", null);
        when(service.salvar(Mockito.any(Email.class))).thenReturn(email);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(email);
        mockMvc.perform(post("/emails")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.field").value("email"))
                .andExpect(jsonPath("$.fieldMessage").value("Verifique o formato do endereço de e-mail."));
        verify(service, times(0))
                .salvar(email);
    }
}
