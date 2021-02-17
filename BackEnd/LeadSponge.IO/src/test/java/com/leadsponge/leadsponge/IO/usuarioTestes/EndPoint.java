//package com.leadsponge.leadsponge.IO.usuarioTestes;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.leadsponge.IO.LeadSpongeApiApplication;
//import com.leadsponge.IO.models.usuario.Usuario;
//import com.leadsponge.IO.repository.Filter.UsuarioFilter;
//import com.leadsponge.IO.services.UsuarioService;
//import com.leadsponge.leadsponge.IO.Util;
//import org.junit.Before;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.MediaType;
//import org.springframework.security.web.FilterChainProxy;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = LeadSpongeApiApplication.class)
//@AutoConfigureMockMvc
//@DisplayName("Testando token do EndPoint Usuario")
//public class EndPoint {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    private FilterChainProxy springSecurityFilterChain;
//
//    @MockBean
//    private UsuarioService service;
//
//    @Mock
//    private Usuario usuario;
//
//    @Mock
//    private Page<Usuario> page;
//
//    private final MediaType contentType = new MediaType("application", "json");
//
//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
//    }
//
//    @Test
//    @DisplayName("Listar Usuarios, retornar a Usuarios e status 200")
//    public void listar() throws Exception {
//        UsuarioFilter usuarioFilter = new UsuarioFilter();
//        Pageable pageable = PageRequest.of(0, 10);
//        when(service.filtrar(usuarioFilter, pageable)).thenReturn(page);
//        mockMvc.perform(get("/usuarios").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
//        verify(service, times(1)).filtrar(usuarioFilter, pageable);
//    }
//
//    @Test
//    @DisplayName("Listar Usuarios usando filtro pelo nome, retornar a Usuarios e status 200")
//    public void listarNome() throws Exception {
//        UsuarioFilter usuarioFilter = new UsuarioFilter("nome", null,null);
//        Pageable pageable = PageRequest.of(0, 10);
//        when(service.filtrar(usuarioFilter, pageable)).thenReturn(page);
//        mockMvc.perform(get("/usuarios?nome=nome").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
//        verify(service, times(1)).filtrar(usuarioFilter, pageable);
//    }
//
//    @Test
//    @DisplayName("Listar Usuarios usando filtro pela cargo, retornar a Usuarios e status 200")
//    public void listarCargo() throws Exception {
//        UsuarioFilter usuarioFilter = new UsuarioFilter(null, "cargo");
//        Pageable pageable = PageRequest.of(0, 10);
//        when(service.filtrar(usuarioFilter, pageable)).thenReturn(page);
//        mockMvc.perform(get("/usuarios?cargo=cargo").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
//        verify(service, times(1)).filtrar(usuarioFilter, pageable);
//    }
//
//    @Test
//    @DisplayName("Buscar Usuarios usando o id, retornar a Usuarios e status 200 sucesso")
//    public void buscar() throws Exception {
//        Usuario usuario = new Usuario(3L, "nome", "cargo", null, null, null);
//        when(service.detalhar(3L)).thenReturn(usuario);
//        mockMvc.perform(get("/usuarios/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
//                .andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.nome").value("nome")).andExpect(jsonPath("$.cargo").value("cargo"));
//        verify(service, times(1)).detalhar(3L);
//    }
//
//    @Test
//    @DisplayName("Deletar Usuarios, retornar a Usuario e status 200")
//    public void deletar() throws Exception {
//        mockMvc.perform(delete("/usuarios/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
//        verify(service, times(1)).deletar(3L);
//    }
//
//    @Test
//    @DisplayName("Criar Usuarios, retornar a Usuarios e status 201")
//    public void criar() throws Exception {
//        Usuario usuario = new Usuario(3L, "nome", "cargo", null, null, null);
//        when(service.salvar(usuario)).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(post("/usuarios").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.nome").value("nome")).andExpect(jsonPath("$.cargo").value("cargo"));
//        verify(service, times(1)).salvar(Mockito.any(Usuario.class));
//    }
//
//    @Test
//    @DisplayName("Atualizar Usuarios, retornar a Usuarios e status 201")
//    public void atualizar() throws Exception {
//        Usuario usuario = new Usuario(3L, "nome", "cargo", null, null, null);
//        when(service.atualizar(1L, usuario)).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(put("/usuarios/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.nome").value("nome")).andExpect(jsonPath("$.cargo").value("cargo"));
//        verify(service, times(1)).atualizar(1L, usuario);
//    }
//
//    // Permissão de acesso
//
//    @Test
//    @DisplayName("Listar Usuarios sem permissão de acesso, retornar o status 403")
//    public void permissaoListar() throws Exception {
//        UsuarioFilter usuarioFilter = new UsuarioFilter();
//        Pageable pageable = PageRequest.of(0, 10);
//        when(service.filtrar(usuarioFilter, pageable)).thenReturn(page);
//        mockMvc.perform(get("/usuarios").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
//        verify(service, times(0)).filtrar(usuarioFilter, pageable);
//    }
//
//    @Test
//    @DisplayName("Listar Usuarios usando filtro pelo nome sem permissão de acesso, retornar o status 403")
//    public void permissaoListarsNome() throws Exception {
//        UsuarioFilter usuarioFilter = new UsuarioFilter("nome", null);
//        Pageable pageable = PageRequest.of(0, 10);
//        when(service.filtrar(usuarioFilter, pageable)).thenReturn(page);
//        mockMvc.perform(get("/usuarios?nome=nome").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
//        verify(service, times(0)).filtrar(usuarioFilter, pageable);
//    }
//
//    @Test
//    @DisplayName("Listar Usuarios usando filtro pela cargo sem permissão de acesso, retornar o status 403")
//    public void permissaoListarCargo() throws Exception {
//        UsuarioFilter campanhaFilter = new UsuarioFilter(null, "cargo");
//        Pageable pageable = PageRequest.of(0, 10);
//        when(service.filtrar(campanhaFilter, pageable)).thenReturn(page);
//        mockMvc.perform(get("/usuarios?cargo=cargo").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
//        verify(service, times(0)).filtrar(campanhaFilter, pageable);
//    }
//
//    @Test
//    @DisplayName("Buscar Usuarios usando o id sem permissão de acesso, retornar o status 403")
//    public void permissaoBuscar() throws Exception {
//        Usuario usuario = new Usuario(3L, "nome", "cargo", null, null, null);
//        when(service.detalhar(3L)).thenReturn(usuario);
//        mockMvc.perform(get("/usuarios/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
//                .andExpect(status().isForbidden()).andDo(print());
//        verify(service, times(0)).detalhar(3L);
//    }
//
//    @Test
//    @DisplayName("Deletar Usuario sem permissão de acesso, retornar a Usuario e status 403")
//    public void permissaoDeletar() throws Exception {
//        mockMvc.perform(delete("/usuarios/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andDo(print());
//        verify(service, times(0)).deletar(3L);
//    }
//
//    @Test
//    @DisplayName("Criar Usuario sem permissão de acesso, retornar o status 403")
//    public void permissaoCriar() throws Exception {
//        Usuario usuario = new Usuario(1L, "nome", "cargo", null, null, null);
//        when(service.salvar(usuario)).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(post("/usuarios").header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType)).andExpect(status().isForbidden()).andDo(print());
//        verify(service, times(0)).salvar(Mockito.any(Usuario.class));
//    }
//
//    @Test
//    @DisplayName("Atualizar Usuario sem permissão de acesso, retornar o status 403")
//    public void permissaoAtualizar() throws Exception {
//        Usuario usuario = new Usuario(1L, "nome", "cargo", null, null, null);
//        when(service.atualizar(1L, usuario)).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(put("/usuarios/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType)).andExpect(status().isForbidden()).andDo(print());
//        verify(service, times(0)).atualizar(1L, usuario);
//    }
//
//    // testando o Token de acesso
//
//    @Test
//    @DisplayName("Listar Usuario com usuario e senha incorretos, retornar status 401")
//    public void listamosTokenIncorreto() throws Exception {
//        UsuarioFilter usuarioFilter = new UsuarioFilter();
//        Pageable pageable = PageRequest.of(0, 10);
//        when(service.filtrar(usuarioFilter, pageable)).thenReturn(page);
//        mockMvc.perform(get("/usuarios").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
//                .andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
//        verify(service, times(0)).filtrar(usuarioFilter, pageable);
//    }
//
//    @Test
//    @DisplayName("Buscar Usuario usando usuario e senha incorretos, retornar status 401")
//    public void buscarTokenIncorreto() throws Exception {
//        Usuario usuario = new Usuario(1L, "nome", "cargo", null, null, null);
//        when(service.detalhar(3L)).thenReturn(usuario);
//        mockMvc.perform(get("/usuarios/{id}", 3L).header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType))
//                .andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
//        verify(service, times(0)).detalhar(3L);
//    }
//
//    @Test
//    @DisplayName("Deletar Usuario com usuario e senha incorretos, retornar status 401")
//    public void deletarTokenIncorreto() throws Exception {
//        Usuario usuario = new Usuario(1L, "nome", "cargo", null, null, null);
//        when(service.deletar(3L)).thenReturn(usuario);
//        mockMvc.perform(delete("/usuarios/1").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized())
//                .andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
//        verify(service, times(0)).deletar(1L);
//    }
//
//    @Test
//    @DisplayName("Criar Usuario com usuario e senha incorretos, retornar status 401")
//    public void criarTokenIncorreto() throws Exception {
//        Usuario usuario = new Usuario(1L, "nome", "cargo", null, null, null);
//        when(service.salvar(usuario)).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(post("/usuarios").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
//        verify(service, times(0)).atualizar(1L, usuario);
//    }
//
//    @Test
//    @DisplayName("Atualizar Usuario com usuario e senha incorretos, retornar status 401")
//    public void atualizarTokenIncorreto() throws Exception {
//        Usuario usuario = new Usuario(1L, "nome", "cargo", null, null, null);
//        when(service.atualizar(1L, usuario)).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(put("/usuarios/1").header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
//        verify(service, times(0)).atualizar(1L, usuario);
//    }
//
//    @Test
//    @DisplayName("Listar Usuarios sem token, retornar status 401")
//    public void listamosSemToken() throws Exception {
//        UsuarioFilter usuarioFilter = new UsuarioFilter();
//        Pageable pageable = PageRequest.of(0, 10);
//        when(service.filtrar(usuarioFilter, pageable)).thenReturn(page);
//        mockMvc.perform(get("/usuarios")).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
//        verify(service, times(0)).filtrar(usuarioFilter, pageable);
//    }
//
//    @Test
//    @DisplayName("Buscar Usuarios sem token, retornar status 401")
//    public void buscarSemToken() throws Exception {
//        Usuario usuario = new Usuario(1L, "nome", "cargo", null, null, null);
//        when(service.detalhar(3L)).thenReturn(usuario);
//        mockMvc.perform(get("/usuarios/{id}", 3L)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
//        verify(service, times(0)).detalhar(3L);
//    }
//
//    @Test
//    @DisplayName("Deletar Usuario sem token, retornar status 401")
//    public void deletarSemToken() throws Exception {
//        when(service.deletar(3L)).thenReturn(usuario);
//        mockMvc.perform(delete("/usuarios/1")).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print()).andExpect(jsonPath("$.error").value("unauthorized"));
//        verify(service, times(0)).deletar(1L);
//    }
//
//    @Test
//    @DisplayName("Criar Usuario sem token, retornar status 401")
//    public void criarSemToken() throws Exception {
//        Usuario usuario = new Usuario(1L, "nome", "cargo", null, null, null);
//        when(service.salvar(usuario)).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(post("/usuarios").accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print())
//                .andExpect(jsonPath("$.error").value("unauthorized"));
//        verify(service, times(0)).salvar(usuario);
//    }
//
//    @Test
//    @DisplayName("Atualizar Usuario sem token, retornar status 401")
//    public void atualizarSemToken() throws Exception {
//        Usuario usuario = new Usuario(1L, "nome", "cargo", null, null, null);
//        when(service.atualizar(1L, usuario)).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(put("/usuarios/1").accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(content().contentType(contentType)).andExpect(status().isUnauthorized()).andDo(print())
//                .andExpect(jsonPath("$.error").value("unauthorized"));
//        verify(service, times(0)).atualizar(1L, usuario);
//    }
//
//    // validar a entidade ate aqui
//
//    @Test
//    @DisplayName("Criar Usuario informando nome e descricao, retornar as informações enviadas e Status 201")
//    public void criarSemInformarUmaDescricao() throws Exception {
//        Usuario usuario = new Usuario(1L, "nome", "cargo", null, null, null);
//        when(service.salvar(Mockito.any(Usuario.class))).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(post("/usuarios").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print());
//        verify(service, times(1)).salvar(usuario);
//    }
//
//    @Test
//    @DisplayName("Atualizar Usuario sem informar uma descricao, retornar as informações e status 201")
//    public void atualizarSemInformarUmaDescricao() throws Exception {
//        Usuario usuarioNovo = new Usuario(1L, "nome", "", null, null, null);
//        when(service.atualizar(1L, usuarioNovo)).thenReturn(usuarioNovo);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuarioNovo);
//        mockMvc.perform(put("/usuarios/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print()).andExpect(jsonPath("$.nome").value("nome")).andExpect(jsonPath("$.cargo").value(""));
//        verify(service, times(1)).atualizar(1L, usuarioNovo);
//    }
//
//    @Test
//    @DisplayName("Criar Usuario informando um nome null, retornar mensagem de erro e status 400.")
//    public void criarNomeNull() throws Exception {
//        Usuario usuario = new Usuario(1L, null, "cargo", null, null, null);
//        when(service.salvar(Mockito.any(Usuario.class))).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(post("/usuarios").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome")).andExpect(jsonPath("$.fieldMessage").value("Não pode ser null"))
//                .andExpect(jsonPath("$.field").value("nome"));
//        verify(service, times(0)).salvar(usuario);
//    }
//
//    @Test
//    @DisplayName("Atualizar Usuario informando um nome null, retornar mensagem de erro e status 400.")
//    public void atualizarNomeNull() throws Exception {
//        Usuario usuario = new Usuario(1L, null, "cargo", null, null, null);
//        when(service.atualizar(1L, usuario)).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(put("/usuarios/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome")).andExpect(jsonPath("$.fieldMessage").value("Não pode ser null"))
//                .andExpect(jsonPath("$.field").value("nome"));
//        verify(service, times(0)).atualizar(1L, usuario);
//    }
//
//    @Test
//    @DisplayName("Criar Usuario informando um nome vazio, retornar mensagem de erro e status 400.")
//    public void criarNomeVazio() throws Exception {
//        Usuario usuario = new Usuario(1L, "", "cargo", null, null, null);
//        when(service.salvar(Mockito.any(Usuario.class))).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(post("/usuarios").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
//                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
//        verify(service, times(0)).salvar(usuario);
//    }
//
//    @Test
//    @DisplayName("Atualizar Usuario informando um nome vazio, retornar mensagem de erro e status 400.")
//    public void atualizarNomeVazio() throws Exception {
//        Usuario usuario = new Usuario(1L, "", "cargo", null, null, null);
//        when(service.atualizar(1L, usuario)).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(put("/usuarios/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
//                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
//        verify(service, times(0)).atualizar(1L, usuario);
//    }
//
//    @Test
//    @DisplayName("Criar Usuario informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//    public void criarNomeAcima50Caracteres() throws Exception {
//        Usuario usuario = new Usuario(null, "nome nome nome nome nome nome nome nome nome nome nome nome", "cargo", null, null, null);
//        when(service.salvar(Mockito.any(Usuario.class))).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(post("/usuarios").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
//                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
//        verify(service, times(0)).salvar(usuario);
//    }
//
//    @Test
//    @DisplayName("Atualizar Usuario informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//    public void atualizarNomeAcima50Caracteres() throws Exception {
//        Usuario usuario = new Usuario(null, "nome nome nome nome nome nome nome nome nome nome nome nome", "cargo", null, null, null);
//        when(service.atualizar(1L, usuario)).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(put("/usuarios/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
//                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
//        verify(service, times(0)).atualizar(1L, usuario);
//    }
//
//    @Test
//    @DisplayName("Criar Usuario informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
//    public void criarNomeAbaixo4Caracteres() throws Exception {
//        Usuario usuario = new Usuario(null, "nom", "cargo", null, null, null);
//        when(service.salvar(Mockito.any(Usuario.class))).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(post("/usuarios").header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
//                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
//        verify(service, times(0)).salvar(usuario);
//    }
//
//    @Test
//    @DisplayName("Atualizar Usuario informando um nome abaixo 4 caracteres, retornar mensagem de erro e status 400.")
//    public void atualizarNomeAbaixo4Caracteres() throws Exception {
//        Usuario usuario = new Usuario(null, "nom", "cargo", null, null, null);
//        when(service.atualizar(1L, usuario)).thenReturn(usuario);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(usuario);
//        mockMvc.perform(put("/usuarios/{id}", 1L).header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc)).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print()).andExpect(jsonPath("$.field").value("nome"))
//                .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres.")).andExpect(jsonPath("$.field").value("nome"));
//        verify(service, times(0)).atualizar(1L, usuario);
//    }
//}
