//package br.com.blinkdev.leadsponge.leadsponge.IO.tarefaTestes;
//
//import br.com.blinkdev.leadsponge.LeadSpongeApiApplication;
//import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;
//import br.com.blinkdev.leadsponge.endPoints.task.entity.TaskEntity;
//import br.com.blinkdev.leadsponge.endPoints.task.enumeration.TypeTask;
//import br.com.blinkdev.leadsponge.endPoints.task.filter.TaskFilter;
//import br.com.blinkdev.leadsponge.endPoints.task.service.TaskService;
//import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;
//import br.com.blinkdev.leadsponge.leadsponge.IO.Util;
//import com.fasterxml.jackson.databind.ObjectMapper;
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
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = LeadSpongeApiApplication.class)
//@AutoConfigureMockMvc
//@DisplayName("Testando token do EndPoint Tarefa")
//public class EndPoint {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private final TaskFilter tarefaFilter = new TaskFilter();
//    private final TaskFilter tarefaFilterAssunto = new TaskFilter("assunto", null, null, null, null, null);
//
//    private final MediaType contentType = new MediaType("application", "json");
//    private final Pageable pageable = PageRequest.of(0, 10);
//    private final TaskFilter tarefaFilterDescricao = new TaskFilter(null, "descricao", null, null, null, null);
//    private final TaskFilter tarefaFilterRealizada = new TaskFilter(null, null, null, true, null, null);
//    private final TaskFilter tarefaFilterTipo = new TaskFilter(null, null, null, null, null, TypeTask.TAREFA);
//    private final TaskEntity tarefa = new TaskEntity(3L, "assunto", "descricao", "2016-03-04 11:30", true, "2016-03-04 11:30", TypeTask.TAREFA, new UserEntity(1L), new NegotiationEntity(1L));
//    @MockBean
//    private TaskService service;
//    @Mock
//    private Page<TaskEntity> page;
//
//    @Test
//    @DisplayName("Listar Tarefas, retornar a Tarefas e status 200")
//    public void listar() throws Exception {
//        when(service.searchWithFilters(tarefaFilter, pageable)).thenReturn(page);
//        mockMvc.perform(get("/tarefas")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//        verify(service, times(1))
//                .searchWithFilters(tarefaFilter, pageable);
//    }
//
//    @Test
//    @DisplayName("Listar Tarefas usando filtro pelo assunto, retornar a Tarefas e status 200")
//    public void listarAssunto() throws Exception {
//        when(service.searchWithFilters(tarefaFilterAssunto, pageable)).thenReturn(page);
//        mockMvc.perform(get("/tarefas?assunto=assunto")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//        verify(service, times(1))
//                .searchWithFilters(tarefaFilterAssunto, pageable);
//    }
//
//    @Test
//    @DisplayName("Listar Tarefas usando filtro pela descrição, retornar a Tarefas e status 200")
//    public void listarDescricao() throws Exception {
//        when(service.searchWithFilters(tarefaFilterDescricao, pageable)).thenReturn(page);
//        mockMvc.perform(get("/tarefas?descricao=descricao")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//        verify(service, times(1))
//                .searchWithFilters(tarefaFilterDescricao, pageable);
//    }
//
////    @Test
////    @DisplayName("Listar Tarefas usando filtro pela hora marcada, retornar a Tarefas e status 200")
////    public void listarHoraMarcada() throws Exception {
////        when(service.filtrar(tarefaFilterHoraMarcada, pageable)).thenReturn(page);
////        mockMvc.perform(get("/tarefas?horaMarcada={data}", "2020-07-01T04:00:00.000Z")
////                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
////                .accept(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk())
////                .andDo(print());
////        verify(service, times(1))
////                .filtrar(tarefaFilterHoraMarcada, pageable);
////    }
//
//    @Test
//    @DisplayName("Listar Tarefas usando filtro pela realizada, retornar a Tarefas e status 200")
//    public void listarRealizada() throws Exception {
//        when(service.searchWithFilters(tarefaFilterRealizada, pageable)).thenReturn(page);
//        mockMvc.perform(get("/tarefas?Realizada=true")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//        verify(service, times(1))
//                .searchWithFilters(tarefaFilterRealizada, pageable);
//    }
//
////    @Test
////    @DisplayName("Listar Tarefas usando filtro pela tipo, retornar a Tarefas e status 200")
////    public void listarHoraRealizada() throws Exception {
////        when(service.filtrar(tarefaFilterHoraRealizada, pageable)).thenReturn(page);
////        mockMvc.perform(get("/tarefas?horaRealizada={data}", data)
////                .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
////                .accept(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk())
////                .andDo(print());
////        verify(service, times(1))
////                .filtrar(tarefaFilterHoraRealizada, pageable);
////    }
//
//    @Test
//    @DisplayName("Listar Tarefas usando filtro pela tipo, retornar a Tarefas e status 200")
//    public void listarTipo() throws Exception {
//        when(service.searchWithFilters(tarefaFilterTipo, pageable)).thenReturn(page);
//        mockMvc.perform(get("/tarefas?tipo={tipo}", TypeTask.TAREFA)
//                        .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//        verify(service, times(1))
//                .searchWithFilters(tarefaFilterTipo, pageable);
//    }
//
//    @Test
//    @DisplayName("Buscar Tarefas usando o id, retornar a Tarefas e status 200 sucesso")
//    public void buscar() throws Exception {
//        when(service.getById(3L)).thenReturn(tarefa);
//        mockMvc.perform(get("/tarefas/{id}", 3L)
//                        .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.assunto").value("assunto"))
//                .andExpect(jsonPath("$.descricao").value("descricao"));
//        verify(service, times(1)).getById(3L);
//    }
//
//    @Test
//    @DisplayName("Deletar Tarefas, retornar a Tarefa e status 200")
//    public void deletar() throws Exception {
//        mockMvc.perform(delete("/tarefas/{id}", 3L)
//                        .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//        verify(service, times(1)).delete(3L);
//    }
//
//    @Test
//    @DisplayName("Criar Tarefas, retornar a Tarefas e status 201")
//    public void criar() throws Exception {
//        when(service.save(tarefa)).thenReturn(tarefa);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(tarefa);
//        mockMvc.perform(post("/tarefas")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isCreated())
//                .andDo(print())
//                .andExpect(jsonPath("$.assunto").value("assunto"))
//                .andExpect(jsonPath("$.descricao").value("descricao"));
//        verify(service, times(1)).save(Mockito.any(TaskEntity.class));
//    }
//
//    @Test
//    @DisplayName("Atualizar Tarefas, retornar a Tarefas e status 201")
//    public void atualizar() throws Exception {
//        when(service.patch(1L, tarefa)).thenReturn(tarefa);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(tarefa);
//        mockMvc.perform(put("/tarefas/{id}", 1L)
//                        .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isCreated())
//                .andDo(print())
//                .andExpect(jsonPath("$.assunto").value("assunto"))
//                .andExpect(jsonPath("$.descricao").value("descricao"));
//        verify(service, times(1))
//                .patch(1L, tarefa);
//    }
//
//    // Permissão de acesso
//
//    @Test
//    @DisplayName("Listar Tarefas sem permissão de acesso, retornar o status 403")
//    public void permissaoListar() throws Exception {
//        when(service.searchWithFilters(tarefaFilter, pageable)).thenReturn(page);
//        mockMvc.perform(get("/tarefas")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("access_denied"));
//        verify(service, times(0))
//                .searchWithFilters(tarefaFilter, pageable);
//    }
//
//    @Test
//    @DisplayName("Listar Tarefas usando filtro pelo assunto sem permissão de acesso, retornar o status 403")
//    public void permissaoListarsassunto() throws Exception {
//        when(service.searchWithFilters(tarefaFilterAssunto, pageable)).thenReturn(page);
//        mockMvc.perform(get("/tarefas?assunto=assunto")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("access_denied"));
//        verify(service, times(0))
//                .searchWithFilters(tarefaFilterAssunto, pageable);
//    }
//
//    @Test
//    @DisplayName("Listar Tarefas usando filtro pela descrição sem permissão de acesso, retornar o status 403")
//    public void permissaoListarDescricao() throws Exception {
//        when(service.searchWithFilters(tarefaFilterDescricao, pageable)).thenReturn(page);
//        mockMvc.perform(get("/tarefas?descricao=descricao")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("access_denied"));
//        verify(service, times(0))
//                .searchWithFilters(tarefaFilterDescricao, pageable);
//    }
//
////    @Test
////    @DisplayName("Listar Tarefas usando filtro pela descricao sem permissão de acesso, retornar o status 403")
////    public void permissaoListarHoraMarcada() throws Exception {
////        when(service.filtrar(tarefaFilterHoraMarcada, pageable)).thenReturn(page);
////        mockMvc.perform(get("/tarefas?descricao=descricao")
////                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
////                .accept(MediaType.APPLICATION_JSON))
////                .andExpect(status().isForbidden())
////                .andDo(print())
////                .andExpect(jsonPath("$.error").value("access_denied"));
////        verify(service, times(0))
////                .filtrar(tarefaFilterHoraMarcada, pageable);
////    }
//
//    @Test
//    @DisplayName("Listar Tarefas usando filtro pela descricao sem permissão de acesso, retornar o status 403")
//    public void permissaoListarRealizada() throws Exception {
//        when(service.searchWithFilters(tarefaFilterRealizada, pageable)).thenReturn(page);
//        mockMvc.perform(get("/tarefas?descricao=descricao")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("access_denied"));
//        verify(service, times(0))
//                .searchWithFilters(tarefaFilterRealizada, pageable);
//    }
//
////    @Test
////    @DisplayName("Listar Tarefas usando filtro pela descricao sem permissão de acesso, retornar o status 403")
////    public void permissaoListarHoraRealizada() throws Exception {
////        when(service.filtrar(tarefaFilterHoraRealizada, pageable)).thenReturn(page);
////        mockMvc.perform(get("/tarefas?descricao=descricao")
////                .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
////                .accept(MediaType.APPLICATION_JSON))
////                .andExpect(status().isForbidden())
////                .andDo(print())
////                .andExpect(jsonPath("$.error").value("access_denied"));
////        verify(service, times(0))
////                .filtrar(tarefaFilterHoraRealizada, pageable);
////    }
//
//    @Test
//    @DisplayName("Listar Tarefas usando filtro pela descricao sem permissão de acesso, retornar o status 403")
//    public void permissaoListarTipo() throws Exception {
//        when(service.searchWithFilters(tarefaFilterTipo, pageable)).thenReturn(page);
//        mockMvc.perform(get("/tarefas?descricao=descricao")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("access_denied"));
//        verify(service, times(0))
//                .searchWithFilters(tarefaFilterTipo, pageable);
//    }
//
//    @Test
//    @DisplayName("Buscar Tarefas usando o id sem permissão de acesso, retornar o status 403")
//    public void permissaoBuscar() throws Exception {
//        when(service.getById(3L)).thenReturn(tarefa);
//        mockMvc.perform(get("/tarefas/{id}", 3L)
//                        .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isForbidden())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("access_denied"));
//        verify(service, times(0)).getById(3L);
//    }
//
//    @Test
//    @DisplayName("Deletar Tarefa sem permissão de acesso, retornar a Tarefa e status 403")
//    public void permissaoDeletar() throws Exception {
//        mockMvc.perform(delete("/tarefas/{id}", 3L)
//                        .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("access_denied"));
//        verify(service, times(0)).delete(3L);
//    }
//
//    @Test
//    @DisplayName("Criar Tarefa sem permissão de acesso, retornar o status 403")
//    public void permissaoCriar() throws Exception {
//        when(service.save(tarefa)).thenReturn(tarefa);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(tarefa);
//        mockMvc.perform(post("/tarefas")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isForbidden())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("access_denied"));
//        verify(service, times(0)).save(Mockito.any(TaskEntity.class));
//    }
//
//    @Test
//    @DisplayName("Atualizar Tarefa sem permissão de acesso, retornar o status 403")
//    public void permissaoAtualizar() throws Exception {
//        when(service.patch(1L, tarefa)).thenReturn(tarefa);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(tarefa);
//        mockMvc.perform(put("/tarefas/{id}", 1L)
//                        .header("Authorization", "Bearer " + Util.getAccessToken("user", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isForbidden())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("access_denied"));
//        verify(service, times(0))
//                .patch(1L, tarefa);
//    }
//
//    // testando o Token de acesso
//
//    @Test
//    @DisplayName("Listar Tarefa com usuario e senha incorretos, retornar status 401")
//    public void listamosTokenIncorreto() throws Exception {
//        when(service.searchWithFilters(tarefaFilter, pageable)).thenReturn(page);
//        mockMvc.perform(get("/tarefas")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isUnauthorized())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("invalid_token"));
//        verify(service, times(0))
//                .searchWithFilters(tarefaFilter, pageable);
//    }
//
//    @Test
//    @DisplayName("Buscar Tarefa usando usuario e senha incorretos, retornar status 401")
//    public void buscarTokenIncorreto() throws Exception {
//        when(service.getById(3L)).thenReturn(tarefa);
//        mockMvc.perform(get("/tarefas/{id}", 3L)
//                        .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isUnauthorized())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("invalid_token"));
//        verify(service, times(0)).getById(3L);
//    }
//
//    @Test
//    @DisplayName("Deletar Tarefa com usuario e senha incorretos, retornar status 401")
//    public void deletarTokenIncorreto() throws Exception {
//        when(service.delete(3L)).thenReturn(tarefa);
//        mockMvc.perform(delete("/tarefas/1")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isUnauthorized())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("invalid_token"));
//        verify(service, times(0)).delete(1L);
//    }
//
//    @Test
//    @DisplayName("Criar Tarefa com usuario e senha incorretos, retornar status 401")
//    public void criarTokenIncorreto() throws Exception {
//        when(service.save(tarefa)).thenReturn(tarefa);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(tarefa);
//        mockMvc.perform(post("/tarefas")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isUnauthorized())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("invalid_token"));
//        verify(service, times(0))
//                .patch(1L, tarefa);
//    }
//
//    @Test
//    @DisplayName("Atualizar Tarefa com usuario e senha incorretos, retornar status 401")
//    public void atualizarTokenIncorreto() throws Exception {
//        when(service.patch(1L, tarefa)).thenReturn(tarefa);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(tarefa);
//        mockMvc.perform(put("/tarefas/1")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("a", "a", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isUnauthorized())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("invalid_token"));
//        verify(service, times(0))
//                .patch(1L, tarefa);
//    }
//
//    @Test
//    @DisplayName("Listar Tarefas sem token, retornar status 401")
//    public void listamosSemToken() throws Exception {
//        when(service.searchWithFilters(tarefaFilter, pageable)).thenReturn(page);
//        mockMvc.perform(get("/tarefas"))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isUnauthorized())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("unauthorized"));
//        verify(service, times(0))
//                .searchWithFilters(tarefaFilter, pageable);
//    }
//
//    @Test
//    @DisplayName("Buscar Tarefas sem token, retornar status 401")
//    public void buscarSemToken() throws Exception {
//        when(service.getById(3L)).thenReturn(tarefa);
//        mockMvc.perform(get("/tarefas/{id}", 3L))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isUnauthorized())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("unauthorized"));
//        verify(service, times(0)).getById(3L);
//    }
//
//    @Test
//    @DisplayName("Deletar Tarefa sem token, retornar status 401")
//    public void deletarSemToken() throws Exception {
//        when(service.delete(3L)).thenReturn(tarefa);
//        mockMvc.perform(delete("/tarefas/1"))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isUnauthorized())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("unauthorized"));
//        verify(service, times(0)).delete(1L);
//    }
//
//    @Test
//    @DisplayName("Criar Tarefa sem token, retornar status 401")
//    public void criarSemToken() throws Exception {
//        when(service.save(tarefa)).thenReturn(tarefa);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(tarefa);
//        mockMvc.perform(post("/tarefas")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isUnauthorized())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("unauthorized"));
//        verify(service, times(0)).save(tarefa);
//    }
//
//    @Test
//    @DisplayName("Atualizar Tarefa sem token, retornar status 401")
//    public void atualizarSemToken() throws Exception {
//        when(service.patch(1L, tarefa)).thenReturn(tarefa);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(tarefa);
//        mockMvc.perform(put("/tarefas/1")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(contentType))
//                .andExpect(status().isUnauthorized())
//                .andDo(print())
//                .andExpect(jsonPath("$.error").value("unauthorized"));
//        verify(service, times(0))
//                .patch(1L, tarefa);
//    }
//
//    // validar a entidade ate aqui
//    @Test
//    @DisplayName("Atualizar Tarefa informando um assunto null, retornar mensagem de erro e status 400.")
//    public void atualizarassuntoNull() throws Exception {
//        LocalDateTime data = LocalDateTime.now();
//        TaskEntity tarefa = new TaskEntity(3L, null, "descricao", data, true, data, TypeTask.TAREFA, new UserEntity(1L), new NegotiationEntity(1L));
//        when(service.patch(1L, tarefa)).thenReturn(tarefa);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(tarefa);
//        mockMvc.perform(put("/tarefas/{id}", 1L)
//                        .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//        verify(service, times(0))
//                .patch(1L, tarefa);
//    }
//
//    @Test
//    @DisplayName("Atualizar Tarefa informando um assunto vazio, retornar mensagem de erro e status 400.")
//    public void atualizarassuntoVazio() throws Exception {
//        LocalDateTime data = LocalDateTime.now();
//        TaskEntity tarefa = new TaskEntity(3L, "", "descricao", data, true, data, TypeTask.TAREFA, new UserEntity(1L), new NegotiationEntity(1L));
//        when(service.patch(1L, tarefa)).thenReturn(tarefa);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(tarefa);
//        mockMvc.perform(put("/tarefas/{id}", 1L)
//                        .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//        verify(service, times(0))
//                .patch(1L, tarefa);
//    }
//
//    @Test
//    @DisplayName("Criar Tarefa informando um assunto acima de 50 caracteres, retornar mensagem de erro e status 400.")
//    public void criarassuntoAcima50Caracteres() throws Exception {
//        LocalDateTime data = LocalDateTime.now();
//        TaskEntity tarefa = new TaskEntity(3L, "assunto assunto assunto assunto assunto assunto assunto assunto assunto assunto assunto assunto", "descricao", data, true, data, TypeTask.TAREFA, new UserEntity(1L), new NegotiationEntity(1L));
//        when(service.save(Mockito.any(TaskEntity.class))).thenReturn(tarefa);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(tarefa);
//        mockMvc.perform(post("/tarefas")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//        verify(service, times(0)).save(tarefa);
//    }
//
//    @Test
//    @DisplayName("Criar Tarefa informando um assunto abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
//    public void criarassuntoAbaixo4Caracteres() throws Exception {
//        LocalDateTime data = LocalDateTime.now();
//        TaskEntity tarefa = new TaskEntity(3L, "nom", "descricao", data, true, data, TypeTask.TAREFA, new UserEntity(1L), new NegotiationEntity(1L));
//        when(service.save(Mockito.any(TaskEntity.class))).thenReturn(tarefa);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(tarefa);
//        mockMvc.perform(post("/tarefas")
//                        .header("Authorization", "Bearer " + Util.getAccessToken("admin", "123321", mockMvc))
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//        verify(service, times(0)).save(tarefa);
//    }
//}
