package com.leadsponge.leadsponge.IO.tarefaTestes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.tarefa.Tarefa;
import com.leadsponge.IO.models.tarefa.TipoTarefa;
import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.repository.Filter.TarefaFilter;
import com.leadsponge.IO.services.TarefaService;
import com.leadsponge.leadsponge.IO.Util;
import org.junit.jupiter.api.BeforeAll;
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

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class)
@AutoConfigureMockMvc
@DisplayName("Testando token do EndPoint Tarefa")
public class EndPoint {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaService service;

    @Mock
    private Tarefa tarefa;

    @Mock
    private Page<Tarefa> page;

    String nulo = null;
    private static MediaType contentType;
    private static Pageable pageable;

    @BeforeAll
    static void setUp() {
        contentType = new MediaType("application", "json");
        pageable = PageRequest.of(0, 10);
    }

    @Test
    @DisplayName("Listar Tarefas, retornar a Tarefas e status 200")
    public void listar() throws Exception {
        TarefaFilter tarefaFilter = new TarefaFilter();
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Tarefas usando filtro pelo assunto, retornar a Tarefas e status 200")
    public void listarAssunto() throws Exception {
        TarefaFilter tarefaFilter = new TarefaFilter("assunto", null, null, null, null, null);
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas?assunto=assunto")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Tarefas usando filtro pela descrição, retornar a Tarefas e status 200")
    public void listarDescricao() throws Exception {
        TarefaFilter tarefaFilter = new TarefaFilter(null, "descricao", null, null, null, null);
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas?descricao=descricao")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Tarefas usando filtro pela hora marcada, retornar a Tarefas e status 200")
    public void listarHoraMarcada() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        TarefaFilter tarefaFilter = new TarefaFilter(null, null, data, null, null, null);
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas?horaMarcada={data}", data)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Tarefas usando filtro pela realizada, retornar a Tarefas e status 200")
    public void listarRealizada() throws Exception {
        TarefaFilter tarefaFilter = new TarefaFilter(null, null, null, true, null, null);
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas?Realizada=true")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Tarefas usando filtro pela tipo, retornar a Tarefas e status 200")
    public void listarHoraRealizada() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        TarefaFilter tarefaFilter = new TarefaFilter(null, null, null, null, data, null);
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas?horaRealizada={data}", data)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Tarefas usando filtro pela tipo, retornar a Tarefas e status 200")
    public void listarTipo() throws Exception {
        TarefaFilter tarefaFilter = new TarefaFilter(null, null, null, null, null, TipoTarefa.TAREFA);
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas?tipo={tipo}", TipoTarefa.TAREFA)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Tarefas usando o id, retornar a Tarefas e status 200 sucesso")
    public void buscar() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "assunto", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.detalhar(3L)).thenReturn(tarefa);
        mockMvc.perform(get("/tarefas/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.assunto").value("assunto"))
                .andExpect(jsonPath("$.descricao").value("descricao"));
        verify(service, times(1)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Tarefas, retornar a Tarefa e status 200")
    public void deletar() throws Exception {
        mockMvc.perform(delete("/tarefas/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service, times(1)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Tarefas, retornar a Tarefas e status 201")
    public void criar() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "assunto", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.salvar(tarefa)).thenReturn(tarefa);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(tarefa);
        mockMvc.perform(post("/tarefas")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.assunto").value("assunto"))
                .andExpect(jsonPath("$.descricao").value("descricao"));
        verify(service, times(1)).salvar(Mockito.any(Tarefa.class));
    }

    @Test
    @DisplayName("Atualizar Tarefas, retornar a Tarefas e status 201")
    public void atualizar() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "assunto", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.atualizar(1L, tarefa)).thenReturn(tarefa);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(tarefa);
        mockMvc.perform(put("/tarefas/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.assunto").value("assunto"))
                .andExpect(jsonPath("$.descricao").value("descricao"));
        verify(service, times(1))
                .atualizar(1L, tarefa);
    }

    // Permissão de acesso

    @Test
    @DisplayName("Listar Tarefas sem permissão de acesso, retornar o status 403")
    public void permissaoListar() throws Exception {
        TarefaFilter tarefaFilter = new TarefaFilter();
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Tarefas usando filtro pelo assunto sem permissão de acesso, retornar o status 403")
    public void permissaoListarsassunto() throws Exception {
        TarefaFilter tarefaFilter = new TarefaFilter("assunto", null, null, null, null, null);
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas?assunto=assunto")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Tarefas usando filtro pela descrição sem permissão de acesso, retornar o status 403")
    public void permissaoListarDescricao() throws Exception {
        TarefaFilter tarefaFilter = new TarefaFilter(null, "descrição", null, null, null, null);
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas?descricao=descricao")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Tarefas usando filtro pela descricao sem permissão de acesso, retornar o status 403")
    public void permissaoListarHoraMarcada() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        TarefaFilter tarefaFilter = new TarefaFilter(null, null, data, null, null, null);
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas?descricao=descricao")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Tarefas usando filtro pela descricao sem permissão de acesso, retornar o status 403")
    public void permissaoListarRealizada() throws Exception {
        TarefaFilter tarefaFilter = new TarefaFilter(null, null, null, true, null, null);
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas?descricao=descricao")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Tarefas usando filtro pela descricao sem permissão de acesso, retornar o status 403")
    public void permissaoListarHoraRealizada() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        TarefaFilter tarefaFilter = new TarefaFilter(null, null, null, null, data, null);
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas?descricao=descricao")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Listar Tarefas usando filtro pela descricao sem permissão de acesso, retornar o status 403")
    public void permissaoListarTipo() throws Exception {
        TarefaFilter tarefaFilter = new TarefaFilter(null, null, null, null, null, TipoTarefa.TAREFA);
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas?descricao=descricao")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Tarefas usando o id sem permissão de acesso, retornar o status 403")
    public void permissaoBuscar() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "assunto", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.detalhar(3L)).thenReturn(tarefa);
        mockMvc.perform(get("/tarefas/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Tarefa sem permissão de acesso, retornar a Tarefa e status 403")
    public void permissaoDeletar() throws Exception {
        mockMvc.perform(delete("/tarefas/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).deletar(3L);
    }

    @Test
    @DisplayName("Criar Tarefa sem permissão de acesso, retornar o status 403")
    public void permissaoCriar() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "assunto", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.salvar(tarefa)).thenReturn(tarefa);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(tarefa);
        mockMvc.perform(post("/tarefas")
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0)).salvar(Mockito.any(Tarefa.class));
    }

    @Test
    @DisplayName("Atualizar Tarefa sem permissão de acesso, retornar o status 403")
    public void permissaoAtualizar() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "assunto", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.atualizar(1L, tarefa)).thenReturn(tarefa);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(tarefa);
        mockMvc.perform(put("/tarefas/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("access_denied"));
        verify(service, times(0))
                .atualizar(1L, tarefa);
    }

    // testando o Token de acesso

    @Test
    @DisplayName("Listar Tarefa com usuario e senha incorretos, retornar status 401")
    public void listamosTokenIncorreto() throws Exception {
        TarefaFilter tarefaFilter = new TarefaFilter();
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Tarefa usando usuario e senha incorretos, retornar status 401")
    public void buscarTokenIncorreto() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "assunto", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.detalhar(3L)).thenReturn(tarefa);
        mockMvc.perform(get("/tarefas/{id}", 3L)
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Tarefa com usuario e senha incorretos, retornar status 401")
    public void deletarTokenIncorreto() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "assunto", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.deletar(3L)).thenReturn(tarefa);
        mockMvc.perform(delete("/tarefas/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Tarefa com usuario e senha incorretos, retornar status 401")
    public void criarTokenIncorreto() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "assunto", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.salvar(tarefa)).thenReturn(tarefa);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(tarefa);
        mockMvc.perform(post("/tarefas")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, tarefa);
    }

    @Test
    @DisplayName("Atualizar Tarefa com usuario e senha incorretos, retornar status 401")
    public void atualizarTokenIncorreto() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "assunto", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.atualizar(1L, tarefa)).thenReturn(tarefa);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(tarefa);
        mockMvc.perform(put("/tarefas/1")
                .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("invalid_token"));
        verify(service, times(0))
                .atualizar(1L, tarefa);
    }

    @Test
    @DisplayName("Listar Tarefas sem token, retornar status 401")
    public void listamosSemToken() throws Exception {
        TarefaFilter tarefaFilter = new TarefaFilter();
        
        when(service.filtrar(tarefaFilter, pageable)).thenReturn(page);
        mockMvc.perform(get("/tarefas"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .filtrar(tarefaFilter, pageable);
    }

    @Test
    @DisplayName("Buscar Tarefas sem token, retornar status 401")
    public void buscarSemToken() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "assunto", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.detalhar(3L)).thenReturn(tarefa);
        mockMvc.perform(get("/tarefas/{id}", 3L))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).detalhar(3L);
    }

    @Test
    @DisplayName("Deletar Tarefa sem token, retornar status 401")
    public void deletarSemToken() throws Exception {
        when(service.deletar(3L)).thenReturn(tarefa);
        mockMvc.perform(delete("/tarefas/1"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).deletar(1L);
    }

    @Test
    @DisplayName("Criar Tarefa sem token, retornar status 401")
    public void criarSemToken() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "assunto", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.salvar(tarefa)).thenReturn(tarefa);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(tarefa);
        mockMvc.perform(post("/tarefas")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0)).salvar(tarefa);
    }

    @Test
    @DisplayName("Atualizar Tarefa sem token, retornar status 401")
    public void atualizarSemToken() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "assunto", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.atualizar(1L, tarefa)).thenReturn(tarefa);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(tarefa);
        mockMvc.perform(put("/tarefas/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(jsonPath("$.error").value("unauthorized"));
        verify(service, times(0))
                .atualizar(1L, tarefa);
    }

    // validar a entidade ate aqui
    @Test
    @DisplayName("Atualizar Tarefa informando um assunto null, retornar mensagem de erro e status 400.")
    public void atualizarassuntoNull() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, null, "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.atualizar(1L, tarefa)).thenReturn(tarefa);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(tarefa);
        mockMvc.perform(put("/tarefas/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
        verify(service, times(0))
                .atualizar(1L, tarefa);
    }

    @Test
    @DisplayName("Atualizar Tarefa informando um assunto vazio, retornar mensagem de erro e status 400.")
    public void atualizarassuntoVazio() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.atualizar(1L, tarefa)).thenReturn(tarefa);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(tarefa);
        mockMvc.perform(put("/tarefas/{id}", 1L)
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
        verify(service, times(0))
                .atualizar(1L, tarefa);
    }

    @Test
    @DisplayName("Criar Tarefa informando um assunto acima de 50 caracteres, retornar mensagem de erro e status 400.")
    public void criarassuntoAcima50Caracteres() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "assunto assunto assunto assunto assunto assunto assunto assunto assunto assunto assunto assunto", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.salvar(Mockito.any(Tarefa.class))).thenReturn(tarefa);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(tarefa);
        mockMvc.perform(post("/tarefas")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
        verify(service, times(0)).salvar(tarefa);
    }

    @Test
    @DisplayName("Criar Tarefa informando um assunto abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
    public void criarassuntoAbaixo4Caracteres() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Tarefa tarefa = new Tarefa(3L, "nom", "descricao", data, true, data, TipoTarefa.TAREFA, new Usuario(1L), new Negociacao(1L));
        when(service.salvar(Mockito.any(Tarefa.class))).thenReturn(tarefa);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(tarefa);
        mockMvc.perform(post("/tarefas")
                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
        verify(service, times(0)).salvar(tarefa);
    }
}
