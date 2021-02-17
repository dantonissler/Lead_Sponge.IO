package com.leadsponge.leadsponge.IO.roleTestes;

import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.role.Role;
import com.leadsponge.IO.repository.Filter.RoleFilter;
import com.leadsponge.IO.repository.projection.RoleResumo;
import com.leadsponge.IO.services.RoleService;
import com.leadsponge.leadsponge.IO.Util;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class)
@AutoConfigureMockMvc
@DisplayName("Testando token do EndPoint Role")
public class EndPoint {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @MockBean
    private RoleService service;

    @Mock
    private Page<Role> page;

    @Mock
    private Page<RoleResumo> pageResumo;

    private final MediaType contentType = new MediaType("application", "json");

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
    }

    @Test
    @DisplayName("Listar Roles, retornar a Roles e status 200")
    public void listar() throws Exception {
        RoleFilter roleFilter = new RoleFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(roleFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/roles")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"));
        verify(service, times(1)).filtrar(roleFilter, pageable);
    }

    @Test
    @DisplayName("Listar Roles usando filtro pelo nome, retornar a Roles e status 200")
    public void listarNome() throws Exception {
        RoleFilter roleFilter = new RoleFilter("nome");
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(roleFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/roles?nome=nome")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"));
        verify(service, times(1)).filtrar(roleFilter, pageable);
    }

    @Test
    @DisplayName("Listar Roles usando resumo, retornar a Roles e status 200")
    public void Resumir() throws Exception {
        RoleFilter roleFilter = new RoleFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.resumir(roleFilter, pageable)).thenReturn(pageResumo);
        mockMvc.perform(get("/roles/resumo")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nome").value("nome"));
        verify(service, times(1)).filtrar(roleFilter, pageable);
    }

    @Test
    @DisplayName("Listar Roles sem permissão de acesso, retornar o status 403")
    public void permissaoListar() throws Exception {
        RoleFilter roleFilter = new RoleFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(roleFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/roles")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).filtrar(roleFilter, pageable);
    }

    @Test
    @DisplayName("Listar Roles usando filtro pelo nome sem permissão de acesso, retornar o status 403")
    public void permissaoListarsNome() throws Exception {
        RoleFilter roleFilter = new RoleFilter("nome");
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(roleFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/roles?nome=nome")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).filtrar(roleFilter, pageable);
    }

    @Test
    @DisplayName("Resumir Roles sem permissão de acesso, retornar o status 403")
    public void permissaoResumo() throws Exception {
        RoleFilter roleFilter = new RoleFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(roleFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/roles/Resumo")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).filtrar(roleFilter, pageable);
    }

    @Test
    @DisplayName("Listar Role com usuario e senha incorretos, retornar status 401")
    public void listamosTokenIncorreto() throws Exception {
        RoleFilter roleFilter = new RoleFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(roleFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/roles")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).filtrar(roleFilter, pageable);
    }

    @Test
    @DisplayName("Listar Roles sem token, retornar status 401")
    public void listamosSemToken() throws Exception {
        RoleFilter roleFilter = new RoleFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(roleFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/roles"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized()).andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).filtrar(roleFilter, pageable);
    }

    @Test
    @DisplayName("Resumir Roles sem token, retornar status 401")
    public void ResumirSemToken() throws Exception {
        RoleFilter roleFilter = new RoleFilter();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.filtrar(roleFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/roles/resumir"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized()).andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).filtrar(roleFilter, pageable);
    }

}
