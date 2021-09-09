//package br.com.blinkdev.leadsponge.leadsponge.IO;
//
//import br.com.blinkdev.leadsponge.LeadSpongeApiApplication;
//import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampanhaEntity;
//import br.com.blinkdev.leadsponge.endPoints.campanha.Filter.CampanhaFilter;
//import br.com.blinkdev.leadsponge.endPoints.campanha.model.CampanhaModel;
//import br.com.blinkdev.leadsponge.endPoints.cliente.entity.Cliente;
//import br.com.blinkdev.leadsponge.endPoints.cliente.filter.ClienteFilter;
//import br.com.blinkdev.leadsponge.endPoints.contato.entity.Contato;
//import br.com.blinkdev.leadsponge.endPoints.contato.filter.ContatoFilter;
//import br.com.blinkdev.leadsponge.endPoints.email.entity.Email;
//import br.com.blinkdev.leadsponge.endPoints.email.filter.EmailFilter;
//import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.entity.EstagioNegociacao;
//import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.filter.EstagioNegociacaoFilter;
//import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.entity.FonteNegociacao;
//import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.filter.FonteNegociacaoFilter;
//import br.com.blinkdev.leadsponge.endPoints.motivoPerda.entity.MotivoPerda;
//import br.com.blinkdev.leadsponge.endPoints.motivoPerda.filter.MotivoPerdaFilter;
//import br.com.blinkdev.leadsponge.models.negociacao.Negociacao;
//import br.com.blinkdev.leadsponge.models.negociacao.TipoReincidencia;
//import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProduto;
//import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProdutoFilter;
//import br.com.blinkdev.leadsponge.models.produto.Produto;
//import br.com.blinkdev.leadsponge.models.produto.ProdutoFilter;
//import br.com.blinkdev.leadsponge.models.role.Role;
//import br.com.blinkdev.leadsponge.models.role.RoleFilter;
//import br.com.blinkdev.leadsponge.models.role.RoleResumo;
//import br.com.blinkdev.leadsponge.models.segmento.Segmento;
//import br.com.blinkdev.leadsponge.models.segmento.SegmentoFilter;
//import br.com.blinkdev.leadsponge.models.tarefa.Tarefa;
//import br.com.blinkdev.leadsponge.endPoints.tarefa.filter.TarefaFilter;
//import br.com.blinkdev.leadsponge.endPoints.tarefa.enumeration.TipoTarefa;
//import br.com.blinkdev.leadsponge.endPoints.telefone.entity.Telefone;
//import br.com.blinkdev.leadsponge.endPoints.telefone.filter.TelefoneFilter;
//import br.com.blinkdev.leadsponge.endPoints.telefone.enumeration.TipoTelefone;
//import br.com.blinkdev.leadsponge.endPoints.user.entity.Usuario;
//import br.com.blinkdev.leadsponge.endPoints.campanha.repository.CampanhaRepository;
//import br.com.blinkdev.leadsponge.endPoints.cliente.repository.ClienteRepository;
//import br.com.blinkdev.leadsponge.endPoints.campanha.service.CampanhaService;
//import br.com.blinkdev.leadsponge.endPoints.cliente.service.ClienteService;
//import br.com.blinkdev.leadsponge.endPoints.contato.service.ContatoService;
//import br.com.blinkdev.leadsponge.endPoints.email.service.EmailService;
//import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.service.EstagioNegociacaoService;
//import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.service.FonteNegociacaoService;
//import br.com.blinkdev.leadsponge.endPoints.motivoPerda.service.MotivoPerdaService;
//import br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.service.NegociacaoProdutoService;
//import br.com.blinkdev.leadsponge.endPoints.produto.service.ProdutoService;
//import br.com.blinkdev.leadsponge.endPoints.role.service.RoleService;
//import br.com.blinkdev.leadsponge.endPoints.segmento.service.SegmentoService;
//import br.com.blinkdev.leadsponge.endPoints.tarefa.service.TarefaService;
//import br.com.blinkdev.leadsponge.endPoints.telefone.service.TelefoneService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.*;
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
//import org.springframework.transaction.TransactionSystemException;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import static br.com.blinkdev.leadsponge.leadsponge.IO.Util.getAccessToken;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
//@DisplayName("Aplicação LeadSponge Testes")
//@SpringBootTest(classes = LeadSpongeApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class LeadSpongeTestes {
//    private final MediaType contentType = new MediaType("application", "json");
//    private final Pageable pageable = PageRequest.of(0, 10);
//    private ObjectMapper mapper;
//
//    @Nested
//    @DisplayName("Campanha")
//    class CampanhaTestes {
//        private final CampanhaFilter filter = new CampanhaFilter(null, null);
//        private final CampanhaFilter filterNome = new CampanhaFilter("nome", null);
//        private final CampanhaFilter filterDescricao = new CampanhaFilter(null, "descrição");
//        private final CampanhaEntity campanha = new CampanhaEntity(3L, "nome", "descrição");
//        private final CampanhaEntity nomeNull = new CampanhaEntity(null, null, "descrição");
//        private final CampanhaEntity nomeVazio = new CampanhaEntity(null, "", "descrição");
//        private final CampanhaEntity nomeMaior50 = new CampanhaEntity(null, "Nome campanha, nome campanha, nome campanha e nomes", "campanha descricao");
//        private final CampanhaEntity nomeMenor4 = new CampanhaEntity(1L, "Nom", "campanha descrição");
//
//        @Nested
//        @DisplayName("Testando End Point, token valido.")
//        class TokenValido {
//
//            @Autowired
//            private MockMvc mockMvc;
//            @MockBean
//            private CampanhaService campanhaServiceMock;
//            @Mock
//            private Page<CampanhaEntity> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Campanha, retornar a Campanha e status 200")
//            public void listarCampanhas() throws Exception {
//                when(campanhaServiceMock.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(campanhaServiceMock, times(1)).filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Campanha usando filtro pelo nome, retornar a Campanha e status 200")
//            public void listarCampanhasNome() throws Exception {
//                when(campanhaServiceMock.filtrar(filterNome, pageable)).thenReturn(page);
//                mockMvc.perform(get("/campanhas?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(campanhaServiceMock, times(1)).filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Campanha usando filtro pela descrição, retornar a Campanha e status 200")
//            public void listarCampanhasDescricao() throws Exception {
//                when(campanhaServiceMock.filtrar(filterDescricao, pageable)).thenReturn(page);
//                mockMvc.perform(get("/campanhas?descricao=descrição")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(campanhaServiceMock, times(1)).filtrar(filterDescricao, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Campanha usando o id, retornar a Campanha e status 200 sucesso")
//            public void buscarCampanha() throws Exception {
//                when(campanhaServiceMock.detalhar(3L)).thenReturn(Mockito.any(CampanhaModel.class));
//                mockMvc.perform(get("/campanhas/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"))
//                        .andExpect(jsonPath("$.descricao").value("descrição"));
//                verify(campanhaServiceMock, times(1)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Campanha, retornar a Campanha e status 200")
//            public void deletarCampanha() throws Exception {
//                mockMvc.perform(delete("/campanhas/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(campanhaServiceMock, times(1)).deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Campanha, retornar a Campanha e status 201")
//            public void criarCampanha() throws Exception {
//                when(campanhaServiceMock.salvar(campanha)).thenReturn(campanha);
//                String jsonString = mapper.writeValueAsString(campanha);
//                mockMvc.perform(post("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"))
//                        .andExpect(jsonPath("$.descricao").value("descrição"));
//                verify(campanhaServiceMock, times(1)).salvar(Mockito.any(CampanhaEntity.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Campanha, retornar a Campanha e status 200")
//            public void atualizarCampanha() throws Exception {
//                when(campanhaServiceMock.atualizar(campanha)).thenReturn(campanha);
//                String jsonString = mapper.writeValueAsString(campanha);
//                mockMvc.perform(put("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"))
//                        .andExpect(jsonPath("$.descricao").value("descrição"));
//                verify(campanhaServiceMock, times(1)).atualizar(campanha);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, token invalido.")
//        class TokenInvalido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private CampanhaService service;
//
//            @Mock
//            private Page<CampanhaEntity> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Campanhas com usuario e senha incorretos, retornar status 401")
//            public void listamosCampanhasTokenIncorreto() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Campanha usando usuario e senha incorretos, retornar status 401")
//            public void buscarCampanhasTokenIncorreto() throws Exception {
//                when(service.detalhar(3L)).thenReturn(Mockito.any(CampanhaModel.class));
//                mockMvc.perform(get("/campanhas/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Campanha com usuario e senha incorretos, retornar status 401")
//            public void deletarCampanhasTokenIncorreto() throws Exception {
//                when(service.deletar(3L)).thenReturn(campanha);
//                mockMvc.perform(delete("/campanhas/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Campanha com usuario e senha incorretos, retornar status 401")
//            public void criarCampanhasTokenIncorreto() throws Exception {
//                when(service.salvar(campanha)).thenReturn(campanha);
//                String jsonString = mapper.writeValueAsString(campanha);
//                mockMvc.perform(post("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).atualizar(campanha);
//            }
//
//            @Test
//            @DisplayName("Atualizar Campanha com usuario e senha incorretos, retornar status 401")
//            public void atualizarCampanhasTokenIncorreto() throws Exception {
//                when(service.atualizar(campanha)).thenReturn(campanha);
//                String jsonString = mapper.writeValueAsString(campanha);
//                mockMvc.perform(put("/campanhas/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).atualizar(campanha);
//            }
//
//            @Test
//            @DisplayName("Listar Campanhas sem token, retornar status 401")
//            public void listamosSemToken() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/campanhas"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Campanha sem token, retornar status 401")
//            public void buscarSemToken() throws Exception {
//                when(service.detalhar(3L)).thenReturn(Mockito.any(CampanhaModel.class));
//                mockMvc.perform(get("/campanhas/{id}", 3L))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Campanha sem token, retornar status 401")
//            public void deletarSemToken() throws Exception {
//                when(service.deletar(3L)).thenReturn(campanha);
//                mockMvc.perform(delete("/campanhas/1"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Campanha sem token, retornar status 401")
//            public void criarSemToken() throws Exception {
//                when(service.salvar(campanha)).thenReturn(campanha);
//                String jsonString = mapper.writeValueAsString(campanha);
//                mockMvc.perform(post("/campanhas")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).salvar(campanha);
//            }
//
//            @Test
//            @DisplayName("Atualizar Campanha sem token, retornar status 401")
//            public void atualizarSemToken() throws Exception {
//                when(service.atualizar(campanha)).thenReturn(campanha);
//                String jsonString = mapper.writeValueAsString(campanha);
//                mockMvc.perform(put("/campanhas/1")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).atualizar(campanha);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, acesso sem permissão.")
//        class SemPermissao {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private CampanhaService service;
//
//            @Mock
//            private Page<CampanhaEntity> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Campanha sem permissão de acesso, retornar a Campanha o status 403")
//            public void permissaoListarCampanhas() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Campanha usando filtro pelo nome sem permissão de acesso, retornar o status 403")
//            public void permissaoListarCampanhasNome() throws Exception {
//                when(service.filtrar(filterNome, pageable)).thenReturn(page);
//                mockMvc.perform(get("/campanhas?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Campanha usando filtro pela descrição sem permissão de acesso, retornar o status 403")
//            public void permissaoListarCampanhasDescricao() throws Exception {
//                when(service.filtrar(filterDescricao, pageable)).thenReturn(page);
//                mockMvc.perform(get("/campanhas?descricao=descrição")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).filtrar(filterDescricao, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Campanha usando o id sem permissão de acesso, retornar o status 403")
//            public void permissaoBuscarCampanha() throws Exception {
//                when(service.detalhar(3L)).thenReturn(Mockito.any(CampanhaModel.class));
//                mockMvc.perform(get("/campanhas/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Campanha sem permissão de acesso, retornar a Campanha e status 403")
//            public void permissaoDeletarCampanha() throws Exception {
//                mockMvc.perform(delete("/campanhas/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Campanha sem permissão de acesso, retornar o status 403")
//            public void permissaoCriarCampanha() throws Exception {
//                when(service.salvar(campanha)).thenReturn(campanha);
//                String jsonString = mapper.writeValueAsString(campanha);
//                mockMvc.perform(post("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).salvar(Mockito.any(CampanhaEntity.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Campanha sem permissão de acesso, retornar o status 403")
//            public void permissaoAtualizarCampanha() throws Exception {
//                when(service.atualizar(campanha)).thenReturn(campanha);
//                String jsonString = mapper.writeValueAsString(campanha);
//                mockMvc.perform(put("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).atualizar(campanha);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, validações das entidades.")
//        class ValidacoesEntidade {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private CampanhaService service;
//
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Criar Campanha informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void criarNomeVazio() throws Exception {
//                when(service.salvar(Mockito.any(CampanhaEntity.class))).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(post("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).salvar(nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Atualizar Campanha informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void atualizarNomeVazio() throws Exception {
//                when(service.atualizar(nomeVazio)).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(put("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).atualizar(nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Criar Campanha informando nome e descricao, retornar as informações enviadas e Status 201")
//            public void criarNomeNull() throws Exception {
//                when(service.salvar(Mockito.any(CampanhaEntity.class))).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(post("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0)).salvar(nomeNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Campanha sem informar uma descricao, retornar as informações e status 201")
//            public void atualizarNomeNull() throws Exception {
//                when(service.atualizar(nomeNull)).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(put("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0)).atualizar(nomeNull);
//            }
//
//            @Test
//            @DisplayName("Criar Campanha informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAcima50Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(CampanhaEntity.class))).thenReturn(nomeMaior50);
//                String jsonString = mapper.writeValueAsString(nomeMaior50);
//                mockMvc.perform(post("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).salvar(nomeMaior50);
//            }
//
//            @Test
//            @DisplayName("Atualizar Campanha informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAcima50Caracteres() throws Exception {
//                when(service.atualizar(nomeMaior50)).thenReturn(nomeMaior50);
//                String jsonString = mapper.writeValueAsString(nomeMaior50);
//                mockMvc.perform(put("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).atualizar(nomeMaior50);
//            }
//
//            @Test
//            @DisplayName("Criar Campanha informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAbaixo4Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(CampanhaEntity.class))).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(post("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).salvar(nomeMenor4);
//            }
//
//            @Test
//            @DisplayName("Atualizar Campanha informando um nome abaixo 4 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAbaixo4Caracteres() throws Exception {
//                when(service.atualizar(nomeMenor4)).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(put("/campanhas")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).atualizar(nomeMenor4);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//            @Autowired
//            private CampanhaRepository repository;
//
//            @AfterEach
//            void setUp() {
//                repository.deleteAll();
//            }
//
//            @Test
//            @DisplayName("Criar Campanha e persistir os dados, retornar: id, nome e descrição")
//            public void criar() {
//                CampanhaEntity campanha = new CampanhaEntity(null, "Nome", "Descrição");
//                campanha = this.repository.save(campanha);
//                CampanhaEntity campanhaNovo = repository.findById(campanha.getId()).orElse(null);
//                assert campanhaNovo != null;
//                assertThat(campanhaNovo.getId()).isNotNull();
//                assertEquals("Nome", campanhaNovo.getNome());
//                assertEquals("Descrição", campanhaNovo.getDescricao());
//            }
//
//            @Test
//            @DisplayName("Deletar uma Campanha")
//            public void deletar() {
//                CampanhaEntity campanha = new CampanhaEntity(null, "Nome de uma campanha", "Descrição de uma campanha");
//                this.repository.save(campanha);
//                this.repository.delete(campanha);
//                assertThat(repository.findById(campanha.getId())).isEmpty();
//            }
//
//            @Test
//            @DisplayName("Atualizar uma Campanha e persistir os dados, retorno: id, nome e descrição")
//            public void atualizar() {
//                CampanhaEntity campanha = new CampanhaEntity(null, "Nome de uma campanha", "Descrição de uma campanha");
//                campanha = this.repository.save(campanha);
//                campanha = new CampanhaEntity(campanha.getId(), "Nome dois", "Descrição dois");
//                campanha = this.repository.save(campanha);
//                assertThat(campanha.getNome()).isEqualTo("Nome dois");
//                assertThat(campanha.getDescricao()).isEqualTo("Descrição dois");
//            }
//
//            @Test
//            @DisplayName("Buscar Campanha por id, retorno: id, nome e descrição")
//            public void buscarPorId() {
//                CampanhaEntity campanha = new CampanhaEntity(null, "Nome de uma campanha", "Descrição de uma campanha");
//                campanha = this.repository.save(campanha);
//                assertThat(campanha.getId()).isNotNull();
//                assertThat(campanha.getNome()).isEqualTo("Nome de uma campanha");
//                assertThat(campanha.getDescricao()).isEqualTo("Descrição de uma campanha");
//            }
//
//            @Test
//            @DisplayName("Salva todas as entidades fornecidas.")
//            public void salvarTodosEntidades() {
//                List<CampanhaEntity> campanhaSalvas = this.repository.saveAll(new ArrayList<>(Arrays.asList(new CampanhaEntity(null, "Nome1", "Descrição1"), new CampanhaEntity(null, "Nome2", "Descrição2"))));
//                assertThat(campanhaSalvas.get(0).getId()).isNotNull();
//                assertThat(campanhaSalvas.get(0).getNome()).isEqualTo("Nome1");
//                assertThat(campanhaSalvas.get(0).getDescricao()).isEqualTo("Descrição1");
//                assertThat(campanhaSalvas.get(1).getId()).isNotNull();
//                assertThat(campanhaSalvas.get(1).getNome()).isEqualTo("Nome2");
//                assertThat(campanhaSalvas.get(1).getDescricao()).isEqualTo("Descrição2");
//            }
//
//            @Test
//            @DisplayName("exclui uma determinada entidade.")
//            public void deletarEntidade() {
//                CampanhaEntity campanha = new CampanhaEntity(1L, "Nome1", "Descrição1");
//                this.repository.delete(campanha);
//                assertThat(repository.findById(campanha.getId())).isEmpty();
//            }
//
//            @Test
//            @DisplayName("Exclui as entidades fornecidas.")
//            public void deletarTodosPorEntidades() {
//                List<CampanhaEntity> campanhas = Arrays.asList(new CampanhaEntity(1L, "Nome1", "Descrição1"), new CampanhaEntity(2L, "Nome2", "Descrição2"));
//                this.repository.saveAll(campanhas);
//                this.repository.deleteAll(campanhas);
//                assertThat(repository.findById(1L)).isEmpty();
//                assertThat(repository.findById(2L)).isEmpty();
//            }
//
//            @Test
//            @DisplayName("Exclui todas as entidades gerenciadas pelo repositório.")
//            public void deletarTudo() {
//                List<CampanhaEntity> campanhas = Arrays.asList(new CampanhaEntity(1L, "Nome1", "Descrição1"), new CampanhaEntity(2L, "Nome2", "Descrição2"), new CampanhaEntity(3L, "Nome3", "Descrição3"));
//                this.repository.saveAll(campanhas);
//                this.repository.deleteAll();
//                assertThat(repository.findById(1L)).isEmpty();
//                assertThat(repository.findById(2L)).isEmpty();
//                assertThat(repository.findById(3L)).isEmpty();
//            }
//
//            @Test
//            @DisplayName("Retorna todas as instâncias do tipo.")
//            public void buscarTodosEntidades() {
//                List<CampanhaEntity> campanhas = Arrays.asList(new CampanhaEntity(null, "Nome1", "Descrição1"), new CampanhaEntity(null, "Nome2", "Descrição2"));
//                this.repository.saveAll(campanhas);
//                List<CampanhaEntity> campanhaBusca = this.repository.findAll();
//                assertThat(campanhaBusca.get(0).getId()).isNotNull();
//                assertThat(campanhaBusca.get(0).getNome()).isEqualTo("Nome1");
//                assertThat(campanhaBusca.get(0).getDescricao()).isEqualTo("Descrição1");
//                assertThat(campanhaBusca.get(1).getId()).isNotNull();
//                assertThat(campanhaBusca.get(1).getNome()).isEqualTo("Nome2");
//                assertThat(campanhaBusca.get(1).getDescricao()).isEqualTo("Descrição2");
//            }
//
//            @Test
//            @DisplayName("Seleciona varias entidades fornecendo os IDs existe.")
//            public void buscarCIds() {
//                List<CampanhaEntity> campanhasSalvas = this.repository.saveAll(Arrays.asList(new CampanhaEntity(1L, "Nome1", "Descrição1"), new CampanhaEntity(2L, "Nome2", "Descrição2")));
//                List<CampanhaEntity> campanhas = this.repository.findAllById(Arrays.asList(campanhasSalvas.get(0).getId(), campanhasSalvas.get(1).getId()));
//                assertThat(campanhas.get(0).getId()).isNotNull();
//                assertThat(campanhas.get(0).getNome()).isEqualTo("Nome1");
//                assertThat(campanhas.get(0).getDescricao()).isEqualTo("Descrição1");
//                assertThat(campanhas.get(1).getId()).isNotNull();
//                assertThat(campanhas.get(1).getNome()).isEqualTo("Nome2");
//                assertThat(campanhas.get(1).getDescricao()).isEqualTo("Descrição2");
//            }
//
//            @Test
//            @DisplayName("Verificar se exite o ID fornecido.")
//            public void verificarSeExistePorId() {
//                CampanhaEntity campanha = this.repository.save(new CampanhaEntity(1L, "Nome", "Descrição"));
//                assertTrue(this.repository.existsById(campanha.getId()));
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service.")
//        class Service {
//
//            @Autowired
//            private CampanhaService service;
//
//            @Autowired
//            private CampanhaRepository repository;
//
//            @AfterEach
//            void setUp() {
//                repository.deleteAll();
//            }
//
//            @Test
//            @DisplayName("Criar Campanha com nome e descrição, percistir os dados.")
//            public void Criar() {
//                CampanhaEntity campanha = this.service.salvar(new CampanhaEntity(1L, "Nome de uma campanha", "Descrição de uma campanha"));
//                assertThat(campanha.getId()).isNotNull();
//                assertEquals("Nome de uma campanha", campanha.getNome());
//                assertEquals("Descrição de uma campanha", campanha.getDescricao());
//            }
//
//            @Test
//            @DisplayName("Criar Campanha com nome e descrição, percistir os dados.")
//            public void CriarNomeNull() {
//                CampanhaEntity campanha = this.service.salvar(new CampanhaEntity(1L, "Nome de uma campanha", "Descrição de uma campanha"));
//                assertThat(campanha.getId()).isNotNull();
//                assertEquals("Nome de uma campanha", campanha.getNome());
//                assertEquals("Descrição de uma campanha", campanha.getDescricao());
//            }
//
//            @Test
//            @DisplayName("Deletar verificar se foi removido.")
//            public void deletar() {
//                CampanhaEntity campanha = this.service.salvar(new CampanhaEntity(1L, "Nome de uma campanha", "Descrição de uma campanha"));
//                service.deletar(campanha.getId());
//                assertThat(repository.findById(campanha.getId())).isEmpty();
//            }
//
//            @Test
//            @DisplayName("Detalhar retornar uma entidade selecionada por id de campanha.")
//            public void detalhar() {
//                CampanhaEntity campanhaSalva = this.repository.save(new CampanhaEntity(1L, "Nome", "Descrição"));
//                CampanhaEntity campanha = service.deletar(campanhaSalva.getId());
//                assertThat(campanha.getId()).isNotNull();
//                assertEquals("Nome", campanha.getNome());
//                assertEquals("Descrição", campanha.getDescricao());
//            }
//
//            @Test
//            @DisplayName("Filtrar itens selecionados ")
//            public void filtrar() {
//                repository.saveAll(Arrays.asList(new CampanhaEntity(null, "Nome1", "Descrição1"), new CampanhaEntity(null, "Nome2", "Descrição2")));
//                CampanhaFilter filtro = new CampanhaFilter("Nome1", "Descrição1");
//                Page<CampanhaEntity> campanhaPage = service.filtrar(filtro, pageable);
//                assertEquals("Nome1", campanhaPage.getContent().get(0).getNome());
//                assertEquals("Descrição1", campanhaPage.getContent().get(0).getDescricao());
//            }
//
//            @Test
//            @DisplayName("Filtrar itens selecionados filtrar por nome.")
//            public void filtrarPorNome() {
//                repository.saveAll(Arrays.asList(new CampanhaEntity(null, "DanNome1", "Descrição1"), new CampanhaEntity(null, "DanNome2", "Descrição2")));
//                CampanhaFilter filtro = new CampanhaFilter("DanNome", null);
//                Pageable pageable = PageRequest.of(0, 2);
//                Page<CampanhaEntity> campanhaPage = service.filtrar(filtro, pageable);
//                assertEquals("DanNome1", campanhaPage.getContent().get(0).getNome());
//                assertEquals("Descrição1", campanhaPage.getContent().get(0).getDescricao());
//                assertEquals("DanNome2", campanhaPage.getContent().get(1).getNome());
//                assertEquals("Descrição2", campanhaPage.getContent().get(1).getDescricao());
//                assertThat(campanhaPage.getSize()).isEqualTo(2);
//            }
//
//            @Test
//            @DisplayName("Filtrar itens selecionados filtrar por descrição.")
//            public void filtrarPorDescricao() {
//                repository.saveAll(Arrays.asList(new CampanhaEntity(null, "Nome1", "DanDescrição1"), new CampanhaEntity(null, "Nome2", "Descrição2")));
//                CampanhaFilter filtro = new CampanhaFilter(null, "DanDescrição1");
//                Page<CampanhaEntity> campanhaPage = service.filtrar(filtro, pageable);
//                assertEquals("Nome1", campanhaPage.getContent().get(0).getNome());
//                assertEquals("DanDescrição1", campanhaPage.getContent().get(0).getDescricao());
//            }
//
//            @Test
//            @DisplayName("Atualizar uma entidade de campanha por id.")
//            public void atualizar() {
//                CampanhaEntity campanhaSalva = this.service.salvar(new CampanhaEntity(1L, "Nome1", "Descrição1"));
//                CampanhaEntity campanha = this.service.atualizar(new CampanhaEntity(campanhaSalva.getId(), "Nome2", "Descrição2"));
//                assertThat(campanha.getId()).isNotNull();
//                assertEquals("Nome2", campanha.getNome());
//                assertEquals("Descrição2", campanha.getDescricao());
//            }
//
//            @Test
//            @DisplayName("Criar Campanha com nome e descrição, percistir os dados.")
//            public void CriarComNomeNull() {
//                Exception exception = assertThrows(TransactionSystemException.class, () -> service.salvar(new CampanhaEntity(1L, "a", "Descrição de uma campanha")));
//                assertTrue(exception.getMessage().contains("Error while committing the transaction"));
//            }
//        }
//    }
//
//    @Nested
//    @DisplayName("Cliente")
//    class ClienteTestes {
//        private final Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
//        private final ClienteFilter filter = new ClienteFilter(null, null, null);
//        private final ClienteFilter filterNome = new ClienteFilter("nome", null, null);
//        private final ClienteFilter filterUrl = new ClienteFilter(null, "url", null);
//        private final ClienteFilter filterResumo = new ClienteFilter(null, null, "resumo");
//        // TODO filter carregado
//        private final Cliente nomeNull = new Cliente(null, null, "url", "resumo", null, null, null, null, null);
//        private final Cliente nomeVazio = new Cliente(null, "", "url", "resumo", null, null, null, null, null);
//        private final Cliente nomeMaior50 = new Cliente(null, "Nome campanha, nome Cliente, nome cliente e nomes nome", "url", "resumo", null, null, null, null, null);
//        private final Cliente nomeMenor4 = new Cliente(null, "Nom", "url", "resumo", null, null, null, null, null);
//
//        @Nested
//        @DisplayName("Testando End Point, token valido.")
//        class TokenValido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private ClienteService service;
//
//            @Mock
//            private Page<Cliente> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Clientes, retornar a Clientes e status 200")
//            public void listarClientes() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/clientes")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Clientes usando filtro pelo nome, retornar a Cliente e status 200")
//            public void listarClientesNome() throws Exception {
//
//                when(service.filtrar(filterNome, pageable))
//                        .thenReturn(page);
//                mockMvc.perform(get("/clientes?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status()
//                                .isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Clientes usando filtro pela url, retornar a Cliente e status 200")
//            public void listarClientesUrl() throws Exception {
//                when(service.filtrar(filterUrl, pageable))
//                        .thenReturn(page);
//                mockMvc.perform(get("/clientes?url=url")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterUrl, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Clientes usando filtro pela resumo, retornar a Cliente e status 200")
//            public void listarClientesResumo() throws Exception {
//                when(service.filtrar(filterResumo, pageable)).thenReturn(page);
//                mockMvc.perform(get("/clientes?resumo=resumo")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterResumo, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Cliente usando o id, retornar a Cliente e status 200 sucesso")
//            public void buscarCliente() throws Exception {
//                Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
//                when(service.detalhar(3L))
//                        .thenReturn(cliente);
//                mockMvc.perform(get("/clientes/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"))
//                        .andExpect(jsonPath("$.resumo").value("resumo"));
//                verify(service, times(1))
//                        .detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Cliente, retornar a Cliente e status 200")
//            public void deletarCliente() throws Exception {
//                mockMvc.perform(delete("/clientes/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Cliente, retornar a Cliente e status 201")
//            public void criarCliente() throws Exception {
//                when(service.salvar(cliente)).thenReturn(cliente);
//                String jsonString = mapper.writeValueAsString(cliente);
//                mockMvc.perform(post("/clientes")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"))
//                        .andExpect(jsonPath("$.resumo").value("resumo"));
//                verify(service, times(1))
//                        .salvar(Mockito.any(Cliente.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Cliente, retornar a Cliente e status 201")
//            public void atualizarCliente() throws Exception {
//                when(service.atualizar(1L, cliente)).thenReturn(cliente);
//                String jsonString = mapper.writeValueAsString(cliente);
//                mockMvc.perform(put("/clientes/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"))
//                        .andExpect(jsonPath("$.resumo").value("resumo"));
//                verify(service, times(1))
//                        .atualizar(1L, cliente);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, token invalido.")
//        class TokenInvalido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private ClienteService service;
//
//            @Mock
//            private Page<Cliente> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Clientes com usuario e senha incorretos, retornar status 401")
//            public void listamosClientesTokenIncorreto() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/clientes")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Cliente usando usuario e senha incorretos, retornar status 401")
//            public void buscarClientesTokenIncorreto() throws Exception {
//                when(service.detalhar(3L)).thenReturn(cliente);
//                mockMvc.perform(get("/clientes/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Cliente com usuario e senha incorretos, retornar status 401")
//            public void deletarClientesTokenIncorreto() throws Exception {
//                when(service.deletar(3L)).thenReturn(cliente);
//                mockMvc.perform(delete("/clientes/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Cliente com usuario e senha incorretos, retornar status 401")
//            public void criarClientesTokenIncorreto() throws Exception {
//                when(service.salvar(cliente)).thenReturn(cliente);
//                String jsonString = mapper.writeValueAsString(cliente);
//                mockMvc.perform(post("/clientes")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(3L, cliente);
//            }
//
//            @Test
//            @DisplayName("Atualizar Cliente com usuario e senha incorretos, retornar status 401")
//            public void atualizarClientesTokenIncorreto() throws Exception {
//                when(service.atualizar(3L, cliente)).thenReturn(cliente);
//                String jsonString = mapper.writeValueAsString(cliente);
//                mockMvc.perform(put("/clientes/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(3L, cliente);
//            }
//
//            @Test
//            @DisplayName("Listar Clientes sem token, retornar status 401")
//            public void listamosSemToken() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/clientes"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Clientes sem token, retornar status 401")
//            public void buscarSemToken() throws Exception {
//                when(service.detalhar(3L)).thenReturn(cliente);
//                mockMvc.perform(get("/clientes/{id}", 3L))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Cliente sem token, retornar status 401")
//            public void deletarSemToken() throws Exception {
//                when(service.deletar(3L)).thenReturn(cliente);
//                mockMvc.perform(delete("/clientes/1"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Cliente sem token, retornar status 401")
//            public void criarSemToken() throws Exception {
//                when(service.salvar(cliente)).thenReturn(cliente);
//                String jsonString = mapper.writeValueAsString(cliente);
//                mockMvc.perform(post("/clientes")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .salvar(cliente);
//            }
//
//            @Test
//            @DisplayName("Atualizar Cliente sem token, retornar status 401")
//            public void atualizarSemToken() throws Exception {
//                when(service.atualizar(1L, cliente)).thenReturn(cliente);
//                String jsonString = mapper.writeValueAsString(cliente);
//                mockMvc.perform(put("/clientes/1")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .atualizar(1L, cliente);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, acesso sem permissão.")
//        class SemPermissao {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private ClienteService service;
//
//            @Mock
//            private Page<Cliente> page;
//
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Clientes sem permissão de acesso, retornar o status 403")
//            public void permissaoListarClientes() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/clientes")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Clientes usando filtro pelo nome sem permissão de acesso, retornar o status 403")
//            public void permissaoListarClientesNome() throws Exception {
//                when(service.filtrar(filterNome, pageable))
//                        .thenReturn(page);
//                mockMvc.perform(get("/clientes?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Clientes usando filtro pela url sem permissão de acesso, retornar o status 403")
//            public void permissaoListarClientesUrl() throws Exception {
//                when(service.filtrar(filterUrl, pageable)).thenReturn(page);
//                mockMvc.perform(get("/clientes?resumo=resumo")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filterUrl, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Clientes usando filtro pela resumo sem permissão de acesso, retornar o status 403")
//            public void permissaoListarClientesResumo() throws Exception {
//                when(service.filtrar(filterResumo, pageable))
//                        .thenReturn(page);
//                mockMvc.perform(get("/clientes?resumo=resumo")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filterResumo, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Cliente usando o id sem permissão de acesso, retornar o status 403")
//            public void permissaoBuscarCliente() throws Exception {
//                when(service.detalhar(3L))
//                        .thenReturn(cliente);
//                mockMvc.perform(get("/clientes/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Cliente sem permissão de acesso, retornar a Cliente e status 403")
//            public void permissaoDeletarCliente() throws Exception {
//                mockMvc.perform(delete("/clientes/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Cliente sem permissão de acesso, retornar o status 403")
//            public void permissaoCriarCliente() throws Exception {
//                when(service.salvar(cliente))
//                        .thenReturn(cliente);
//
//                String jsonString = mapper.writeValueAsString(cliente);
//                mockMvc.perform(post("/clientes")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .salvar(Mockito.any(Cliente.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Cliente sem permissão de acesso, retornar o status 403")
//            public void permissaoAtualizarCliente() throws Exception {
//                when(service.atualizar(1L, cliente)).thenReturn(cliente);
//
//                String jsonString = mapper.writeValueAsString(cliente);
//                mockMvc.perform(put("/clientes/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .atualizar(1L, cliente);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, validações das entidades.")
//        class ValidacoesEntidade {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private ClienteService service;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Criar Cliente informando um nome null, retornar mensagem de erro e status 400.")
//            public void criarNomeNull() throws Exception {
//                when(service.salvar(Mockito.any(Cliente.class))).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(post("/clientes")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0))
//                        .salvar(nomeNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Cliente informando um nome null, retornar mensagem de erro e status 400.")
//            public void atualizarNomeNull() throws Exception {
//                when(service.atualizar(1L, nomeNull)).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(put("/clientes/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeNull);
//            }
//
//            @Test
//            @DisplayName("Criar Cliente informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void criarNomeVazio() throws Exception {
//                when(service.salvar(Mockito.any(Cliente.class))).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(post("/clientes")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .salvar(nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Atualizar Cliente informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void atualizarNomeVazio() throws Exception {
//                when(service.atualizar(1L, nomeVazio)).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(put("/clientes/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Criar Cliente informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAcima50Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(Cliente.class))).thenReturn(nomeMaior50);
//                String jsonString = mapper.writeValueAsString(nomeMaior50);
//                mockMvc.perform(post("/clientes")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .salvar(nomeMaior50);
//            }
//
//            @Test
//            @DisplayName("Atualizar Cliente informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAcima50Caracteres() throws Exception {
//                when(service.atualizar(3L, nomeMaior50)).thenReturn(nomeMaior50);
//                String jsonString = mapper.writeValueAsString(nomeMaior50);
//                mockMvc.perform(put("/clientes/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(3L, nomeMaior50);
//            }
//
//            @Test
//            @DisplayName("Criar Cliente informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAbaixo4Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(Cliente.class))).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(post("/clientes")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .salvar(nomeMenor4);
//            }
//
//            @Test
//            @DisplayName("Atualizar Cliente informando um nome abaixo 4 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAbaixo4Caracteres() throws Exception {
//                when(service.atualizar(1L, nomeMenor4)).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(put("/clientes/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeMenor4);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//
//            @Autowired
//            private ClienteRepository repository;
//
//            @AfterEach
//            void setUp() {
//                repository.deleteAll();
//            }
//
//            @Test
//            @DisplayName("Criar Cliente e persistir os dados, retornar: id, nome e descrição")
//            public void criar() {
//                Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
//                cliente = this.repository.save(cliente);
//                Cliente clienteNovo = repository.findById(cliente.getId()).orElse(null);
//                assert clienteNovo != null;
//                assertThat(clienteNovo.getId()).isNotNull();
//                assertEquals("nome", clienteNovo.getNome());
//                assertEquals("url", clienteNovo.getUrl());
//                assertEquals("resumo", clienteNovo.getResumo());
//            }
//
//            @Test
//            @DisplayName("Deletar uma CLiente")
//            public void deletar() {
//                Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
//                this.repository.save(cliente);
//                this.repository.delete(cliente);
//                assertThat(repository.findById(cliente.getId())).isEmpty();
//            }
//
//            @Test
//            @DisplayName("Atualizar uma CLiente e persistir os dados, retorno: id, nome e descrição")
//            public void atualizar() {
//                Cliente clienteAtualizado = this.repository.save(new Cliente(3L, "nomeNovo", "urlNovo", "resumoNovo", null, null, null, null, null));
//                assertThat(clienteAtualizado.getNome()).isEqualTo("nomeNovo");
//                assertThat(clienteAtualizado.getUrl()).isEqualTo("urlNovo");
//                assertThat(clienteAtualizado.getResumo()).isEqualTo("resumoNovo");
//            }
//
//            @Test
//            @DisplayName("Buscar CLiente por id, retorno: id, nome e descrição")
//            public void buscarPorId() {
//                Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
//                cliente = this.repository.save(cliente);
//                assertThat(cliente.getId()).isNotNull();
//                assertThat(cliente.getNome()).isEqualTo("nome");
//                assertThat(cliente.getUrl()).isEqualTo("url");
//                assertThat(cliente.getResumo()).isEqualTo("resumo");
//            }
//
//            @Test
//            @DisplayName("Salva todas as entidades fornecidas.")
//            public void salvarTodosEntidades() {
//                List<Cliente> clientes = Arrays.asList(new Cliente(null, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(null, "nome2", "url2", "resumo2", null, null, null, null, null));
//                List<Cliente> clienteSalvas = this.repository.saveAll(clientes);
//                assertThat(clienteSalvas.get(0).getId()).isNotNull();
//                assertThat(clienteSalvas.get(0).getNome()).isEqualTo("nome1");
//                assertThat(clienteSalvas.get(0).getUrl()).isEqualTo("url1");
//                assertThat(clienteSalvas.get(0).getResumo()).isEqualTo("resumo1");
//                assertThat(clienteSalvas.get(1).getId()).isNotNull();
//                assertThat(clienteSalvas.get(1).getNome()).isEqualTo("nome2");
//                assertThat(clienteSalvas.get(1).getUrl()).isEqualTo("url2");
//                assertThat(clienteSalvas.get(1).getResumo()).isEqualTo("resumo2");
//            }
//
//            @Test
//            @DisplayName("exclui uma determinada entidade.")
//            public void deletarEntidade() {
//                Cliente cliente = new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null);
//                this.repository.delete(cliente);
//                assertThat(repository.findById(cliente.getId())).isEmpty();
//            }
//
//            @Test
//            @DisplayName("Exclui as entidades fornecidas.")
//            public void deletarTodosPorEntidades() {
//                List<Cliente> clientes = Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome2", "url2", "resumo2", null, null, null, null, null));
//                this.repository.saveAll(clientes);
//                this.repository.deleteAll(clientes);
//                assertThat(repository.findById(1L)).isEmpty();
//                assertThat(repository.findById(2L)).isEmpty();
//            }
//
//
//            @Test
//            @DisplayName("Exclui todas as entidades gerenciadas pelo repositório.")
//            public void deletarTudo() {
//                List<Cliente> clientes = Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome2", "url2", "resumo2", null, null, null, null, null));
//                this.repository.saveAll(clientes);
//                this.repository.deleteAll();
//                assertThat(repository.findById(1L)).isEmpty();
//                assertThat(repository.findById(2L)).isEmpty();
//                assertThat(repository.findById(3L)).isEmpty();
//            }
//
//            @Test
//            @DisplayName("Retorna todas as instâncias do tipo.")
//            public void buscarTodosEntidades() {
//                List<Cliente> clientes = Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome2", "url2", "resumo2", null, null, null, null, null));
//                this.repository.saveAll(clientes);
//                List<Cliente> clientesBusca = this.repository.findAll();
//                assertThat(clientesBusca.get(0).getId()).isNotNull();
//                assertThat(clientesBusca.get(0).getNome()).isEqualTo("nome1");
//                assertThat(clientesBusca.get(0).getUrl()).isEqualTo("url1");
//                assertThat(clientesBusca.get(0).getResumo()).isEqualTo("resumo1");
//                assertThat(clientesBusca.get(1).getId()).isNotNull();
//                assertThat(clientesBusca.get(1).getNome()).isEqualTo("nome2");
//                assertThat(clientesBusca.get(1).getUrl()).isEqualTo("url2");
//                assertThat(clientesBusca.get(1).getResumo()).isEqualTo("resumo2");
//            }
//
//            @Test
//            @DisplayName("Seleciona varias entidades fornecendo os IDs existe.")
//            public void buscarCIds() {
//                List<Cliente> clientesSalvas = this.repository.saveAll(Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome2", "url2", "resumo2", null, null, null, null, null)));
//                List<Cliente> clientes = this.repository.findAllById(Arrays.asList(clientesSalvas.get(0).getId(), clientesSalvas.get(1).getId()));
//                assertThat(clientes.get(0).getId()).isNotNull();
//                assertThat(clientes.get(0).getNome()).isEqualTo("nome1");
//                assertThat(clientes.get(0).getUrl()).isEqualTo("url1");
//                assertThat(clientes.get(0).getResumo()).isEqualTo("resumo1");
//                assertThat(clientes.get(1).getId()).isNotNull();
//                assertThat(clientes.get(1).getNome()).isEqualTo("nome2");
//                assertThat(clientes.get(1).getUrl()).isEqualTo("url2");
//                assertThat(clientes.get(1).getResumo()).isEqualTo("resumo2");
//            }
//
//            @Test
//            @DisplayName("Verificar se exite o ID fornecido.")
//            public void verificarSeExistePorId() {
//                Cliente cliente = this.repository.save(new Cliente(3L, "nome", "url", "resumo", null, null, null, null, null));
//                assertTrue(this.repository.existsById(cliente.getId()));
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service.")
//        class Service {
//            @Autowired
//            private ClienteService service;
//
//            @Autowired
//            private ClienteRepository repository;
//
//            @AfterEach
//            void setUp() {
//                repository.deleteAll();
//            }
//
//            @Test
//            @DisplayName("Criar Campanha com nome, url e resumo, percistir os dados.")
//            public void Criar() {
//                Cliente cliente = this.service.salvar(new Cliente(1L, "nome", "url", "resumo", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Usuario(1L)));
//                assertThat(cliente.getId()).isNotNull();
//                assertEquals("nome", cliente.getNome());
//                assertEquals("url", cliente.getUrl());
//                assertEquals("resumo", cliente.getResumo());
//            }
//
//            @Test
//            @DisplayName("Deletar verificar se foi removido.")
//            public void deletar() {
//                Cliente cliente = this.service.salvar(new Cliente(1L, "nome", "url", "resumo", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Usuario(1L)));
//                service.deletar(cliente.getId());
//                assertThat(repository.findById(cliente.getId())).isEmpty();
//            }
//
//            @Test
//            @DisplayName("Detalhar retornar uma entidade selecionada por id de campanha.")
//            public void detalhar() {
//                Cliente clienteSalva = this.repository.save(new Cliente(1L, "nome", "url", "resumo", null, null, null, null, null));
//                Cliente cliente = service.deletar(clienteSalva.getId());
//                assertThat(cliente.getId()).isNotNull();
//                assertEquals("nome", cliente.getNome());
//                assertEquals("url", cliente.getUrl());
//                assertEquals("resumo", cliente.getResumo());
//            }
//
//            @Test
//            @DisplayName("Filtrar itens selecionados ")
//            public void filtrarPorTodos() {
//                repository.saveAll(Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome2", "url2", "resumo2", null, null, null, null, null)));
//                ClienteFilter filtro = new ClienteFilter("nome", "url", "resumo");
//                Page<Cliente> campanhaPage = service.filtrar(filtro, pageable);
//                assertEquals("nome1", campanhaPage.getContent().get(0).getNome());
//                assertEquals("url1", campanhaPage.getContent().get(0).getUrl());
//                assertEquals("resumo1", campanhaPage.getContent().get(0).getResumo());
//            }
//
//            @Test
//            @DisplayName("Filtrar itens selecionados filtrar por nome.")
//            public void filtrarPorNome() {
//                repository.saveAll(Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome2", "url2", "resumo2", null, null, null, null, null)));
//                ClienteFilter filtro = new ClienteFilter("nome", null, null);
//                Pageable pageable = PageRequest.of(0, 2);
//                Page<Cliente> clientePage = service.filtrar(filtro, pageable);
//                assertEquals("nome1", clientePage.getContent().get(0).getNome());
//                assertEquals("url1", clientePage.getContent().get(0).getUrl());
//                assertEquals("resumo1", clientePage.getContent().get(0).getResumo());
//                assertEquals("nome2", clientePage.getContent().get(1).getNome());
//                assertEquals("url2", clientePage.getContent().get(1).getUrl());
//                assertEquals("resumo2", clientePage.getContent().get(1).getResumo());
//                assertThat(clientePage.getSize()).isEqualTo(2);
//            }
//
//            @Test
//            @DisplayName("Filtrar itens selecionados filtrar por descrição.")
//            public void filtrarPorNomeEUrl() {
//                repository.saveAll(Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome", "url", "resumo", null, null, null, null, null)));
//                ClienteFilter filtro = new ClienteFilter("nome1", "url1", null);
//                Page<Cliente> campanhaPage = service.filtrar(filtro, pageable);
//                assertEquals("nome1", campanhaPage.getContent().get(0).getNome());
//                assertEquals("url1", campanhaPage.getContent().get(0).getUrl());
//                assertEquals("resumo1", campanhaPage.getContent().get(0).getResumo());
//            }
//
//            @Test
//            @DisplayName("Filtrar itens selecionados filtrar por descrição.")
//            public void filtrarPorNomeEResumo() {
//                repository.saveAll(Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome", "url", "resumo", null, null, null, null, null)));
//                ClienteFilter filtro = new ClienteFilter("nome1", null, "resumo1");
//                Page<Cliente> campanhaPage = service.filtrar(filtro, pageable);
//                assertEquals("nome1", campanhaPage.getContent().get(0).getNome());
//                assertEquals("url1", campanhaPage.getContent().get(0).getUrl());
//                assertEquals("resumo1", campanhaPage.getContent().get(0).getResumo());
//            }
//
//            @Test
//            @DisplayName("Filtrar itens selecionados filtrar por descrição.")
//            public void filtrarPorResumo() {
//                repository.saveAll(Arrays.asList(new Cliente(1L, "nome1", "url1", "resumo1", null, null, null, null, null), new Cliente(2L, "nome", "url", "resumo", null, null, null, null, null)));
//                ClienteFilter filtro = new ClienteFilter(null, null, "resumo1");
//                Page<Cliente> campanhaPage = service.filtrar(filtro, pageable);
//                assertEquals("nome1", campanhaPage.getContent().get(0).getNome());
//                assertEquals("url1", campanhaPage.getContent().get(0).getUrl());
//                assertEquals("resumo1", campanhaPage.getContent().get(0).getResumo());
//            }
//
//            @Test
//            @DisplayName("Atualizar uma entidade de cliente por id.")
//            public void atualizar() {
//                Cliente clienteSalva = this.service.salvar(new Cliente(1L, "nome", "url", "resumo", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Usuario(1L)));
//                Cliente cliente = this.service.atualizar(clienteSalva.getId(), new Cliente(clienteSalva.getId(), "nome2", "url2", "resumo2", null, null, null, null, null));
//                assertThat(cliente.getId()).isNotNull();
//                assertEquals("nome2", cliente.getNome());
//                assertEquals("url2", cliente.getUrl());
//                assertEquals("resumo2", cliente.getResumo());
//            }
//
//            @Test
//            @DisplayName("Criar Campanha com nome e descrição, percistir os dados.")
//            public void CriarComNomeNull() {
//                Exception exception = assertThrows(TransactionSystemException.class, () -> this.repository.save(new Cliente(1L, null, "url", "resumo", null, null, null, null, null)));
//                assertTrue(exception.getMessage().contains("Error while committing the transaction"));
//            }
////	this.repository.save(new Cliente(1L, "nome", "url", "resumo", Arrays.asList(Mockito.any(Contato.class)), Arrays.asList(Mockito.any(Negociacao.class)),
////			Arrays.asList(Mockito.any(Segmento.class)), Arrays.asList(Mockito.any(Usuario.class)), Mockito.any(Usuario.class))));
//        }
//    }
//
//    // TODO: Falta fazer os testes de service e repository
//    @Nested
//    @DisplayName("Contato")
//    class ContatoTestes {
//        // TODO filter carregado;
//        private final ContatoFilter filter = new ContatoFilter();
//        private final ContatoFilter filterNome = new ContatoFilter("nome", null);
//        private final ContatoFilter filterCargo = new ContatoFilter(null, "cargo");
//        private final Contato contato = new Contato(3L, "nome", "cargo", null, null, null);
//        private final Contato nomeNull = new Contato(3L, null, "cargo", null, null, null);
//        private final Contato nomeVazio = new Contato(3L, "", "cargo", null, null, null);
//        private final Contato nomeMaior50 = new Contato(null, "nome nome nome nome nome nome nome nome nome nome nome nome", "cargo", null, null, null);
//        private final Contato nomeMenor4 = new Contato(null, "nom", "cargo", null, null, null);
//        private final Contato cargoMaior50 = new Contato(null, "nome", "cargo cargo cargo cargo cargo cargo cargo cargo cargo cargo", null, null, null);
//
//        @Nested
//        @DisplayName("Testando End Point, token valido.")
//        class TokenValido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private ContatoService service;
//
//            @Mock
//            private Page<Contato> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Contatos, retornar a Contatos e status 200")
//            public void listar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/contatos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Contatos usando filtro pelo nome, retornar a Contatos e status 200")
//            public void listarNome() throws Exception {
//                when(service.filtrar(filterNome, pageable)).thenReturn(page);
//                mockMvc.perform(get("/contatos?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Contatos usando filtro pela cargo, retornar a Contatos e status 200")
//            public void listarCargo() throws Exception {
//                when(service.filtrar(filterCargo, pageable)).thenReturn(page);
//                mockMvc.perform(get("/contatos?cargo=cargo")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterCargo, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Contatos usando o id, retornar a Contatos e status 200 sucesso")
//            public void buscar() throws Exception {
//                when(service.detalhar(3L)).thenReturn(contato);
//                mockMvc.perform(get("/contatos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"))
//                        .andExpect(jsonPath("$.cargo").value("cargo"));
//                verify(service, times(1))
//                        .detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Contatos, retornar a Contato e status 200")
//            public void deletar() throws Exception {
//                mockMvc.perform(delete("/contatos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Contatos, retornar a Contatos e status 201")
//            public void criar() throws Exception {
//                when(service.salvar(contato)).thenReturn(contato);
//                String jsonString = mapper.writeValueAsString(contato);
//                mockMvc.perform(post("/contatos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"))
//                        .andExpect(jsonPath("$.cargo").value("cargo"));
//                verify(service, times(1))
//                        .salvar(Mockito.any(Contato.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Contatos, retornar a Contatos e status 201")
//            public void atualizar() throws Exception {
//                when(service.atualizar(3L, contato)).thenReturn(contato);
//                String jsonString = mapper.writeValueAsString(contato);
//                mockMvc.perform(put("/contatos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"))
//                        .andExpect(jsonPath("$.cargo").value("cargo"));
//                verify(service, times(1))
//                        .atualizar(3L, contato);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, token invalido.")
//        class TokenInvalido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private ContatoService service;
//
//            @Mock
//            private Page<Contato> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Contato com usuario e senha incorretos, retornar status 401")
//            public void listamosTokenIncorreto() throws Exception {
//                when(service.filtrar(filter, pageable))
//                        .thenReturn(page);
//                mockMvc.perform(get("/contatos")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Contato usando usuario e senha incorretos, retornar status 401")
//            public void buscarTokenIncorreto() throws Exception {
//                when(service.detalhar(3L)).thenReturn(contato);
//                mockMvc.perform(get("/contatos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Contato com usuario e senha incorretos, retornar status 401")
//            public void deletarTokenIncorreto() throws Exception {
//                when(service.deletar(3L)).thenReturn(contato);
//                mockMvc.perform(delete("/contatos/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Contato com usuario e senha incorretos, retornar status 401")
//            public void criarTokenIncorreto() throws Exception {
//                when(service.salvar(contato)).thenReturn(contato);
//                String jsonString = mapper.writeValueAsString(contato);
//                mockMvc.perform(post("/contatos")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(3L, contato);
//            }
//
//            @Test
//            @DisplayName("Atualizar Contato com usuario e senha incorretos, retornar status 401")
//            public void atualizarTokenIncorreto() throws Exception {
//                when(service.atualizar(3L, contato)).thenReturn(contato);
//                String jsonString = mapper.writeValueAsString(contato);
//                mockMvc.perform(put("/contatos/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(3L, contato);
//            }
//
//            @Test
//            @DisplayName("Listar Contatos sem token, retornar status 401")
//            public void listamosSemToken() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/contatos"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Contatos sem token, retornar status 401")
//            public void buscarSemToken() throws Exception {
//                when(service.detalhar(3L)).thenReturn(contato);
//                mockMvc.perform(get("/contatos/{id}", 3L))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Contato sem token, retornar status 401")
//            public void deletarSemToken() throws Exception {
//                when(service.deletar(3L)).thenReturn(contato);
//                mockMvc.perform(delete("/contatos/1"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Contato sem token, retornar status 401")
//            public void criarSemToken() throws Exception {
//                when(service.salvar(contato)).thenReturn(contato);
//                String jsonString = mapper.writeValueAsString(contato);
//                mockMvc.perform(post("/contatos")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .salvar(contato);
//            }
//
//            @Test
//            @DisplayName("Atualizar Contato sem token, retornar status 401")
//            public void atualizarSemToken() throws Exception {
//                when(service.atualizar(3L, contato)).thenReturn(contato);
//                String jsonString = mapper.writeValueAsString(contato);
//                mockMvc.perform(put("/contatos/1")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .atualizar(3L, contato);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, acesso sem permissão.")
//        class SemPermissao {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private ContatoService service;
//
//            @Mock
//            private Page<Contato> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Contatos sem permissão de acesso, retornar o status 403")
//            public void permissaoListar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/contatos")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Contatos usando filtro pelo nome sem permissão de acesso, retornar o status 403")
//            public void permissaoListarsNome() throws Exception {
//                when(service.filtrar(filterNome, pageable)).thenReturn(page);
//                mockMvc.perform(get("/contatos?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Contatos usando filtro pela cargo sem permissão de acesso, retornar o status 403")
//            public void permissaoListarCargo() throws Exception {
//                ContatoFilter campanhaFilter = new ContatoFilter(null, "cargo");
//                when(service.filtrar(campanhaFilter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/contatos?cargo=cargo")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(campanhaFilter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Contatos usando o id sem permissão de acesso, retornar o status 403")
//            public void permissaoBuscar() throws Exception {
//                when(service.detalhar(3L)).thenReturn(contato);
//                mockMvc.perform(get("/contatos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Contato sem permissão de acesso, retornar a Contato e status 403")
//            public void permissaoDeletar() throws Exception {
//                mockMvc.perform(delete("/contatos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Contato sem permissão de acesso, retornar o status 403")
//            public void permissaoCriar() throws Exception {
//                when(service.salvar(contato)).thenReturn(contato);
//                String jsonString = mapper.writeValueAsString(contato);
//                mockMvc.perform(post("/contatos")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .salvar(Mockito.any(Contato.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Contato sem permissão de acesso, retornar o status 403")
//            public void permissaoAtualizar() throws Exception {
//                when(service.atualizar(3L, contato)).thenReturn(contato);
//                String jsonString = mapper.writeValueAsString(contato);
//                mockMvc.perform(put("/contatos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .atualizar(3L, contato);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, validações das entidades.")
//        class ValidacoesEntidade {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private ContatoService service;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Criar Contato informando um nome null, retornar mensagem de erro e status 400.")
//            public void criarNomeNull() throws Exception {
//                when(service.salvar(Mockito.any(Contato.class))).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(post("/contatos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0))
//                        .salvar(nomeNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Contato informando um nome null, retornar mensagem de erro e status 400.")
//            public void atualizarNomeNull() throws Exception {
//                when(service.atualizar(3L, nomeNull)).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(put("/contatos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0))
//                        .atualizar(3L, nomeNull);
//            }
//
//            @Test
//            @DisplayName("Criar Contato informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void criarNomeVazio() throws Exception {
//                when(service.salvar(Mockito.any(Contato.class))).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(post("/contatos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .salvar(nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Atualizar Contato informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void atualizarNomeVazio() throws Exception {
//                when(service.atualizar(3L, nomeVazio)).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(put("/contatos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(3L, nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Criar Contato informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAcima50Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(Contato.class))).thenReturn(nomeMaior50);
//                String jsonString = mapper.writeValueAsString(nomeMaior50);
//                mockMvc.perform(post("/contatos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .salvar(nomeMaior50);
//            }
//
//            @Test
//            @DisplayName("Atualizar Contato informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAcima50Caracteres() throws Exception {
//                when(service.atualizar(3L, nomeMaior50)).thenReturn(nomeMaior50);
//                String jsonString = mapper.writeValueAsString(nomeMaior50);
//                mockMvc.perform(put("/contatos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(3L, nomeMaior50);
//            }
//
//            @Test
//            @DisplayName("Criar Contato informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAbaixo4Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(Contato.class))).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(post("/contatos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .salvar(nomeMenor4);
//            }
//
//            @Test
//            @DisplayName("Atualizar Contato informando um nome abaixo 4 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAbaixo4Caracteres() throws Exception {
//                when(service.atualizar(3L, nomeMenor4)).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(put("/contatos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(3L, nomeMenor4);
//            }
//
//            @Test
//            @DisplayName("Criar Contato informando um cargo acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void criarCargoAcima50Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(Contato.class))).thenReturn(cargoMaior50);
//                String jsonString = mapper.writeValueAsString(cargoMaior50);
//                mockMvc.perform(post("/contatos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("cargo"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O cargo não deve ultrapassar os 50 caracteres."));
//                verify(service, times(0))
//                        .salvar(cargoMaior50);
//            }
//
//            @Test
//            @DisplayName("Atualizar Contato informando um cargo acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarCargoAcima50Caracteres() throws Exception {
//                when(service.atualizar(3L, cargoMaior50)).thenReturn(cargoMaior50);
//                String jsonString = mapper.writeValueAsString(cargoMaior50);
//                mockMvc.perform(put("/contatos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("cargo"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O cargo não deve ultrapassar os 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(3L, cargoMaior50);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service")
//        class Service {
//        }
//    }
//
//    // TODO: Falta fazer os testes de service e repository
//    @Nested
//    @DisplayName("Email")
//    class EmailTestes {
//        private final EmailFilter filter = new EmailFilter();
//        private final EmailFilter emailFilter = new EmailFilter("email@gmail.com");
//        private final Email email = new Email(3L, "email@gmail.com", new Contato(1L));
//        private final Email emailNull = new Email(null, null, new Contato(1L));
//        private final Email emailVazio = new Email(null, "", new Contato(1L));
//        private final Email emailInvalido = new Email(null, "emailgmail.com", new Contato(1L));
//
//        @Nested
//        @DisplayName("Testando End Point, token valido.")
//        class TokenValido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private EmailService service;
//
//            @Mock
//            private Page<Email> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Emails, retornar a Emails e status 200")
//            public void listar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/emails")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Emails usando filtro pelo email, retornar a Emails e status 200")
//            public void listarEmail() throws Exception {
//                when(service.filtrar(emailFilter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/emails?email=email@gmail.com")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(emailFilter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Emails usando o id, retornar a Emails e status 200 sucesso")
//            public void buscar() throws Exception {
//                when(service.detalhar(3L)).thenReturn(email);
//                mockMvc.perform(get("/emails/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.email").value("email@gmail.com"));
//                verify(service, times(1))
//                        .detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Emails, retornar a Email e status 200")
//            public void deletar() throws Exception {
//                mockMvc.perform(delete("/emails/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Emails, retornar a Emails e status 201")
//            public void criar() throws Exception {
//                when(service.salvar(email)).thenReturn(email);
//                String jsonString = mapper.writeValueAsString(email);
//                mockMvc.perform(post("/emails")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.email").value("email@gmail.com"));
//                verify(service, times(1))
//                        .salvar(Mockito.any(Email.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Emails, retornar a Emails e status 201")
//            public void atualizar() throws Exception {
//                when(service.atualizar(1L, email)).thenReturn(email);
//                String jsonString = mapper.writeValueAsString(email);
//                mockMvc.perform(put("/emails/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.email").value("email@gmail.com"));
//                verify(service, times(1))
//                        .atualizar(1L, email);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, token invalido.")
//        class TokenInvalido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private EmailService service;
//
//            @Mock
//            private Page<Email> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Email com usuario e senha incorretos, retornar status 401")
//            public void listamosTokenIncorreto() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/emails")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Email usando usuario e senha incorretos, retornar status 401")
//            public void buscarTokenIncorreto() throws Exception {
//                when(service.detalhar(3L)).thenReturn(email);
//                mockMvc.perform(get("/emails/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Email com usuario e senha incorretos, retornar status 401")
//            public void deletarTokenIncorreto() throws Exception {
//                when(service.deletar(3L)).thenReturn(email);
//                mockMvc.perform(delete("/emails/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Email com usuario e senha incorretos, retornar status 401")
//            public void criarTokenIncorreto() throws Exception {
//                when(service.salvar(email)).thenReturn(email);
//                String jsonString = mapper.writeValueAsString(email);
//                mockMvc.perform(post("/emails")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, email);
//            }
//
//            @Test
//            @DisplayName("Atualizar Email com usuario e senha incorretos, retornar status 401")
//            public void atualizarTokenIncorreto() throws Exception {
//                when(service.atualizar(1L, email)).thenReturn(email);
//                String jsonString = mapper.writeValueAsString(email);
//                mockMvc.perform(put("/emails/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, email);
//            }
//
//            @Test
//            @DisplayName("Listar Emails sem token, retornar status 401")
//            public void listamosSemToken() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/emails"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Emails sem token, retornar status 401")
//            public void buscarSemToken() throws Exception {
//                when(service.detalhar(3L)).thenReturn(email);
//                mockMvc.perform(get("/emails/{id}", 3L))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Email sem token, retornar status 401")
//            public void deletarSemToken() throws Exception {
//                when(service.deletar(3L)).thenReturn(email);
//                mockMvc.perform(delete("/emails/1"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Email sem token, retornar status 401")
//            public void criarSemToken() throws Exception {
//                when(service.salvar(email)).thenReturn(email);
//                String jsonString = mapper.writeValueAsString(email);
//                mockMvc.perform(post("/emails")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .salvar(email);
//            }
//
//            @Test
//            @DisplayName("Atualizar Email sem token, retornar status 401")
//            public void atualizarSemToken() throws Exception {
//                when(service.atualizar(1L, email)).thenReturn(email);
//                String jsonString = mapper.writeValueAsString(email);
//                mockMvc.perform(put("/emails/1")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .atualizar(1L, email);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, acesso sem permissão.")
//        class SemPermissao {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private EmailService service;
//
//            @Mock
//            private Page<Email> page;
//
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Emails sem permissão de acesso, retornar o status 403")
//            public void permissaoListar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/emails")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Emails usando filtro pela email sem permissão de acesso, retornar o status 403")
//            public void permissaoListarEmail() throws Exception {
//                when(service.filtrar(emailFilter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/emails?cargo=cargo")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(emailFilter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Emails usando o id sem permissão de acesso, retornar o status 403")
//            public void permissaoBuscar() throws Exception {
//                when(service.detalhar(3L)).thenReturn(email);
//                mockMvc.perform(get("/emails/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Email sem permissão de acesso, retornar a Email e status 403")
//            public void permissaoDeletar() throws Exception {
//                mockMvc.perform(delete("/emails/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Email sem permissão de acesso, retornar o status 403")
//            public void permissaoCriar() throws Exception {
//                when(service.salvar(email)).thenReturn(email);
//                String jsonString = mapper.writeValueAsString(email);
//                mockMvc.perform(post("/emails")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .salvar(Mockito.any(Email.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Email sem permissão de acesso, retornar o status 403")
//            public void permissaoAtualizar() throws Exception {
//                when(service.atualizar(1L, email)).thenReturn(email);
//                String jsonString = mapper.writeValueAsString(email);
//                mockMvc.perform(put("/emails/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .atualizar(1L, email);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, validações das entidades.")
//        class ValidacoesEntidade {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private EmailService service;
//
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Criar Email informando um email null, retornar mensagem de erro e status 400.")
//            public void criarEmailNull() throws Exception {
//                when(service.salvar(Mockito.any(Email.class))).thenReturn(emailNull);
//                String jsonString = mapper.writeValueAsString(emailNull);
//                mockMvc.perform(post("/emails")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("email"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O email não pode ser null."));
//                verify(service, times(0))
//                        .salvar(emailNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Email informando um email null, retornar mensagem de erro e status 400.")
//            public void atualizarEmailNull() throws Exception {
//                when(service.atualizar(3L, emailNull)).thenReturn(emailNull);
//                String jsonString = mapper.writeValueAsString(emailNull);
//                mockMvc.perform(put("/emails/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("email"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O email não pode ser null."));
//                verify(service, times(0))
//                        .salvar(emailNull);
//            }
//
//            @Test
//            @DisplayName("Criar Email informando um email vazio, retornar mensagem de erro e status 400.")
//            public void criarEmailVazio() throws Exception {
//                when(service.salvar(Mockito.any(Email.class))).thenReturn(emailVazio);
//                String jsonString = mapper.writeValueAsString(emailVazio);
//                mockMvc.perform(post("/emails")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("email"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O email não pode ser null."));
//                verify(service, times(0))
//                        .salvar(emailVazio);
//            }
//
//            @Test
//            @DisplayName("Atualizar Email informando um email vazio, retornar mensagem de erro e status 400.")
//            public void atualizarEmailVazio() throws Exception {
//                when(service.atualizar(3L, emailVazio)).thenReturn(emailVazio);
//                String jsonString = mapper.writeValueAsString(emailVazio);
//                mockMvc.perform(put("/emails/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("email"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O email não pode ser null."));
//                verify(service, times(0))
//                        .salvar(emailVazio);
//            }
//
//            @Test
//            @DisplayName("Criar Email informando um email invalido, retornar mensagem de erro e status 400.")
//            public void criarEmailInvalido() throws Exception {
//                when(service.salvar(Mockito.any(Email.class))).thenReturn(emailInvalido);
//                String jsonString = mapper.writeValueAsString(emailInvalido);
//                mockMvc.perform(post("/emails")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("email"))
//                        .andExpect(jsonPath("$.fieldMessage").value("Verifique o formato do endereço de e-mail."));
//                verify(service, times(0))
//                        .salvar(emailInvalido);
//            }
//
//            @Test
//            @DisplayName("Atualizar Email informando um email invalido, retornar mensagem de erro e status 400.")
//            public void atualizarEmailInvalido() throws Exception {
//                when(service.atualizar(3L, emailInvalido)).thenReturn(emailInvalido);
//                String jsonString = mapper.writeValueAsString(emailInvalido);
//                mockMvc.perform(put("/emails/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("email"))
//                        .andExpect(jsonPath("$.fieldMessage").value("Verifique o formato do endereço de e-mail."));
//                verify(service, times(0))
//                        .salvar(emailInvalido);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service")
//        class Service {
//        }
//    }
//
//    // TODO: Falta fazer os testes de service e repository
//    @Nested
//    @DisplayName("Estagio Negociação")
//    class EstagioNegociacaoTestes {
//        private final EstagioNegociacaoFilter filter = new EstagioNegociacaoFilter();
//        private final EstagioNegociacaoFilter filterNome = new EstagioNegociacaoFilter("nome", null, null);
//        private final EstagioNegociacaoFilter filterApelido = new EstagioNegociacaoFilter(null, "apelido", null);
//        private final EstagioNegociacaoFilter filterPosicao = new EstagioNegociacaoFilter(null, null, 1);
//        private final EstagioNegociacao estagio = new EstagioNegociacao(null, "nome", "apelido", 1, null);
//        private final EstagioNegociacao nomeNull = new EstagioNegociacao(null, null, "apelido", 1, null);
//        private final EstagioNegociacao nomeVazio = new EstagioNegociacao(1L, "", "apelido", 1, null);
//        private final EstagioNegociacao nomeMaior50 = new EstagioNegociacao(null, "nome nome nome nome nome nome nome nome nome nome nome nome", "apelido", 1, null);
//        private final EstagioNegociacao nomeMenor4 = new EstagioNegociacao(null, "nom", "apelido", 1, null);
//        private final EstagioNegociacao apelidoNull = new EstagioNegociacao(null, "nome", null, 1, null);
//        private final EstagioNegociacao apelidoMaior10 = new EstagioNegociacao(null, "nome", "asdasdasdaa", 1, null);
//        private final EstagioNegociacao posicaoNull = new EstagioNegociacao(null, "nome", "aplido", null, null);
//
//        @Nested
//        @DisplayName("Testando End Point, token valido.")
//        class TokenValido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private EstagioNegociacaoService service;
//
//            @Mock
//            private Page<EstagioNegociacao> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Estagios da Negociacao, retornar a Estagio da Negociacao e status 200")
//            public void listar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/estagios")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Estagios da Negociacao usando filtro pelo nome, retornar a Estagio da Negociacao e status 200")
//            public void listarNome() throws Exception {
//                when(service.filtrar(filterNome, pageable)).thenReturn(page);
//                mockMvc.perform(get("/estagios?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Estagios da Negociacao usando filtro pela apelido, retornar a Estagio da Negociacao e status 200")
//            public void listarApelido() throws Exception {
//                when(service.filtrar(filterApelido, pageable)).thenReturn(page);
//                mockMvc.perform(get("/estagios?apelido=apelido")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterApelido, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Estagios da Negociacao usando filtro pela posicao, retornar a Estagio da Negociacao e status 200")
//            public void listarPosicao() throws Exception {
//                when(service.filtrar(filterPosicao, pageable)).thenReturn(page);
//                mockMvc.perform(get("/estagios?posicao=1")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterPosicao, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Estagios da Negociacao usando o id, retornar a Estagio da Negociacao e status 200 sucesso")
//            public void buscar() throws Exception {
//                when(service.detalhar(1L)).thenReturn(estagio);
//                mockMvc.perform(get("/estagios/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"))
//                        .andExpect(jsonPath("$.apelido").value("apelido"));
//                verify(service, times(1)).detalhar(1L);
//            }
//
//            @Test
//            @DisplayName("Deletar Estagios da Negociacao, retornar a EstagioNegociacao e status 200")
//            public void deletar() throws Exception {
//                mockMvc.perform(delete("/estagios/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Estagios da Negociacao, retornar a EstagioNegociacao e status 201")
//            public void criar() throws Exception {
//                when(service.salvar(estagio)).thenReturn(estagio);
//                String jsonString = mapper.writeValueAsString(estagio);
//                mockMvc.perform(post("/estagios")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"))
//                        .andExpect(jsonPath("$.apelido").value("apelido"));
//                verify(service, times(1)).salvar(Mockito.any(EstagioNegociacao.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Estagios da Negociacao, retornar a Estagio da Negociacao e status 201")
//            public void atualizar() throws Exception {
//                when(service.atualizar(1L, estagio)).thenReturn(estagio);
//                String jsonString = mapper.writeValueAsString(estagio);
//                mockMvc.perform(put("/estagios/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"))
//                        .andExpect(jsonPath("$.apelido").value("apelido"));
//                verify(service, times(1))
//                        .atualizar(1L, estagio);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, token invalido.")
//        class TokenInvalido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private EstagioNegociacaoService service;
//
//            @Mock
//            private Page<EstagioNegociacao> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Estagio da Negociacao com usuario e senha incorretos, retornar status 401")
//            public void listamosTokenIncorreto() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/estagios")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Estagio da Negociacao usando usuario e senha incorretos, retornar status 401")
//            public void buscarTokenIncorreto() throws Exception {
//                when(service.detalhar(1L)).thenReturn(estagio);
//                mockMvc.perform(get("/estagios/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).detalhar(1L);
//            }
//
//            @Test
//            @DisplayName("Deletar Estagio da Negociacao com usuario e senha incorretos, retornar status 401")
//            public void deletarTokenIncorreto() throws Exception {
//                when(service.deletar(1L)).thenReturn(estagio);
//                mockMvc.perform(delete("/estagios/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Estagio da Negociacao com usuario e senha incorretos, retornar status 401")
//            public void criarTokenIncorreto() throws Exception {
//                when(service.salvar(estagio)).thenReturn(estagio);
//                String jsonString = mapper.writeValueAsString(estagio);
//                mockMvc.perform(post("/estagios")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, estagio);
//            }
//
//            @Test
//            @DisplayName("Atualizar Estagio da Negociacao com usuario e senha incorretos, retornar status 401")
//            public void atualizarTokenIncorreto() throws Exception {
//                when(service.atualizar(1L, estagio)).thenReturn(estagio);
//                String jsonString = mapper.writeValueAsString(estagio);
//                mockMvc.perform(put("/estagios/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, estagio);
//            }
//
//            @Test
//            @DisplayName("Listar Estagio da Negociacao sem token, retornar status 401")
//            public void listamosSemToken() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/estagios"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Estagio da Negociacao sem token, retornar status 401")
//            public void buscarSemToken() throws Exception {
//                when(service.detalhar(1L)).thenReturn(estagio);
//                mockMvc.perform(get("/estagios/{id}", 1L))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).detalhar(1L);
//            }
//
//            @Test
//            @DisplayName("Deletar Estagio da Negociacao sem token, retornar status 401")
//            public void deletarSemToken() throws Exception {
//                when(service.deletar(1L)).thenReturn(estagio);
//                mockMvc.perform(delete("/estagios/1"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Estagio da Negociacao sem token, retornar status 401")
//            public void criarSemToken() throws Exception {
//                when(service.salvar(estagio)).thenReturn(estagio);
//                String jsonString = mapper.writeValueAsString(estagio);
//                mockMvc.perform(post("/estagios")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).salvar(estagio);
//            }
//
//            @Test
//            @DisplayName("Atualizar Estagio da Negociacao sem token, retornar status 401")
//            public void atualizarSemToken() throws Exception {
//                when(service.atualizar(1L, estagio)).thenReturn(estagio);
//                String jsonString = mapper.writeValueAsString(estagio);
//                mockMvc.perform(put("/estagios/1")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .atualizar(1L, estagio);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, acesso sem permissão.")
//        class SemPermissao {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private EstagioNegociacaoService service;
//
//            @Mock
//            private Page<EstagioNegociacao> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Estagios da Negociacao sem permissão de acesso, retornar o status 403")
//            public void permissaoListar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/estagios")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Estagios da Negociacao usando filtro pelo nome sem permissão de acesso, retornar o status 403")
//            public void permissaoListarsNome() throws Exception {
//                when(service.filtrar(filterNome, pageable)).thenReturn(page);
//                mockMvc.perform(get("/estagios?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Estagios da Negociacao usando filtro pela apelido sem permissão de acesso, retornar o status 403")
//            public void permissaoListarCargo() throws Exception {
//                when(service.filtrar(filterApelido, pageable)).thenReturn(page);
//                mockMvc.perform(get("/estagios?apelido=apelido")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filterApelido, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Estagios da Negociacao usando filtro pela posição sem permissão de acesso, retornar o status 403")
//            public void permissaoListarPosicao() throws Exception {
//                when(service.filtrar(filterPosicao, pageable)).thenReturn(page);
//                mockMvc.perform(get("/estagios?posicao=1")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filterPosicao, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Estagios da Negociacao usando o id sem permissão de acesso, retornar o status 403")
//            public void permissaoBuscar() throws Exception {
//                when(service.detalhar(1L)).thenReturn(estagio);
//                mockMvc.perform(get("/estagios/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).detalhar(1L);
//            }
//
//            @Test
//            @DisplayName("Deletar Estagio da Negociacao sem permissão de acesso, retornar a Estagio da Negociacao e status 403")
//            public void permissaoDeletar() throws Exception {
//                mockMvc.perform(delete("/estagios/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print());
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Estagio da Negociacao sem permissão de acesso, retornar o status 403")
//            public void permissaoCriar() throws Exception {
//                when(service.salvar(estagio)).thenReturn(estagio);
//                String jsonString = mapper.writeValueAsString(estagio);
//                mockMvc.perform(post("/estagios")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).salvar(estagio);
//            }
//
//            @Test
//            @DisplayName("Atualizar Estagio da Negociacao sem permissão de acesso, retornar o status 403")
//            public void permissaoAtualizar() throws Exception {
//                when(service.atualizar(1L, estagio)).thenReturn(estagio);
//                String jsonString = mapper.writeValueAsString(estagio);
//                mockMvc.perform(put("/estagios/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .atualizar(1L, estagio);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, validações das entidades.")
//        class ValidacoesEntidade {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private EstagioNegociacaoService service;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Criar Estagio da Negociacao informando um nome null, retornar mensagem de erro e status 400.")
//            public void criarNomeNull() throws Exception {
//                when(service.salvar(Mockito.any(EstagioNegociacao.class))).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(post("/estagios")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0)).salvar(nomeNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Estagio da Negociacao informando um nome null, retornar mensagem de erro e status 400.")
//            public void atualizarNomeNull() throws Exception {
//                when(service.atualizar(1L, nomeNull)).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(put("/estagios/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0)).salvar(nomeNull);
//            }
//
//            @Test
//            @DisplayName("Criar Estagio da Negociacao informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void criarNomeVazio() throws Exception {
//                when(service.salvar(Mockito.any(EstagioNegociacao.class))).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(post("/estagios")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).salvar(nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Atualizar Estagio da Negociacao informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void atualizarNomeVazio() throws Exception {
//                when(service.atualizar(1L, nomeVazio)).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(put("/estagios/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).atualizar(1L, nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Criar Estagio da Negociacao informando um apelido null, retornar mensagem de erro e status 400.")
//            public void criarApelidoNull() throws Exception {
//                when(service.salvar(Mockito.any(EstagioNegociacao.class))).thenReturn(apelidoNull);
//                String jsonString = mapper.writeValueAsString(apelidoNull);
//                mockMvc.perform(post("/estagios")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("apelido"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O apelido não pode pode ser null."));
//                verify(service, times(0)).salvar(apelidoNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Estagio da Negociacao informando um apelido null, retornar mensagem de erro e status 400.")
//            public void atualizarApelidoNull() throws Exception {
//                when(service.atualizar(1L, apelidoNull)).thenReturn(apelidoNull);
//                String jsonString = mapper.writeValueAsString(apelidoNull);
//                mockMvc.perform(put("/estagios/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("apelido"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O apelido não pode pode ser null."));
//                verify(service, times(0)).salvar(apelidoNull);
//            }
//
//            @Test
//            @DisplayName("Criar Estagio da Negociacao informando um posição null, retornar mensagem de erro e status 400.")
//            public void criarPosicaoNull() throws Exception {
//                when(service.salvar(Mockito.any(EstagioNegociacao.class))).thenReturn(posicaoNull);
//                String jsonString = mapper.writeValueAsString(posicaoNull);
//                mockMvc.perform(post("/estagios")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("posicao"))
//                        .andExpect(jsonPath("$.fieldMessage").value("A posição não poder ser null."));
//                verify(service, times(0)).salvar(posicaoNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Estagio da Negociacao informando um posição null, retornar mensagem de erro e status 400.")
//            public void atualizarPosicaoNull() throws Exception {
//                when(service.atualizar(1L, posicaoNull)).thenReturn(posicaoNull);
//                String jsonString = mapper.writeValueAsString(posicaoNull);
//                mockMvc.perform(put("/estagios/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("posicao"))
//                        .andExpect(jsonPath("$.fieldMessage").value("A posição não poder ser null."));
//                verify(service, times(0)).salvar(posicaoNull);
//            }
//
//            @Test
//            @DisplayName("Criar Estagio da Negociacao informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAcima50Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(EstagioNegociacao.class))).thenReturn(nomeMaior50);
//                String jsonString = mapper.writeValueAsString(nomeMaior50);
//                mockMvc.perform(post("/estagios")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).salvar(nomeMaior50);
//            }
//
//            @Test
//            @DisplayName("Atualizar Estagio da Negociacao informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAcima50Caracteres() throws Exception {
//                when(service.atualizar(1L, nomeMaior50)).thenReturn(nomeMaior50);
//                String jsonString = mapper.writeValueAsString(nomeMaior50);
//                mockMvc.perform(put("/estagios/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).atualizar(1L, nomeMaior50);
//            }
//
//            @Test
//            @DisplayName("Criar Estagio da Negociacao informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAbaixo4Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(EstagioNegociacao.class))).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(post("/estagios")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).salvar(nomeMenor4);
//            }
//
//            @Test
//            @DisplayName("Atualizar Estagio da Negociacao informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAbaixo4Caracteres() throws Exception {
//                when(service.atualizar(1L, nomeMenor4)).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(put("/estagios/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).atualizar(1L, nomeMenor4);
//            }
//
//            @Test
//            @DisplayName("Criar Estagio da Negociacao informando um apelido acima de 10 caracteres, retornar mensagem de erro e status 400.")
//            public void criarApelidoAcima10Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(EstagioNegociacao.class))).thenReturn(apelidoMaior10);
//                String jsonString = mapper.writeValueAsString(apelidoMaior10);
//                mockMvc.perform(post("/estagios")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("apelido"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O apelido não pode ter menos de 10 caracteres."));
//                verify(service, times(0)).salvar(apelidoMaior10);
//            }
//
//            @Test
//            @DisplayName("Atualizar Estagio da Negociacao informando um apelido acima de 10 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarApelidoAcima10Caracteres() throws Exception {
//                when(service.atualizar(1L, apelidoMaior10)).thenReturn(apelidoMaior10);
//                String jsonString = mapper.writeValueAsString(apelidoMaior10);
//                mockMvc.perform(put("/estagios/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("apelido"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O apelido não pode ter menos de 10 caracteres."));
//                verify(service, times(0)).atualizar(1L, apelidoMaior10);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service")
//        class Service {
//        }
//    }
//
//    // TODO: Falta fazer os testes de service e repository
//    @Nested
//    @DisplayName("Fonte Negociação")
//    class FonteNegociacaoTestes {
//        private final FonteNegociacaoFilter filter = new FonteNegociacaoFilter();
//        private final FonteNegociacaoFilter filterNome = new FonteNegociacaoFilter("nome");
//        private final FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
//        private final FonteNegociacao nomeNull = new FonteNegociacao(1L, null, null);
//        private final FonteNegociacao nomeVazio = new FonteNegociacao(null, "", null);
//        private final FonteNegociacao nomeMenor4 = new FonteNegociacao(null, "nome nome nome nome nome nome nome nome nome nome nome nome", null);
//        private final FonteNegociacao nomeMaior50 = new FonteNegociacao(null, "nom", null);
//
//        @Nested
//        @DisplayName("Testando o token valido.")
//        class TokenValido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private FonteNegociacaoService service;
//
//            @Mock
//            private Page<FonteNegociacao> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Fonte Negociacaos, retornar a Fonte Negociacaos e status 200")
//            public void listar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/fontes")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar FonteNegociacaos usando filtro pelo nome, retornar a FonteNegociacaos e status 200")
//            public void listarNome() throws Exception {
//                when(service.filtrar(filterNome, pageable)).thenReturn(page);
//                mockMvc.perform(get("/fontes?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Fonte Negociacaos usando o id, retornar a Fonte Negociacaos e status 200 sucesso")
//            public void buscar() throws Exception {
//                FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
//                when(service.detalhar(1L)).thenReturn(fonteNegociacao);
//                mockMvc.perform(get("/fontes/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"));
//                verify(service, times(1)).detalhar(1L);
//            }
//
//            @Test
//            @DisplayName("Deletar Fonte Negociacaos, retornar a Fonte Negociacao e status 200")
//            public void deletar() throws Exception {
//                mockMvc.perform(delete("/fontes/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Fonte Negociacaos, retornar a Fonte Negociacaos e status 201")
//            public void criar() throws Exception {
//                when(service.salvar(fonteNegociacao)).thenReturn(fonteNegociacao);
//                String jsonString = mapper.writeValueAsString(fonteNegociacao);
//                mockMvc.perform(post("/fontes")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"));
//                verify(service, times(1)).salvar(Mockito.any(FonteNegociacao.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Fonte Negociacaos, retornar a Fonte Negociacaos e status 201")
//            public void atualizar() throws Exception {
//                when(service.atualizar(1L, fonteNegociacao)).thenReturn(fonteNegociacao);
//                String jsonString = mapper.writeValueAsString(fonteNegociacao);
//                mockMvc.perform(put("/fontes/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"));
//                verify(service, times(1))
//                        .atualizar(1L, fonteNegociacao);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando o token invalido.")
//        class TokenInvalido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private FonteNegociacaoService service;
//
//            @Mock
//            private Page<FonteNegociacao> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Fonte Negociacao com usuario e senha incorretos, retornar status 401")
//            public void listamosTokenIncorreto() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/fontes")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Fonte Negociacao usando usuario e senha incorretos, retornar status 401")
//            public void buscarTokenIncorreto() throws Exception {
//                when(service.detalhar(1L)).thenReturn(fonteNegociacao);
//                mockMvc.perform(get("/fontes/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).detalhar(1L);
//            }
//
//            @Test
//            @DisplayName("Deletar Fonte Negociacao com usuario e senha incorretos, retornar status 401")
//            public void deletarTokenIncorreto() throws Exception {
//                when(service.deletar(1L)).thenReturn(fonteNegociacao);
//                mockMvc.perform(delete("/fontes/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Fonte Negociacao com usuario e senha incorretos, retornar status 401")
//            public void criarTokenIncorreto() throws Exception {
//                when(service.salvar(fonteNegociacao)).thenReturn(fonteNegociacao);
//                String jsonString = mapper.writeValueAsString(fonteNegociacao);
//                mockMvc.perform(post("/fontes")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, fonteNegociacao);
//            }
//
//            @Test
//            @DisplayName("Atualizar Fonte Negociacao com usuario e senha incorretos, retornar status 401")
//            public void atualizarTokenIncorreto() throws Exception {
//                when(service.atualizar(1L, fonteNegociacao)).thenReturn(fonteNegociacao);
//                String jsonString = mapper.writeValueAsString(fonteNegociacao);
//                mockMvc.perform(put("/fontes/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, fonteNegociacao);
//            }
//
//            @Test
//            @DisplayName("Listar Fonte Negociacaos sem token, retornar status 401")
//            public void listamosSemToken() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/fontes"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Fonte Negociacaos sem token, retornar status 401")
//            public void buscarSemToken() throws Exception {
//                FonteNegociacao fonteNegociacao = new FonteNegociacao(1L, "nome", null);
//                when(service.detalhar(1L)).thenReturn(fonteNegociacao);
//                mockMvc.perform(get("/fontes/{id}", 1L))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).detalhar(1L);
//            }
//
//            @Test
//            @DisplayName("Deletar Fonte Negociacao sem token, retornar status 401")
//            public void deletarSemToken() throws Exception {
//                when(service.deletar(1L)).thenReturn(fonteNegociacao);
//                mockMvc.perform(delete("/fontes/1"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Fonte Negociacao sem token, retornar status 401")
//            public void criarSemToken() throws Exception {
//                when(service.salvar(fonteNegociacao)).thenReturn(fonteNegociacao);
//                String jsonString = mapper.writeValueAsString(fonteNegociacao);
//                mockMvc.perform(post("/fontes")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).salvar(fonteNegociacao);
//            }
//
//            @Test
//            @DisplayName("Atualizar Fonte Negociacao sem token, retornar status 401")
//            public void atualizarSemToken() throws Exception {
//                when(service.atualizar(1L, fonteNegociacao)).thenReturn(fonteNegociacao);
//                String jsonString = mapper.writeValueAsString(fonteNegociacao);
//                mockMvc.perform(put("/fontes/1")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .atualizar(1L, fonteNegociacao);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando o acesso sem permissão.")
//        class SemPermissao {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private FonteNegociacaoService service;
//
//            @Mock
//            private Page<FonteNegociacao> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Fonte Negociacaos sem permissão de acesso, retornar o status 403")
//            public void permissaoListar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/fontes")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Fonte Negociacaos usando filtro pelo nome sem permissão de acesso, retornar o status 403")
//            public void permissaoListarsNome() throws Exception {
//                when(service.filtrar(filterNome, pageable)).thenReturn(page);
//                mockMvc.perform(get("/fontes?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Fonte Negociacaos usando o id sem permissão de acesso, retornar o status 403")
//            public void permissaoBuscar() throws Exception {
//                when(service.detalhar(1L)).thenReturn(fonteNegociacao);
//                mockMvc.perform(get("/fontes/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).detalhar(1L);
//            }
//
//            @Test
//            @DisplayName("Deletar Fonte Negociacao sem permissão de acesso, retornar a Fonte Negociacao e status 403")
//            public void permissaoDeletar() throws Exception {
//                mockMvc.perform(delete("/fontes/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Fonte Negociacao sem permissão de acesso, retornar o status 403")
//            public void permissaoCriar() throws Exception {
//                when(service.salvar(fonteNegociacao)).thenReturn(fonteNegociacao);
//                String jsonString = mapper.writeValueAsString(fonteNegociacao);
//                mockMvc.perform(post("/fontes")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).salvar(Mockito.any(FonteNegociacao.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Fonte Negociacao sem permissão de acesso, retornar o status 403")
//            public void permissaoAtualizar() throws Exception {
//                when(service.atualizar(1L, fonteNegociacao)).thenReturn(fonteNegociacao);
//                String jsonString = mapper.writeValueAsString(fonteNegociacao);
//                mockMvc.perform(put("/fontes/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .atualizar(1L, fonteNegociacao);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da entidade.")
//        class ValidacoesEntidade {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private FonteNegociacaoService service;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Criar Fonte Negociacao informando um nome null, retornar mensagem de erro e status 400.")
//            public void criarNomeNull() throws Exception {
//                when(service.salvar(nomeNull)).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(post("/fontes")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0)).salvar(nomeNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Fonte Negociacao informando um nome null, retornar mensagem de erro e status 400.")
//            public void atualizarNomeNull() throws Exception {
//                when(service.atualizar(1L, nomeNull)).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(put("/fontes/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeNull);
//            }
//
//            @Test
//            @DisplayName("Criar Fonte Negociacao informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void criarNomeVazio() throws Exception {
//                when(service.salvar(nomeVazio)).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(post("/fontes")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).salvar(nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Atualizar Fonte Negociacao informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void atualizarNomeVazio() throws Exception {
//                when(service.atualizar(1L, nomeVazio)).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(put("/fontes/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Criar Fonte Negociacao informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAcima50Caracteres() throws Exception {
//                when(service.salvar(nomeMaior50)).thenReturn(nomeMaior50);
//                String jsonString = mapper.writeValueAsString(nomeMaior50);
//                mockMvc.perform(post("/fontes")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).salvar(nomeMaior50);
//            }
//
//            @Test
//            @DisplayName("Atualizar Fonte Negociacao informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarrNomeAcima50Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(FonteNegociacao.class))).thenReturn(nomeMaior50);
//                String jsonString = mapper.writeValueAsString(nomeMaior50);
//                mockMvc.perform(post("/fontes")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).salvar(nomeMaior50);
//            }
//
//            @Test
//            @DisplayName("Criar Fonte Negociacao informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAbaixo4Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(FonteNegociacao.class))).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(post("/fontes")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).salvar(nomeMenor4);
//            }
//
//            @Test
//            @DisplayName("Atualizar Fonte Negociacao informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAbaixo4Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(FonteNegociacao.class))).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(post("/fontes")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).salvar(nomeMenor4);
//            }
//        }
//
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service")
//        class Service {
//        }
//    }
//
//    // TODO: Falta fazer os testes de service e repository
//    @Nested
//    @DisplayName("Negociação Produto")
//    class NegociacaoProdutoTestes {
//        private final NegociacaoProdutoFilter filter = new NegociacaoProdutoFilter();
//        private final NegociacaoProdutoFilter quantidadeFilter = new NegociacaoProdutoFilter(1, null, null);
//        private final NegociacaoProdutoFilter valorFilter = new NegociacaoProdutoFilter(null, new BigDecimal(1), null);
//        private final NegociacaoProdutoFilter totalFilter = new NegociacaoProdutoFilter(null, null, new BigDecimal(1));
//        private final NegociacaoProdutoFilter filterCarregado = new NegociacaoProdutoFilter(1, new BigDecimal(1), new BigDecimal(1));
//        private final NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, new Produto(1L), new Negociacao(1L));
//        NegociacaoProduto quantidadeNull = new NegociacaoProduto(1L, null, new BigDecimal(1), TipoReincidencia.RECORRENTE, new Produto(1L), new Negociacao(1L));
//        NegociacaoProduto valorNull = new NegociacaoProduto(1L, 1, null, TipoReincidencia.RECORRENTE, new Produto(1L), new Negociacao(1L));
//        NegociacaoProduto tipoReincidenciaNull = new NegociacaoProduto(1L, 1, new BigDecimal(1), null, new Produto(1L), new Negociacao(1L));
//        NegociacaoProduto produtoNull = new NegociacaoProduto(1L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, null, new Negociacao(1L));
//        NegociacaoProduto negociacaoNull = new NegociacaoProduto(1L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, new Produto(1L), null);
//
//        @Nested
//        @DisplayName("Testando End Point, token valido.")
//        class TokenValido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private NegociacaoProdutoService service;
//
//            @Mock
//            private Page<NegociacaoProduto> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Negociacao Produtos, retornar a Negociacao Produtos e status 200")
//            public void listar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/negociacaoProdutos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Negociacao Produtos usando filtro pela quantidade, retornar a Negociacao Produtos e status 200")
//            public void listarQuantidade() throws Exception {
//                when(service.filtrar(quantidadeFilter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/negociacaoProdutos?quantidade=1")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(quantidadeFilter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Negociacao Produtos usando filtro pelo valor, retornar a Negociacao Produtos e status 200")
//            public void listarValor() throws Exception {
//                when(service.filtrar(valorFilter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/negociacaoProdutos?valor=1")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(valorFilter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Negociacao Produtos usando filtro pelo total, retornar a Negociacao Produtos e status 200")
//            public void listarTotal() throws Exception {
//                when(service.filtrar(totalFilter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/negociacaoProdutos?total=1")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(totalFilter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Negociacao Produtos usando filtro por todos os filtros, retornar a NegociacaoProdutos e status 200")
//            public void listarTodosFiltros() throws Exception {
//                when(service.filtrar(filterCarregado, pageable)).thenReturn(page);
//                mockMvc.perform(get("/negociacaoProdutos?quantidade=1&valor=1&total=1")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterCarregado, pageable);
//            }
//
//            @Test
//            @DisplayName("Deletar Negociacao Produtos, retornar a NegociacaoProduto e status 200")
//            public void deletar() throws Exception {
//                mockMvc.perform(delete("/negociacaoProdutos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1)).deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Negociacao Produtos, retornar a NegociacaoProdutos e status 201")
//            public void criar() throws Exception {
//                when(service.salvar(negociacaoProduto)).thenReturn(negociacaoProduto);
//                String jsonString = mapper.writeValueAsString(negociacaoProduto);
//                mockMvc.perform(post("/negociacaoProdutos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.quantidade").value(1))
//                        .andExpect(jsonPath("$.valor").value(1));
//                verify(service, times(1)).salvar(Mockito.any(NegociacaoProduto.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Negociacao Produtos, retornar a Negociacao Produtos e status 201")
//            public void atualizar() throws Exception {
//                NegociacaoProduto negociacaoProduto = new NegociacaoProduto(3L, 1, new BigDecimal(1), TipoReincidencia.RECORRENTE, new Produto(1L), new Negociacao(1L));
//                when(service.atualizar(1L, negociacaoProduto)).thenReturn(negociacaoProduto);
//                String jsonString = mapper.writeValueAsString(negociacaoProduto);
//                mockMvc.perform(put("/negociacaoProdutos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.quantidade").value(1))
//                        .andExpect(jsonPath("$.valor").value(1));
//                verify(service, times(1))
//                        .atualizar(1L, negociacaoProduto);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, token invalido.")
//        class TokenInvalido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private NegociacaoProdutoService service;
//
//            @Mock
//            private Page<NegociacaoProduto> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Negociacao Produto com usuario e senha incorretos, retornar status 401")
//            public void listamosTokenIncorreto() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/negociacaoProdutos")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Negociacao Produto usando usuario e senha incorretos, retornar status 401")
//            public void buscarTokenIncorreto() throws Exception {
//                when(service.detalhar(3L)).thenReturn(negociacaoProduto);
//                mockMvc.perform(get("/negociacaoProdutos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Negociacao Produto com usuario e senha incorretos, retornar status 401")
//            public void deletarTokenIncorreto() throws Exception {
//                when(service.deletar(3L)).thenReturn(negociacaoProduto);
//                mockMvc.perform(delete("/negociacaoProdutos/3")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Negociacao Produto com usuario e senha incorretos, retornar status 401")
//            public void criarTokenIncorreto() throws Exception {
//                when(service.salvar(negociacaoProduto)).thenReturn(negociacaoProduto);
//                String jsonString = mapper.writeValueAsString(negociacaoProduto);
//                mockMvc.perform(post("/negociacaoProdutos")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, negociacaoProduto);
//            }
//
//            @Test
//            @DisplayName("Atualizar Negociacao Produto com usuario e senha incorretos, retornar status 401")
//            public void atualizarTokenIncorreto() throws Exception {
//                when(service.atualizar(1L, negociacaoProduto)).thenReturn(negociacaoProduto);
//                String jsonString = mapper.writeValueAsString(negociacaoProduto);
//                mockMvc.perform(put("/negociacaoProdutos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, negociacaoProduto);
//            }
//
//            @Test
//            @DisplayName("Listar Negociacao Produtos sem token, retornar status 401")
//            public void listamosSemToken() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/negociacaoProdutos"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Negociacao Produtos sem token, retornar status 401")
//            public void buscarSemToken() throws Exception {
//                when(service.detalhar(3L)).thenReturn(negociacaoProduto);
//                mockMvc.perform(get("/negociacaoProdutos/{id}", 3L))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Negociacao Produto sem token, retornar status 401")
//            public void deletarSemToken() throws Exception {
//                when(service.deletar(3L)).thenReturn(negociacaoProduto);
//                mockMvc.perform(delete("/negociacaoProdutos/1"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Negociacao Produto sem token, retornar status 401")
//            public void criarSemToken() throws Exception {
//                when(service.salvar(negociacaoProduto)).thenReturn(negociacaoProduto);
//                String jsonString = mapper.writeValueAsString(negociacaoProduto);
//                mockMvc.perform(post("/negociacaoProdutos")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).salvar(negociacaoProduto);
//            }
//
//            @Test
//            @DisplayName("Atualizar Negociacao Produto sem token, retornar status 401")
//            public void atualizarSemToken() throws Exception {
//                when(service.atualizar(1L, negociacaoProduto)).thenReturn(negociacaoProduto);
//                String jsonString = mapper.writeValueAsString(negociacaoProduto);
//                mockMvc.perform(put("/negociacaoProdutos/{id}", 3L)
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .atualizar(3L, negociacaoProduto);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, acesso sem permissão.")
//        class SemPermissao {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private NegociacaoProdutoService service;
//            @Mock
//            private Page<NegociacaoProduto> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Negociacao Produtos sem permissão de acesso, retornar o status 403")
//            public void permissaoListar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/negociacaoProdutos")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Negociacao Produtos usando filtro pela quantidade sem permissão de acesso, retornar o status 403")
//            public void permissaoListarsQuantidade() throws Exception {
//                when(service.filtrar(quantidadeFilter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/negociacaoProdutos?quantidade=1")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(quantidadeFilter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Negociacao Produtos usando filtro pela valor sem permissão de acesso, retornar o status 403")
//            public void permissaoListarValor() throws Exception {
//                when(service.filtrar(valorFilter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/negociacaoProdutos?valor=1")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print());
//                verify(service, times(0))
//                        .filtrar(valorFilter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Negociacao Produtos usando filtro pelo total sem permissão de acesso, retornar o status 403")
//            public void permissaoListarTotal() throws Exception {
//                when(service.filtrar(totalFilter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/negociacaoProdutos?total=1")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(totalFilter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Negociacao Produtos usando o id sem permissão de acesso, retornar o status 403")
//            public void permissaoBuscar() throws Exception {
//                when(service.detalhar(3L)).thenReturn(negociacaoProduto);
//                mockMvc.perform(get("/negociacaoProdutos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Negociacao Produto sem permissão de acesso, retornar a NegociacaoProduto e status 403")
//            public void permissaoDeletar() throws Exception {
//                mockMvc.perform(delete("/negociacaoProdutos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print());
//                verify(service, times(0)).deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Negociacao Produto sem permissão de acesso, retornar o status 403")
//            public void permissaoCriar() throws Exception {
//                when(service.salvar(negociacaoProduto)).thenReturn(negociacaoProduto);
//                String jsonString = mapper.writeValueAsString(negociacaoProduto);
//                mockMvc.perform(post("/negociacaoProdutos")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).salvar(Mockito.any(NegociacaoProduto.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Negociacao Produto sem permissão de acesso, retornar o status 403")
//            public void permissaoAtualizar() throws Exception {
//                when(service.atualizar(1L, negociacaoProduto)).thenReturn(negociacaoProduto);
//                String jsonString = mapper.writeValueAsString(negociacaoProduto);
//                mockMvc.perform(put("/negociacaoProdutos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .atualizar(3L, negociacaoProduto);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, validações das entidades.")
//        class ValidacoesEntidade {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private NegociacaoProdutoService service;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Criar Negociacao Produto informando uma quantidade null, retornar mensagem de erro e status 400.")
//            public void criarQuantidadeNull() throws Exception {
//                when(service.salvar(quantidadeNull)).thenReturn(quantidadeNull);
//                String jsonString = mapper.writeValueAsString(quantidadeNull);
//                mockMvc.perform(post("/negociacaoProdutos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("quantidade"))
//                        .andExpect(jsonPath("$.fieldMessage").value("A quantidade não pode ser null."));
//                verify(service, times(0)).salvar(quantidadeNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Negociacao Produto informando uma quantidade null, retornar mensagem de erro e status 400.")
//            public void atualizarQuantidadeNull() throws Exception {
//                when(service.atualizar(1L, quantidadeNull)).thenReturn(quantidadeNull);
//                String jsonString = mapper.writeValueAsString(quantidadeNull);
//                mockMvc.perform(put("/negociacaoProdutos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("quantidade"))
//                        .andExpect(jsonPath("$.fieldMessage").value("A quantidade não pode ser null."));
//                verify(service, times(0)).salvar(quantidadeNull);
//            }
//
//            @Test
//            @DisplayName("Criar Negociacao Produto informando um valor null, retornar mensagem de erro e status 400.")
//            public void criarValorNull() throws Exception {
//                when(service.salvar(valorNull)).thenReturn(valorNull);
//                String jsonString = mapper.writeValueAsString(valorNull);
//                mockMvc.perform(post("/negociacaoProdutos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("valor"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O valor não pode ser null."));
//                verify(service, times(0)).salvar(valorNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Negociacao Produto informando um valor null, retornar mensagem de erro e status 400.")
//            public void atualizarValorNull() throws Exception {
//                when(service.atualizar(1L, valorNull)).thenReturn(valorNull);
//                String jsonString = mapper.writeValueAsString(valorNull);
//                mockMvc.perform(put("/negociacaoProdutos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("valor"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O valor não pode ser null."));
//                verify(service, times(0)).salvar(valorNull);
//            }
//
//            @Test
//            @DisplayName("Criar Negociacao Produto informando um total null, retornar mensagem de erro e status 400.")
//            public void criarTipoReincidenciaNull() throws Exception {
//                when(service.salvar(tipoReincidenciaNull)).thenReturn(tipoReincidenciaNull);
//                String jsonString = mapper.writeValueAsString(tipoReincidenciaNull);
//                mockMvc.perform(post("/negociacaoProdutos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("reincidencia"))
//                        .andExpect(jsonPath("$.fieldMessage").value("A reicidência não pode ser null."));
//                verify(service, times(0)).salvar(tipoReincidenciaNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Negociacao Produto informando um total null, retornar mensagem de erro e status 400.")
//            public void atualizarTipoReincidenciaNull() throws Exception {
//                when(service.atualizar(1L, tipoReincidenciaNull)).thenReturn(tipoReincidenciaNull);
//                String jsonString = mapper.writeValueAsString(tipoReincidenciaNull);
//                mockMvc.perform(put("/negociacaoProdutos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("reincidencia"))
//                        .andExpect(jsonPath("$.fieldMessage").value("A reicidência não pode ser null."));
//                verify(service, times(0)).salvar(tipoReincidenciaNull);
//            }
//
//            @Test
//            @DisplayName("Criar Negociacao Produto informando um Produto null, retornar mensagem de erro e status 400.")
//            public void criarProdutoNull() throws Exception {
//                when(service.salvar(produtoNull)).thenReturn(produtoNull);
//                String jsonString = mapper.writeValueAsString(produtoNull);
//                mockMvc.perform(post("/negociacaoProdutos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("produto"))
//                        .andExpect(jsonPath("$.fieldMessage").value("É necessário ter um Produto vinculado."));
//                verify(service, times(0)).salvar(produtoNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Negociacao Produto informando um Produto null, retornar mensagem de erro e status 400.")
//            public void atualizarProdutoNull() throws Exception {
//                when(service.atualizar(1L, produtoNull)).thenReturn(produtoNull);
//                String jsonString = mapper.writeValueAsString(produtoNull);
//                mockMvc.perform(put("/negociacaoProdutos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("produto"))
//                        .andExpect(jsonPath("$.fieldMessage").value("É necessário ter um Produto vinculado."));
//                verify(service, times(0)).salvar(produtoNull);
//            }
//
//            @Test
//            @DisplayName("Criar Negociacao Produto informando uma Negociação null, retornar mensagem de erro e status 400.")
//            public void criarNegociacaoNull() throws Exception {
//                when(service.salvar(negociacaoNull)).thenReturn(negociacaoNull);
//                String jsonString = mapper.writeValueAsString(negociacaoNull);
//                mockMvc.perform(post("/negociacaoProdutos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("negociacao"))
//                        .andExpect(jsonPath("$.fieldMessage").value("É necessário ter uma Negociação vinculada."));
//                verify(service, times(0)).salvar(negociacaoNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Negociacao Produto informando uma Negociação null, retornar mensagem de erro e status 400.")
//            public void atualizarNegociacaoNull() throws Exception {
//                when(service.atualizar(1L, negociacaoNull)).thenReturn(negociacaoNull);
//                String jsonString = mapper.writeValueAsString(negociacaoNull);
//                mockMvc.perform(put("/negociacaoProdutos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("negociacao"))
//                        .andExpect(jsonPath("$.fieldMessage").value("É necessário ter uma Negociação vinculada."));
//                verify(service, times(0)).salvar(negociacaoNull);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service")
//        class Service {
//        }
//    }
//
//    // TODO: Falta fazer os testes de service e repository
//    @Nested
//    @DisplayName("Motivo Perda")
//    class MotivoPerdaTestes {
//        private final MotivoPerdaFilter filter = new MotivoPerdaFilter();
//        private final MotivoPerdaFilter nomeFilter = new MotivoPerdaFilter("nome");
//        private final MotivoPerda motivoPerda = new MotivoPerda(3L, "nome", null);
//        MotivoPerda nomeNull = new MotivoPerda(1L, null, null);
//        MotivoPerda nomeVazio = new MotivoPerda(1L, "", null);
//        MotivoPerda nomeMaior50 = new MotivoPerda(null, "nome nome nome nome nome nome nome nome nome nome nome nome", null);
//        MotivoPerda nomeMenor4 = new MotivoPerda(null, "nom", null);
//
//
//        @Nested
//        @DisplayName("Testando End Point, token valido.")
//        class TokenValido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private MotivoPerdaService service;
//
//            @Mock
//            private Page<MotivoPerda> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Motivo Perdas, retornar a MotivoPerdas e status 200")
//            public void listar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/motivoperda")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Motivo Perdas usando filtro pelo nome, retornar a MotivoPerdas e status 200")
//            public void listarNome() throws Exception {
//                when(service.filtrar(nomeFilter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/motivoperda?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(nomeFilter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Motivo Perdas usando o id, retornar a MotivoPerdas e status 200 sucesso")
//            public void buscar() throws Exception {
//                when(service.detalhar(3L)).thenReturn(motivoPerda);
//                mockMvc.perform(get("/motivoperda/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"));
//                verify(service, times(1)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Motivo Perdas, retornar a MotivoPerda e status 200")
//            public void deletar() throws Exception {
//                mockMvc.perform(delete("/motivoperda/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1)).deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Motivo Perdas, retornar a MotivoPerdas e status 201")
//            public void criar() throws Exception {
//                when(service.salvar(motivoPerda)).thenReturn(motivoPerda);
//                String jsonString = mapper.writeValueAsString(motivoPerda);
//                mockMvc.perform(post("/motivoperda")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"));
//                verify(service, times(1)).salvar(Mockito.any(MotivoPerda.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Motivo Perdas, retornar a MotivoPerdas e status 201")
//            public void atualizar() throws Exception {
//                when(service.atualizar(1L, motivoPerda)).thenReturn(motivoPerda);
//                String jsonString = mapper.writeValueAsString(motivoPerda);
//                mockMvc.perform(put("/motivoperda/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"));
//                verify(service, times(1))
//                        .atualizar(1L, motivoPerda);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, token invalido.")
//        class TokenInvalido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private MotivoPerdaService service;
//
//            @Mock
//            private Page<MotivoPerda> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Motivo Perda com usuario e senha incorretos, retornar status 401")
//            public void listamosTokenIncorreto() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/motivoperda")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Motivo Perda usando usuario e senha incorretos, retornar status 401")
//            public void buscarTokenIncorreto() throws Exception {
//                when(service.detalhar(3L)).thenReturn(motivoPerda);
//                mockMvc.perform(get("/motivoperda/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Motivo Perda com usuario e senha incorretos, retornar status 401")
//            public void deletarTokenIncorreto() throws Exception {
//                when(service.deletar(3L)).thenReturn(motivoPerda);
//                mockMvc.perform(delete("/motivoperda/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Motivo Perda com usuario e senha incorretos, retornar status 401")
//            public void criarTokenIncorreto() throws Exception {
//                when(service.salvar(motivoPerda)).thenReturn(motivoPerda);
//                String jsonString = mapper.writeValueAsString(motivoPerda);
//                mockMvc.perform(post("/motivoperda")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, motivoPerda);
//            }
//
//            @Test
//            @DisplayName("Atualizar Motivo Perda com usuario e senha incorretos, retornar status 401")
//            public void atualizarTokenIncorreto() throws Exception {
//                when(service.atualizar(1L, motivoPerda)).thenReturn(motivoPerda);
//                String jsonString = mapper.writeValueAsString(motivoPerda);
//                mockMvc.perform(put("/motivoperda/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, motivoPerda);
//            }
//
//            @Test
//            @DisplayName("Listar Motivo Perdas sem token, retornar status 401")
//            public void listamosSemToken() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/motivoperda"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Motivo Perdas sem token, retornar status 401")
//            public void buscarSemToken() throws Exception {
//                when(service.detalhar(3L)).thenReturn(motivoPerda);
//                mockMvc.perform(get("/motivoperda/{id}", 3L))
//                        .andExpect(content()
//                                .contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Motivo Perda sem token, retornar status 401")
//            public void deletarSemToken() throws Exception {
//                when(service.deletar(3L)).thenReturn(motivoPerda);
//                mockMvc.perform(delete("/motivoperda/1"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Motivo Perda sem token, retornar status 401")
//            public void criarSemToken() throws Exception {
//                when(service.salvar(motivoPerda)).thenReturn(motivoPerda);
//                String jsonString = mapper.writeValueAsString(motivoPerda);
//                mockMvc.perform(post("/motivoperda")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).salvar(motivoPerda);
//            }
//
//            @Test
//            @DisplayName("Atualizar Motivo Perda sem token, retornar status 401")
//            public void atualizarSemToken() throws Exception {
//                when(service.atualizar(1L, motivoPerda)).thenReturn(motivoPerda);
//                String jsonString = mapper.writeValueAsString(motivoPerda);
//                mockMvc.perform(put("/motivoperda/1")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .atualizar(1L, motivoPerda);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, acesso sem permissão.")
//        class SemPermissao {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private MotivoPerdaService service;
//
//            @Mock
//            private Page<MotivoPerda> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Motivo Perdas sem permissão de acesso, retornar o status 403")
//            public void permissaoListar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/motivoperda")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Motivo Perdas usando filtro pelo nome sem permissão de acesso, retornar o status 403")
//            public void permissaoListarsNome() throws Exception {
//                when(service.filtrar(nomeFilter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/motivoperda?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(nomeFilter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Motivo Perdas usando o id sem permissão de acesso, retornar o status 403")
//            public void permissaoBuscar() throws Exception {
//                when(service.detalhar(3L)).thenReturn(motivoPerda);
//                mockMvc.perform(get("/motivoperda/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Motivo Perda sem permissão de acesso, retornar a MotivoPerda e status 403")
//            public void permissaoDeletar() throws Exception {
//                mockMvc.perform(delete("/motivoperda/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Motivo Perda sem permissão de acesso, retornar o status 403")
//            public void permissaoCriar() throws Exception {
//                when(service.salvar(motivoPerda)).thenReturn(motivoPerda);
//                String jsonString = mapper.writeValueAsString(motivoPerda);
//                mockMvc.perform(post("/motivoperda")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).salvar(Mockito.any(MotivoPerda.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Motivo Perda sem permissão de acesso, retornar o status 403")
//            public void permissaoAtualizar() throws Exception {
//                when(service.atualizar(1L, motivoPerda)).thenReturn(motivoPerda);
//                String jsonString = mapper.writeValueAsString(motivoPerda);
//                mockMvc.perform(put("/motivoperda/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .atualizar(1L, motivoPerda);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, validações das entidades.")
//        class ValidacoesEntidade {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private MotivoPerdaService service;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Criar Motivo Perda informando um nome null, retornar mensagem de erro e status 400.")
//            public void criarNomeNull() throws Exception {
//                when(service.salvar(nomeNull)).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(post("/motivoperda")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Motivo Perda informando um nome null, retornar mensagem de erro e status 400.")
//            public void atualizarNomeNull() throws Exception {
//                when(service.atualizar(1L, nomeNull)).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(put("/motivoperda/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeNull);
//            }
//
//            @Test
//            @DisplayName("Criar Motivo Perda informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void criarNomeVazio() throws Exception {
//                when(service.salvar(nomeVazio)).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(post("/motivoperda")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Atualizar Motivo Perda informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void atualizarNomeVazio() throws Exception {
//                when(service.atualizar(1L, nomeVazio)).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(put("/motivoperda/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Criar Motivo Perda informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAcima50Caracteres() throws Exception {
//                when(service.atualizar(1L, nomeMaior50)).thenReturn(nomeMaior50);
//                String jsonString = mapper.writeValueAsString(nomeMaior50);
//                mockMvc.perform(post("/motivoperda")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeMaior50);
//            }
//
//            @Test
//            @DisplayName("Atualizar Motivo Perda informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAcima50Caracteres() throws Exception {
//                when(service.atualizar(1L, nomeMaior50)).thenReturn(nomeMaior50);
//                String jsonString = mapper.writeValueAsString(nomeMaior50);
//                mockMvc.perform(put("/motivoperda/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeMaior50);
//            }
//
//            @Test
//            @DisplayName("Criar Motivo Perda informando um nome abaixo 4 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAbaixo4Caracteres() throws Exception {
//                when(service.salvar(nomeMenor4)).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(post("/motivoperda")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeMenor4);
//
//            }
//
//            @Test
//            @DisplayName("Atualizar Motivo Perda informando um nome abaixo 4 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAbaixo4Caracteres() throws Exception {
//                when(service.atualizar(1L, nomeMenor4)).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(put("/motivoperda/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeMenor4);
//
//            }
//        }
//
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service")
//        class Service {
//        }
//    }
//
//    // TODO: Falta fazer os testes de service e repository
//    @Nested
//    @DisplayName("Produto")
//    class ProdutoTestes {
//        private final Produto produto = new Produto(1L, "nome", "descricao", new BigDecimal(10), true, null);
//        private final ProdutoFilter filter = new ProdutoFilter();
//        private final ProdutoFilter filterNome = new ProdutoFilter("nome", null);
//        private final ProdutoFilter filterDescricao = new ProdutoFilter(null, "descricao");
//        private final Produto nomeNull = new Produto(1L, null, "descricao", new BigDecimal(10), true, null);
//        private final Produto valorNull = new Produto(1L, "nome", "descricao", null, true, null);
//        private final Produto visibilidadeNull = new Produto(1L, "nome", "descricao", new BigDecimal(10), null, null);
//        private final Produto nomeVazio = new Produto(1L, "", "descricao", new BigDecimal(10), true, null);
//        private final Produto nomeMaior100 = new Produto(null, "nome nome nome nome nome nome nome nome nome nome nome nome nome nome nome nome nome nome nome nome nome nome nome nome", "descricao", new BigDecimal(10), true, null);
//        private final Produto nomeMenor4 = new Produto(null, "nom", "descricao", new BigDecimal(10), true, null);
//
//        @Nested
//        @DisplayName("Testando End Point, token valido.")
//        class TokenValido {
//            @Autowired
//            private MockMvc mockMvc;
//            @MockBean
//            private ProdutoService service;
//            @Mock
//            private Page<Produto> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Produtos, retornar a Produtos e status 200")
//            public void listar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/produtos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Produtos usando filtro pelo nome, retornar a Produtos e status 200")
//            public void listarNome() throws Exception {
//                when(service.filtrar(filterNome, pageable)).thenReturn(page);
//                mockMvc.perform(get("/produtos?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Produtos usando filtro pela descricao, retornar a Produtos e status 200")
//            public void listarDescricao() throws Exception {
//                when(service.filtrar(filterDescricao, pageable)).thenReturn(page);
//                mockMvc.perform(get("/produtos?descricao=descricao")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterDescricao, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Produtos usando o id, retornar a Produtos e status 200 sucesso")
//            public void buscar() throws Exception {
//                when(service.detalhar(1L)).thenReturn(produto);
//                mockMvc.perform(get("/produtos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .detalhar(1L);
//            }
//
//            @Test
//            @DisplayName("Deletar Produtos, retornar a Produto e status 200")
//            public void deletar() throws Exception {
//                mockMvc.perform(delete("/produtos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Produtos, retornar a Produtos e status 201")
//            public void criar() throws Exception {
//                when(service.salvar(produto)).thenReturn(produto);
//                String jsonString = mapper.writeValueAsString(produto);
//                mockMvc.perform(post("/produtos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"))
//                        .andExpect(jsonPath("$.descricao").value("descricao"));
//                verify(service, times(1)).salvar(Mockito.any(Produto.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Produtos, retornar a Produtos e status 201")
//            public void atualizar() throws Exception {
//                when(service.atualizar(1L, produto)).thenReturn(produto);
//                String jsonString = mapper.writeValueAsString(produto);
//                mockMvc.perform(put("/produtos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"))
//                        .andExpect(jsonPath("$.descricao").value("descricao"));
//                verify(service, times(1))
//                        .atualizar(1L, produto);
//            }
//
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, token invalido.")
//        class TokenInvalido {
//            @Autowired
//            private MockMvc mockMvc;
//            @MockBean
//            private ProdutoService service;
//            @Mock
//            private Page<Produto> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Produto com usuario e senha incorretos, retornar status 401")
//            public void listamosTokenIncorreto() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/produtos")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Produto usando usuario e senha incorretos, retornar status 401")
//            public void buscarTokenIncorreto() throws Exception {
//                when(service.detalhar(1L)).thenReturn(produto);
//                mockMvc.perform(get("/produtos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).detalhar(1L);
//            }
//
//            @Test
//            @DisplayName("Deletar Produto com usuario e senha incorretos, retornar status 401")
//            public void deletarTokenIncorreto() throws Exception {
//                when(service.deletar(1L)).thenReturn(produto);
//                mockMvc.perform(delete("/produtos/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Produto com usuario e senha incorretos, retornar status 401")
//            public void criarTokenIncorreto() throws Exception {
//                Produto produto = new Produto(1L, "nome", "descricao", null, null, null);
//                when(service.salvar(produto)).thenReturn(produto);
//                String jsonString = mapper.writeValueAsString(produto);
//                mockMvc.perform(post("/produtos")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, produto);
//            }
//
//            @Test
//            @DisplayName("Atualizar Produto com usuario e senha incorretos, retornar status 401")
//            public void atualizarTokenIncorreto() throws Exception {
//                when(service.atualizar(1L, produto)).thenReturn(produto);
//                String jsonString = mapper.writeValueAsString(produto);
//                mockMvc.perform(put("/produtos/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, produto);
//            }
//
//            @Test
//            @DisplayName("Listar Produtos sem token, retornar status 401")
//            public void listamosSemToken() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/produtos"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Produtos sem token, retornar status 401")
//            public void buscarSemToken() throws Exception {
//                when(service.detalhar(1L)).thenReturn(produto);
//                mockMvc.perform(get("/produtos/{id}", 1L))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).detalhar(1L);
//            }
//
//            @Test
//            @DisplayName("Deletar Produto sem token, retornar status 401")
//            public void deletarSemToken() throws Exception {
//                when(service.deletar(1L)).thenReturn(produto);
//                mockMvc.perform(delete("/produtos/1"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Produto sem token, retornar status 401")
//            public void criarSemToken() throws Exception {
//                when(service.salvar(produto)).thenReturn(produto);
//                String jsonString = mapper.writeValueAsString(produto);
//                mockMvc.perform(post("/produtos")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).salvar(produto);
//            }
//
//            @Test
//            @DisplayName("Atualizar Produto sem token, retornar status 401")
//            public void atualizarSemToken() throws Exception {
//                when(service.atualizar(1L, produto)).thenReturn(produto);
//                String jsonString = mapper.writeValueAsString(produto);
//                mockMvc.perform(put("/produtos/1")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .atualizar(1L, produto);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, acesso sem permissão.")
//        class SemPermissao {
//            @Autowired
//            private MockMvc mockMvc;
//            @MockBean
//            private ProdutoService service;
//            @Mock
//            private Page<Produto> page;
//
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Produtos sem permissão de acesso, retornar o status 403")
//            public void permissaoListar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/produtos")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Produtos usando filtro pelo nome sem permissão de acesso, retornar o status 403")
//            public void permissaoListarsNome() throws Exception {
//                when(service.filtrar(filterNome, pageable)).thenReturn(page);
//                mockMvc.perform(get("/produtos?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Produtos usando filtro pela descricao sem permissão de acesso, retornar o status 403")
//            public void permissaoListarDescricao() throws Exception {
//                when(service.filtrar(filterDescricao, pageable)).thenReturn(page);
//                mockMvc.perform(get("/produtos?descricao=descricao")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filterDescricao, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Produtos usando o id sem permissão de acesso, retornar o status 403")
//            public void permissaoBuscar() throws Exception {
//                when(service.detalhar(1L)).thenReturn(produto);
//                mockMvc.perform(get("/produtos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).detalhar(1L);
//            }
//
//            @Test
//            @DisplayName("Deletar Produto sem permissão de acesso, retornar a Produto e status 403")
//            public void permissaoDeletar() throws Exception {
//                mockMvc.perform(delete("/produtos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Produto sem permissão de acesso, retornar o status 403")
//            public void permissaoCriar() throws Exception {
//                when(service.salvar(produto)).thenReturn(produto);
//                String jsonString = mapper.writeValueAsString(produto);
//                mockMvc.perform(post("/produtos")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).salvar(Mockito.any(Produto.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Produto sem permissão de acesso, retornar o status 403")
//            public void permissaoAtualizar() throws Exception {
//                when(service.atualizar(1L, produto)).thenReturn(produto);
//                String jsonString = mapper.writeValueAsString(produto);
//                mockMvc.perform(put("/produtos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .atualizar(1L, produto);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, validações das entidades.")
//        class ValidacoesEntidade {
//            @Autowired
//            private MockMvc mockMvc;
//            @MockBean
//            private ProdutoService service;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Criar Produto informando um nome null, retornar mensagem de erro e status 400.")
//            public void criarNomeNull() throws Exception {
//                when(service.salvar(Mockito.any(Produto.class))).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(post("/produtos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0)).salvar(nomeNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Produto informando um nome null, retornar mensagem de erro e status 400.")
//            public void atualizarNomeNull() throws Exception {
//                when(service.atualizar(1L, nomeNull)).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(put("/produtos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeNull);
//            }
//
//            @Test
//            @DisplayName("Criar Produto informando um valor null, retornar mensagem de erro e status 400.")
//            public void criarValorNull() throws Exception {
//                when(service.salvar(Mockito.any(Produto.class))).thenReturn(valorNull);
//                String jsonString = mapper.writeValueAsString(valorNull);
//                mockMvc.perform(post("/produtos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("valor"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O valor não pode ser null."));
//                verify(service, times(0)).salvar(valorNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Produto informando um valor null, retornar mensagem de erro e status 400.")
//            public void atualizarValorNull() throws Exception {
//                when(service.atualizar(1L, valorNull)).thenReturn(valorNull);
//                String jsonString = mapper.writeValueAsString(valorNull);
//                mockMvc.perform(put("/produtos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("valor"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O valor não pode ser null."));
//                verify(service, times(0))
//                        .atualizar(1L, valorNull);
//            }
//
//
//            @Test
//            @DisplayName("Criar Produto informando um visibilidade null, retornar mensagem de erro e status 400.")
//            public void criarVisibilidadeNull() throws Exception {
//                when(service.salvar(Mockito.any(Produto.class))).thenReturn(visibilidadeNull);
//                String jsonString = mapper.writeValueAsString(visibilidadeNull);
//                mockMvc.perform(post("/produtos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("visibilidade"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O visibilidade não pode ser null."));
//                verify(service, times(0)).salvar(visibilidadeNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Produto informando um visibilidade null, retornar mensagem de erro e status 400.")
//            public void atualizarvisibilidadeNull() throws Exception {
//                when(service.atualizar(1L, visibilidadeNull)).thenReturn(visibilidadeNull);
//                String jsonString = mapper.writeValueAsString(visibilidadeNull);
//                mockMvc.perform(put("/produtos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("visibilidade"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O visibilidade não pode ser null."));
//                verify(service, times(0))
//                        .atualizar(1L, visibilidadeNull);
//            }
//
//            @Test
//            @DisplayName("Criar Produto informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void criarNomeVazio() throws Exception {
//                when(service.salvar(Mockito.any(Produto.class))).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(post("/produtos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome do produto deve estar entre 4 e 100 caracteres."));
//                verify(service, times(0)).salvar(nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Atualizar Produto informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void atualizarNomeVazio() throws Exception {
//                when(service.atualizar(1L, nomeVazio)).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(put("/produtos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome do produto deve estar entre 4 e 100 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Criar Produto informando um nome acima de 100 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAcima100Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(Produto.class))).thenReturn(nomeMaior100);
//                String jsonString = mapper.writeValueAsString(nomeMaior100);
//                mockMvc.perform(post("/produtos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome do produto deve estar entre 4 e 100 caracteres."));
//                verify(service, times(0)).salvar(nomeMaior100);
//            }
//
//            @Test
//            @DisplayName("Atualizar Produto informando um nome acima de 100 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAcima100Caracteres() throws Exception {
//                when(service.atualizar(1L, nomeMaior100)).thenReturn(nomeMaior100);
//                String jsonString = mapper.writeValueAsString(nomeMaior100);
//                mockMvc.perform(put("/produtos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome do produto deve estar entre 4 e 100 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeMaior100);
//            }
//
//            @Test
//            @DisplayName("Criar Produto informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAbaixo4Caracteres() throws Exception {
//                when(service.salvar(Mockito.any(Produto.class))).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(post("/produtos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome do produto deve estar entre 4 e 100 caracteres."));
//                verify(service, times(0)).salvar(nomeMenor4);
//            }
//
//            @Test
//            @DisplayName("Atualizar Produto informando um nome abaixo 4 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAbaixo4Caracteres() throws Exception {
//                when(service.atualizar(1L, nomeMenor4)).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(put("/produtos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome do produto deve estar entre 4 e 100 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeMenor4);
//            }
//        }
//
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service")
//        class Service {
//        }
//    }
//
//    // TODO: Falta fazer os testes de service e repository
//    @Nested
//    @DisplayName("Role")
//    class RoleTestes {
//        private final RoleFilter filter = new RoleFilter();
//        private final RoleFilter filterNome = new RoleFilter("nome");
//
//        @Nested
//        @DisplayName("Testando End Point, token valido.")
//        class TokenValido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private RoleService service;
//
//            @Mock
//            private Page<Role> page;
//
//            @Mock
//            private Page<RoleResumo> pageResumo;
//
//            @Test
//            @DisplayName("Listar Roles, retornar a Roles e status 200")
//            public void listar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/roles")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1)).filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Roles usando filtro pelo nome, retornar a Roles e status 200")
//            public void listarNome() throws Exception {
//                when(service.filtrar(filterNome, pageable)).thenReturn(page);
//                mockMvc.perform(get("/roles?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1)).filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Roles usando resumo, retornar a Roles e status 200")
//            public void Resumir() throws Exception {
//                when(service.resumir(filter, pageable)).thenReturn(pageResumo);
//                mockMvc.perform(get("/roles?resumo")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1)).resumir(filter, pageable);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, token invalido.")
//        class TokenInvalido {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private RoleService service;
//
//            @Mock
//            private Page<Role> page;
//
//            private final RoleFilter filter = new RoleFilter();
//
//            @Test
//            @DisplayName("Listar Role com usuario e senha incorretos, retornar status 401")
//            public void listamosTokenIncorreto() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/roles")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print()).andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Roles sem token, retornar status 401")
//            public void listamosSemToken() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/roles"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized()).andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Resumir Roles sem token, retornar status 401")
//            public void ResumirSemToken() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/roles/resumo"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized()).andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).filtrar(filter, pageable);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, acesso sem permissão.")
//        class SemPermissao {
//            @Autowired
//            private MockMvc mockMvc;
//
//            @MockBean
//            private RoleService service;
//
//            @Mock
//            private Page<Role> page;
//
//            @Test
//            @DisplayName("Listar Roles sem permissão de acesso, retornar o status 403")
//            public void permissaoListar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/roles")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Roles usando filtro pelo nome sem permissão de acesso, retornar o status 403")
//            public void permissaoListarsNome() throws Exception {
//                when(service.filtrar(filterNome, pageable)).thenReturn(page);
//                mockMvc.perform(get("/roles?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Resumir Roles sem permissão de acesso, retornar o status 403")
//            public void permissaoResumo() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/roles?resumo")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).filtrar(filter, pageable);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service")
//        class Service {
//        }
//    }
//
//    // TODO: Falta fazer os testes de service e repository
//    @Nested
//    @DisplayName("Segmento")
//    class SegmentoTestes {
//
//        private final SegmentoFilter filter = new SegmentoFilter();
//        private final SegmentoFilter filterNome = new SegmentoFilter("nome");
//        private final Segmento segmento = new Segmento(3L, "nome", null);
//        private final Segmento nomeNull = new Segmento(1L, null, null);
//        private final Segmento nomeVazio = new Segmento(1L, "", null);
//        private final Segmento nomeMaior50 = new Segmento(1L, "nome nome nome nome nome nome nome nome nome nome nome nome", null);
//        private final Segmento nomeMenor4 = new Segmento(1L, "nom", null);
//
//
//        @Nested
//        @DisplayName("Testando End Point, token valido.")
//        class TokenValido {
//            @Autowired
//            private MockMvc mockMvc;
//            @MockBean
//            private SegmentoService service;
//            @Mock
//            private Page<Segmento> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Segmentos, retornar a Segmentos e status 200")
//            public void listar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/segmentos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Segmentos usando filtro pelo nome, retornar a Segmentos e status 200")
//            public void listarNome() throws Exception {
//                when(service.filtrar(filterNome, pageable)).thenReturn(page);
//                mockMvc.perform(get("/segmentos?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterNome, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Segmentos usando o id, retornar a Segmentos e status 200 sucesso")
//            public void buscar() throws Exception {
//                when(service.detalhar(3L)).thenReturn(segmento);
//                mockMvc.perform(get("/segmentos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"));
//                verify(service, times(1)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Segmentos, retornar a Segmento e status 200")
//            public void deletar() throws Exception {
//                mockMvc.perform(delete("/segmentos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1)).deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Segmentos, retornar a Segmentos e status 201")
//            public void criar() throws Exception {
//                when(service.salvar(segmento)).thenReturn(segmento);
//                String jsonString = mapper.writeValueAsString(segmento);
//                mockMvc.perform(post("/segmentos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"));
//                verify(service, times(1)).salvar(Mockito.any(Segmento.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Segmentos, retornar a Segmentos e status 201")
//            public void atualizar() throws Exception {
//                when(service.atualizar(1L, segmento)).thenReturn(segmento);
//                String jsonString = mapper.writeValueAsString(segmento);
//                mockMvc.perform(put("/segmentos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.nome").value("nome"));
//                verify(service, times(1))
//                        .atualizar(1L, segmento);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, token invalido.")
//        class TokenInvalido {
//            @Autowired
//            private MockMvc mockMvc;
//            @MockBean
//            private SegmentoService service;
//            @Mock
//            private Page<Segmento> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Segmento com usuario e senha incorretos, retornar status 401")
//            public void listamosTokenIncorreto() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/segmentos")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Segmento usando usuario e senha incorretos, retornar status 401")
//            public void buscarTokenIncorreto() throws Exception {
//                when(service.detalhar(3L)).thenReturn(segmento);
//                mockMvc.perform(get("/segmentos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Segmento com usuario e senha incorretos, retornar status 401")
//            public void deletarTokenIncorreto() throws Exception {
//                when(service.deletar(3L)).thenReturn(segmento);
//                mockMvc.perform(delete("/segmentos/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Segmento com usuario e senha incorretos, retornar status 401")
//            public void criarTokenIncorreto() throws Exception {
//                when(service.salvar(segmento)).thenReturn(segmento);
//                String jsonString = mapper.writeValueAsString(segmento);
//                mockMvc.perform(post("/segmentos")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, segmento);
//            }
//
//            @Test
//            @DisplayName("Atualizar Segmento com usuario e senha incorretos, retornar status 401")
//            public void atualizarTokenIncorreto() throws Exception {
//                when(service.atualizar(1L, segmento)).thenReturn(segmento);
//                String jsonString = mapper.writeValueAsString(segmento);
//                mockMvc.perform(put("/segmentos/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, segmento);
//            }
//
//            @Test
//            @DisplayName("Listar Segmentos sem token, retornar status 401")
//            public void listamosSemToken() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/segmentos"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Segmentos sem token, retornar status 401")
//            public void buscarSemToken() throws Exception {
//                when(service.detalhar(3L)).thenReturn(segmento);
//                mockMvc.perform(get("/segmentos/{id}", 3L))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Segmento sem token, retornar status 401")
//            public void deletarSemToken() throws Exception {
//                when(service.deletar(3L)).thenReturn(segmento);
//                mockMvc.perform(delete("/segmentos/1"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Segmento sem token, retornar status 401")
//            public void criarSemToken() throws Exception {
//                when(service.salvar(segmento)).thenReturn(segmento);
//                String jsonString = mapper.writeValueAsString(segmento);
//                mockMvc.perform(post("/segmentos")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).salvar(segmento);
//            }
//
//            @Test
//            @DisplayName("Atualizar Segmento sem token, retornar status 401")
//            public void atualizarSemToken() throws Exception {
//                when(service.atualizar(1L, segmento)).thenReturn(segmento);
//                String jsonString = mapper.writeValueAsString(segmento);
//                mockMvc.perform(put("/segmentos/1")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .atualizar(1L, segmento);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, acesso sem permissão.")
//        class SemPermissao {
//            @Autowired
//            private MockMvc mockMvc;
//            @MockBean
//            private SegmentoService service;
//            @Mock
//            private Page<Segmento> page;
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Segmentos sem permissão de acesso, retornar o status 403")
//            public void permissaoListar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/segmentos")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Segmentos usando filtro pelo nome sem permissão de acesso, retornar o status 403")
//            public void permissaoListarsNome() throws Exception {
//                when(service.filtrar(filterNome, pageable)).thenReturn(page);
//                mockMvc.perform(get("/segmentos?nome=nome")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filterNome, pageable);
//            }
//
//
//            @Test
//            @DisplayName("Buscar Segmentos usando o id sem permissão de acesso, retornar o status 403")
//            public void permissaoBuscar() throws Exception {
//                Segmento segmento = new Segmento(3L, "nome", null);
//                when(service.detalhar(3L)).thenReturn(segmento);
//                mockMvc.perform(get("/segmentos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Segmento sem permissão de acesso, retornar a Segmento e status 403")
//            public void permissaoDeletar() throws Exception {
//                mockMvc.perform(delete("/segmentos/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print());
//                verify(service, times(0)).deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Segmento sem permissão de acesso, retornar o status 403")
//            public void permissaoCriar() throws Exception {
//                Segmento segmento = new Segmento(1L, "nome", null);
//                when(service.salvar(segmento)).thenReturn(segmento);
//                String jsonString = mapper.writeValueAsString(segmento);
//                mockMvc.perform(post("/segmentos")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).salvar(Mockito.any(Segmento.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Segmento sem permissão de acesso, retornar o status 403")
//            public void permissaoAtualizar() throws Exception {
//                Segmento segmento = new Segmento(1L, "nome", null);
//                when(service.atualizar(1L, segmento)).thenReturn(segmento);
//                String jsonString = mapper.writeValueAsString(segmento);
//                mockMvc.perform(put("/segmentos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .atualizar(1L, segmento);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, validações das entidades.")
//        class ValidacoesEntidade {
//            @Autowired
//            private MockMvc mockMvc;
//            @MockBean
//            private SegmentoService service;
//
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Criar Segmento informando um nome null, retornar mensagem de erro e status 400.")
//            public void criarNomeNull() throws Exception {
//                when(service.salvar(nomeNull)).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(post("/segmentos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0)).salvar(nomeNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Segmento informando um nome null, retornar mensagem de erro e status 400.")
//            public void atualizarNomeNull() throws Exception {
//                when(service.atualizar(1L, nomeNull)).thenReturn(nomeNull);
//                String jsonString = mapper.writeValueAsString(nomeNull);
//                mockMvc.perform(put("/segmentos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome não pode ser null."));
//                verify(service, times(0)).atualizar(1L, nomeNull);
//            }
//
//
//            @Test
//            @DisplayName("Criar Segmento informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void criarNomeVazio() throws Exception {
//                when(service.salvar(nomeVazio)).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(post("/segmentos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).salvar(nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Atualizar Segmento informando um nome vazio, retornar mensagem de erro e status 400.")
//            public void atualizarNomeVazio() throws Exception {
//                when(service.atualizar(1L, nomeVazio)).thenReturn(nomeVazio);
//                String jsonString = mapper.writeValueAsString(nomeVazio);
//                mockMvc.perform(put("/segmentos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).atualizar(1L, nomeVazio);
//            }
//
//            @Test
//            @DisplayName("Criar Segmento informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAcima50Caracteres() throws Exception {
//                when(service.salvar(nomeMaior50)).thenReturn(nomeMaior50);
//                String jsonString = mapper.writeValueAsString(nomeMaior50);
//                mockMvc.perform(post("/segmentos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .salvar(nomeMaior50);
//            }
//
//            @Test
//            @DisplayName("Atualizar Segmento informando um nome acima de 50 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAcima50Caracteres() throws Exception {
//                when(service.atualizar(1L, nomeMaior50)).thenReturn(nomeMaior50);
//                String jsonString = mapper.writeValueAsString(nomeMaior50);
//                mockMvc.perform(put("/segmentos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, nomeMaior50);
//            }
//
//            @Test
//            @DisplayName("Criar Segmento informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNomeAbaixo4Caracteres() throws Exception {
//                when(service.salvar(nomeMenor4)).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(post("/segmentos")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).salvar(nomeMenor4);
//            }
//
//            @Test
//            @DisplayName("Atualizar Segmento informando um nome abaixo de 4 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNomeAbaixo4Caracteres() throws Exception {
//                when(service.atualizar(1L, nomeMenor4)).thenReturn(nomeMenor4);
//                String jsonString = mapper.writeValueAsString(nomeMenor4);
//                mockMvc.perform(put("/segmentos/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("nome"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O nome deve ter entre 4 e 50 caracteres."));
//                verify(service, times(0)).atualizar(1L, nomeMenor4);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service")
//        class Service {
//        }
//    }
//
//    // TODO: Falta fazer os testes de service e repository
//    @Nested
//    @DisplayName("Telefone")
//    class TelefoneTestes {
//
//        @Nested
//        @DisplayName("Testando End Point, token valido.")
//        class TokenValido {
//            @Autowired
//            private MockMvc mockMvc;
//            @MockBean
//            private TelefoneService service;
//            @Mock
//            private Page<Telefone> page;
//
//            private final TelefoneFilter filter = new TelefoneFilter();
//            private final TelefoneFilter filterNumero = new TelefoneFilter("+999 (99) 99999-9999", null);
//            private final TelefoneFilter filterTipo = new TelefoneFilter(null, TipoTelefone.COMERCIAL);
//            private final Telefone telefone = new Telefone(3L, "+999 (99) 99999-9999", TipoTelefone.COMERCIAL, new Contato(1L));
//
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Telefones, retornar a Telefones e status 200")
//            public void listar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/telefones")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Telefones usando filtro pelo numero, retornar a Telefones e status 200")
//            public void listarnumero() throws Exception {
//                when(service.filtrar(filterNumero, pageable)).thenReturn(page);
//                mockMvc.perform(get("/telefones?numero=+999 (99) 99999-9999")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterNumero, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Telefones usando filtro pela tipo, retornar a Telefones e status 200")
//            public void listartipo() throws Exception {
//                when(service.filtrar(filterTipo, pageable)).thenReturn(page);
//                mockMvc.perform(get("/telefones?tipo={tipo}", TipoTelefone.COMERCIAL)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1))
//                        .filtrar(filterTipo, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Telefones usando o id, retornar a Telefones e status 200 sucesso")
//            public void buscar() throws Exception {
//                when(service.detalhar(3L)).thenReturn(telefone);
//                mockMvc.perform(get("/telefones/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.numero").value("+999 (99) 99999-9999"))
//                        .andExpect(jsonPath("$.tipo").value("COMERCIAL"));
//                verify(service, times(1)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Telefones, retornar a Telefone e status 200")
//            public void deletar() throws Exception {
//                mockMvc.perform(delete("/telefones/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isOk())
//                        .andDo(print());
//                verify(service, times(1)).deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Telefones, retornar a Telefones e status 201")
//            public void criar() throws Exception {
//                when(service.salvar(telefone)).thenReturn(telefone);
//                String jsonString = mapper.writeValueAsString(telefone);
//                mockMvc.perform(post("/telefones")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.numero").value("+999 (99) 99999-9999"))
//                        .andExpect(jsonPath("$.tipo").value("COMERCIAL"));
//                verify(service, times(1)).salvar(Mockito.any(Telefone.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Telefones, retornar a Telefones e status 201")
//            public void atualizar() throws Exception {
//                when(service.atualizar(1L, telefone)).thenReturn(telefone);
//                String jsonString = mapper.writeValueAsString(telefone);
//                mockMvc.perform(put("/telefones/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isCreated())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.numero").value("+999 (99) 99999-9999"))
//                        .andExpect(jsonPath("$.tipo").value("COMERCIAL"));
//                verify(service, times(1))
//                        .atualizar(1L, telefone);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, token invalido.")
//        class TokenInvalido {
//            @Autowired
//            private MockMvc mockMvc;
//            @MockBean
//            private TelefoneService service;
//            @Mock
//            private Page<Telefone> page;
//
//            private final TelefoneFilter filter = new TelefoneFilter();
//            private final Telefone telefone = new Telefone(3L, "+999 (99) 99999-9999", TipoTelefone.COMERCIAL, new Contato(1L));
//
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Telefone com usuario e senha incorretos, retornar status 401")
//            public void listamosTokenIncorreto() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/telefones")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Telefone usando usuario e senha incorretos, retornar status 401")
//            public void buscarTokenIncorreto() throws Exception {
//                when(service.detalhar(3L)).thenReturn(telefone);
//                mockMvc.perform(get("/telefones/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Telefone com usuario e senha incorretos, retornar status 401")
//            public void deletarTokenIncorreto() throws Exception {
//                when(service.deletar(3L)).thenReturn(telefone);
//                mockMvc.perform(delete("/telefones/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Telefone com usuario e senha incorretos, retornar status 401")
//            public void criarTokenIncorreto() throws Exception {
//                when(service.salvar(telefone)).thenReturn(telefone);
//                String jsonString = mapper.writeValueAsString(telefone);
//                mockMvc.perform(post("/telefones")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, telefone);
//            }
//
//            @Test
//            @DisplayName("Atualizar Telefone com usuario e senha incorretos, retornar status 401")
//            public void atualizarTokenIncorreto() throws Exception {
//                when(service.atualizar(1L, telefone)).thenReturn(telefone);
//                String jsonString = mapper.writeValueAsString(telefone);
//                mockMvc.perform(put("/telefones/1")
//                                .header("Authorization", "Bearer " + getAccessToken("a", "a", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("invalid_token"));
//                verify(service, times(0))
//                        .atualizar(1L, telefone);
//            }
//
//            @Test
//            @DisplayName("Listar Telefones sem token, retornar status 401")
//            public void listamosSemToken() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/telefones"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Telefones sem token, retornar status 401")
//            public void buscarSemToken() throws Exception {
//                when(service.detalhar(3L)).thenReturn(telefone);
//                mockMvc.perform(get("/telefones/{id}", 3L))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Telefone sem token, retornar status 401")
//            public void deletarSemToken() throws Exception {
//                when(service.deletar(3L)).thenReturn(telefone);
//                mockMvc.perform(delete("/telefones/1"))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).deletar(1L);
//            }
//
//            @Test
//            @DisplayName("Criar Telefone sem token, retornar status 401")
//            public void criarSemToken() throws Exception {
//                when(service.salvar(telefone)).thenReturn(telefone);
//                String jsonString = mapper.writeValueAsString(telefone);
//                mockMvc.perform(post("/telefones")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0)).salvar(telefone);
//            }
//
//            @Test
//            @DisplayName("Atualizar Telefone sem token, retornar status 401")
//            public void atualizarSemToken() throws Exception {
//                when(service.atualizar(1L, telefone)).thenReturn(telefone);
//                String jsonString = mapper.writeValueAsString(telefone);
//                mockMvc.perform(put("/telefones/1")
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isUnauthorized())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("unauthorized"));
//                verify(service, times(0))
//                        .atualizar(1L, telefone);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, acesso sem permissão.")
//        class SemPermissao {
//            @Autowired
//            private MockMvc mockMvc;
//            @MockBean
//            private TelefoneService service;
//            @Mock
//            private Page<Telefone> page;
//
//            private final TelefoneFilter filter = new TelefoneFilter();
//            private final TelefoneFilter filterNumero = new TelefoneFilter("+999 (99) 99999-9999", null);
//            private final Telefone telefone = new Telefone(3L, "+999 (99) 99999-9999", TipoTelefone.COMERCIAL, new Contato(1L));
//
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Listar Telefones sem permissão de acesso, retornar o status 403")
//            public void permissaoListar() throws Exception {
//                when(service.filtrar(filter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/telefones")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filter, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Telefones usando filtro pelo numero sem permissão de acesso, retornar o status 403")
//            public void permissaoListarsNumero() throws Exception {
//                when(service.filtrar(filterNumero, pageable)).thenReturn(page);
//                mockMvc.perform(get("/telefones?numero=99999999999")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(filterNumero, pageable);
//            }
//
//            @Test
//            @DisplayName("Listar Telefones usando filtro pela tipo sem permissão de acesso, retornar o status 403")
//            public void permissaoListarTipo() throws Exception {
//                TelefoneFilter campanhaFilter = new TelefoneFilter(null, TipoTelefone.COMERCIAL);
//                when(service.filtrar(campanhaFilter, pageable)).thenReturn(page);
//                mockMvc.perform(get("/telefones?tipo={tipo}", TipoTelefone.COMERCIAL)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .filtrar(campanhaFilter, pageable);
//            }
//
//            @Test
//            @DisplayName("Buscar Telefones usando o id sem permissão de acesso, retornar o status 403")
//            public void permissaoBuscar() throws Exception {
//                Telefone telefone = new Telefone(3L, "numero", TipoTelefone.COMERCIAL, new Contato(1L));
//                when(service.detalhar(3L)).thenReturn(telefone);
//                mockMvc.perform(get("/telefones/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).detalhar(3L);
//            }
//
//            @Test
//            @DisplayName("Deletar Telefone sem permissão de acesso, retornar a Telefone e status 403")
//            public void permissaoDeletar() throws Exception {
//                mockMvc.perform(delete("/telefones/{id}", 3L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).deletar(3L);
//            }
//
//            @Test
//            @DisplayName("Criar Telefone sem permissão de acesso, retornar o status 403")
//            public void permissaoCriar() throws Exception {
//                when(service.salvar(telefone)).thenReturn(telefone);
//                String jsonString = mapper.writeValueAsString(telefone);
//                mockMvc.perform(post("/telefones")
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0)).salvar(Mockito.any(Telefone.class));
//            }
//
//            @Test
//            @DisplayName("Atualizar Telefone sem permissão de acesso, retornar o status 403")
//            public void permissaoAtualizar() throws Exception {
//                when(service.atualizar(1L, telefone)).thenReturn(telefone);
//                String jsonString = mapper.writeValueAsString(telefone);
//                mockMvc.perform(put("/telefones/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("user", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isForbidden())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.error").value("access_denied"));
//                verify(service, times(0))
//                        .atualizar(1L, telefone);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando End Point, validações das entidades.")
//        class ValidacoesEntidade {
//            @Autowired
//            private MockMvc mockMvc;
//            @MockBean
//            private TelefoneService service;
//
//            private final Telefone numeroNull = new Telefone(1L, null, TipoTelefone.COMERCIAL, null);
//            private final Telefone numeroVazio = new Telefone(1L, "", TipoTelefone.COMERCIAL, null);
//            private final Telefone tipoNull = new Telefone(1L, "+999 (99) 99999-9999", null, null);
//            private final Telefone numeroMaior20 = new Telefone(1L, "+999 (99) 99999-9999 9", TipoTelefone.COMERCIAL, null);
//            private final Telefone numeroMenor10 = new Telefone(1L, "999999999", TipoTelefone.COMERCIAL, null);
//
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Test
//            @DisplayName("Criar Telefone informando um numero null, retornar mensagem de erro e status 400.")
//            public void criarNumeroNull() throws Exception {
//                when(service.salvar(numeroNull)).thenReturn(numeroNull);
//                String jsonString = mapper.writeValueAsString(numeroNull);
//                mockMvc.perform(post("/telefones")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("numero"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O número não pode ser null."));
//                verify(service, times(0)).salvar(numeroNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Telefone informando um numero null, retornar mensagem de erro e status 400.")
//            public void atualizarNumeroNull() throws Exception {
//                when(service.atualizar(1L, numeroNull)).thenReturn(numeroNull);
//                String jsonString = mapper.writeValueAsString(numeroNull);
//                mockMvc.perform(put("/telefones/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("numero"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O número não pode ser null."));
//                verify(service, times(0))
//                        .atualizar(1L, numeroNull);
//            }
//
//            @Test
//            @DisplayName("Criar Telefone informando um tipo null, retornar mensagem de erro e status 400.")
//            public void criarTipoNull() throws Exception {
//                when(service.salvar(tipoNull)).thenReturn(tipoNull);
//                String jsonString = mapper.writeValueAsString(tipoNull);
//                mockMvc.perform(post("/telefones")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("tipo"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O tipo não pode ser null."));
//                verify(service, times(0)).salvar(tipoNull);
//            }
//
//            @Test
//            @DisplayName("Atualizar Telefone informando um tipo null, retornar mensagem de erro e status 400.")
//            public void atualizarTipoNull() throws Exception {
//                when(service.atualizar(1L, tipoNull)).thenReturn(tipoNull);
//                String jsonString = mapper.writeValueAsString(tipoNull);
//                mockMvc.perform(put("/telefones/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("tipo"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O tipo não pode ser null."));
//                verify(service, times(0))
//                        .atualizar(1L, tipoNull);
//            }
//
//            @Test
//            @DisplayName("Criar Telefone informando um numero vazio, retornar mensagem de erro e status 400.")
//            public void criarnumeroVazio() throws Exception {
//                when(service.salvar(numeroVazio)).thenReturn(numeroVazio);
//                String jsonString = mapper.writeValueAsString(numeroVazio);
//                mockMvc.perform(post("/telefones")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("numero"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O número deve ter entre 10 e 20 caracteres."));
//                verify(service, times(0)).salvar(numeroVazio);
//            }
//
//            @Test
//            @DisplayName("Atualizar Telefone informando um numero vazio, retornar mensagem de erro e status 400.")
//            public void atualizarnumeroVazio() throws Exception {
//                when(service.atualizar(1L, numeroVazio)).thenReturn(numeroVazio);
//                String jsonString = mapper.writeValueAsString(numeroVazio);
//                mockMvc.perform(put("/telefones/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("numero"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O número deve ter entre 10 e 20 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, numeroVazio);
//            }
//
//            @Test
//            @DisplayName("Criar Telefone informando um numero acima de 11 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNumeroAcima20Caracteres() throws Exception {
//                when(service.salvar(numeroMaior20)).thenReturn(numeroMaior20);
//                String jsonString = mapper.writeValueAsString(numeroMaior20);
//                mockMvc.perform(post("/telefones")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("numero"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O número deve ter entre 10 e 20 caracteres."));
//                verify(service, times(0)).salvar(numeroMaior20);
//            }
//
//            @Test
//            @DisplayName("Atualizar Telefone informando um numero acima de 20 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNumeroAcima20Caracteres() throws Exception {
//                when(service.atualizar(1L, numeroMaior20)).thenReturn(numeroMaior20);
//                String jsonString = mapper.writeValueAsString(numeroMaior20);
//                mockMvc.perform(put("/telefones/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("numero"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O número deve ter entre 10 e 20 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, numeroMaior20);
//            }
//
//            @Test
//            @DisplayName("Criar Telefone informando um numero abaixo de 20 caracteres, retornar mensagem de erro e status 400.")
//            public void criarNumeroAbaixo10Caracteres() throws Exception {
//                when(service.salvar(numeroMenor10)).thenReturn(numeroMenor10);
//                String jsonString = mapper.writeValueAsString(numeroMenor10);
//                mockMvc.perform(post("/telefones")
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("numero"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O número deve ter entre 10 e 20 caracteres."));
//                verify(service, times(0)).salvar(numeroMenor10);
//            }
//
//            @Test
//            @DisplayName("Atualizar Telefone informando um numero abaixo 10 caracteres, retornar mensagem de erro e status 400.")
//            public void atualizarNumeroAbaixo11Caracteres() throws Exception {
//                when(service.atualizar(1L, numeroMenor10)).thenReturn(numeroMenor10);
//                String jsonString = mapper.writeValueAsString(numeroMenor10);
//                mockMvc.perform(put("/telefones/{id}", 1L)
//                                .header("Authorization", "Bearer " + getAccessToken("admin", "123321", mockMvc))
//                                .accept(contentType)
//                                .content(jsonString)
//                                .contentType(contentType))
//                        .andExpect(content().contentType(contentType))
//                        .andExpect(status().isBadRequest())
//                        .andDo(print())
//                        .andExpect(jsonPath("$.field").value("numero"))
//                        .andExpect(jsonPath("$.fieldMessage").value("O número deve ter entre 10 e 20 caracteres."));
//                verify(service, times(0))
//                        .atualizar(1L, numeroMenor10);
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service")
//        class Service {
//        }
//    }
//
//    // TODO: falta fazer os testes
//    @Nested
//    @DisplayName("Tarefa")
//    class TarefaTestes {
//        @Nested
//        @DisplayName("Testando End Point")
//        public class EndPoint {
//            @Autowired
//            private MockMvc mockMvc;
//            @MockBean
//            private TarefaService service;
//            @Mock
//            private Page<Tarefa> page;
//            private final ObjectMapper mapper = new ObjectMapper();
//            private final LocalDateTime data = LocalDateTime.now();
//            private final TarefaFilter tarefaFilter = new TarefaFilter();
////            private final TarefaFilter tarefaFilterAssunto = new TarefaFilter("assunto", null, null, null, null, null);
////            private final TarefaFilter tarefaFilterDescricao = new TarefaFilter(null, "descricao", null, null, null, null);
////            private final TarefaFilter tarefaFilterHoraMarcada = new TarefaFilter(null, null, data, null, null, null);
////            private final TarefaFilter tarefaFilterRealizada = new TarefaFilter(null, null, null, true, null, null);
////            private final TarefaFilter tarefaFilterHoraRealizada = new TarefaFilter(null, null, null, null, data, null);
////            private final TarefaFilter tarefaFilterTipo = new TarefaFilter(null, null, null, null, null, TipoTarefa.TAREFA);
//
//            private final Tarefa tarefa = new Tarefa(3L, "assunto", "descricao", data, true, data, TipoTarefa.TAREFA, null, null);
//
//            @Nested
//            @DisplayName("Testando o token valido.")
//            class TokenValido {
//
//            }
//
//            @Nested
//            @DisplayName("Testando o token invalido.")
//            class TokenInvalido {
//            }
//
//            @Nested
//            @DisplayName("Testando o acesso sem permissão.")
//            class SemPermissao {
//            }
//
//            @Nested
//            @DisplayName("Testando as validações da entidade.")
//            class ValidacoesEntidade {
//
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service")
//        class Service {
//        }
//    }
//
//    // TODO: falta fazer os testes
//    @Nested
//    @DisplayName("Usuário")
//    class UsuarioTestes {
//        @Nested
//        @DisplayName("Testando End Point")
//        public class EndPoint {
//
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Nested
//            @DisplayName("Testando o token valido.")
//            class TokenValido {
//            }
//
//            @Nested
//            @DisplayName("Testando o token invalido.")
//            class TokenInvalido {
//            }
//
//            @Nested
//            @DisplayName("Testando o acesso sem permissão.")
//            class SemPermissao {
//            }
//
//            @Nested
//            @DisplayName("Testando as validações da entidade.")
//            class ValidacoesEntidade {
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service")
//        class Service {
//        }
//    }
//
//    // TODO: falta fazer os testes
//    @Nested
//    @DisplayName("Negociação")
//    class NegociacaoTestes {
//
//        @Nested
//        @DisplayName("Testando End Point")
//        public class EndPoint {
//
//
//            @BeforeEach
//            void init() {
//                mapper = new ObjectMapper();
//            }
//
//            @Nested
//            @DisplayName("Testando o token valido.")
//            class TokenValido {
//            }
//
//            @Nested
//            @DisplayName("Testando o token invalido.")
//            class TokenInvalido {
//            }
//
//            @Nested
//            @DisplayName("Testando o acesso sem permissão.")
//            class SemPermissao {
//            }
//
//            @Nested
//            @DisplayName("Testando as validações da entidade.")
//            class ValidacoesEntidade {
//            }
//        }
//
//        @Nested
//        @DisplayName("Testando o repositorio.")
//        class Repository {
//        }
//
//        @Nested
//        @DisplayName("Testando as validações da service")
//        class Service {
//        }
//    }
//
//}
