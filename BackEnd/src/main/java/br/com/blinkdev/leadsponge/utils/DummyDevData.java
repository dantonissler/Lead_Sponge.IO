package br.com.blinkdev.leadsponge.utils;

import br.com.blinkdev.leadsponge.models.campanha.Campanha;
import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacao;
import br.com.blinkdev.leadsponge.models.fonteNegociacao.FonteNegociacao;
import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import br.com.blinkdev.leadsponge.models.segmento.Segmento;
import br.com.blinkdev.leadsponge.models.usuario.Usuario;
import br.com.blinkdev.leadsponge.repository.campanha.CampanhaRepository;
import br.com.blinkdev.leadsponge.repository.estagioNegociacao.EstagioNegociacaoRepository;
import br.com.blinkdev.leadsponge.repository.fonteNegociacao.FonteNegociacaoRepository;
import br.com.blinkdev.leadsponge.repository.motivoPerda.MotivoPerdaRepository;
import br.com.blinkdev.leadsponge.repository.role.RoleRepository;
import br.com.blinkdev.leadsponge.services.SegmentoService;
import br.com.blinkdev.leadsponge.services.UsuarioService;
import br.com.blinkdev.leadsponge.models.role.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.HashSet;

@Profile("dev")
@PropertySource("classpath:application-dev.properties")
@Configuration
class DummyDevData {
    @Bean
    CommandLineRunner initTableRole(RoleRepository roleRepository) {
        return args -> {
            roleRepository.save(new Role("CADASTRAR_USUARIO"));
            roleRepository.save(new Role("REMOVER_USUARIO"));
            roleRepository.save(new Role("PESQUISAR_USUARIO"));

            roleRepository.save(new Role("CADASTRAR_CLIENTE"));
            roleRepository.save(new Role("REMOVER_CLIENTE"));
            roleRepository.save(new Role("PESQUISAR_CLIENTE"));

            roleRepository.save(new Role("CADASTRAR_NEGOCIACAO"));
            roleRepository.save(new Role("REMOVER_NEGOCIACAO"));
            roleRepository.save(new Role("PESQUISAR_NEGOCIACAO"));

            roleRepository.save(new Role("CADASTRAR_CAMPANHA"));
            roleRepository.save(new Role("REMOVER_CAMPANHA"));
            roleRepository.save(new Role("PESQUISAR_CAMPANHA"));

            roleRepository.save(new Role("CADASTRAR_CONTATO"));
            roleRepository.save(new Role("REMOVER_CONTATO"));
            roleRepository.save(new Role("PESQUISAR_CONTATO"));

            roleRepository.save(new Role("CADASTRAR_FONTE"));
            roleRepository.save(new Role("REMOVER_FONTE"));
            roleRepository.save(new Role("PESQUISAR_FONTE"));

            roleRepository.save(new Role("CADASTRAR_PRODUTO"));
            roleRepository.save(new Role("REMOVER_PRODUTO"));
            roleRepository.save(new Role("PESQUISAR_PRODUTO"));

            roleRepository.save(new Role("CADASTRAR_TAREFA"));
            roleRepository.save(new Role("REMOVER_TAREFA"));
            roleRepository.save(new Role("PESQUISAR_TAREFA"));

            roleRepository.save(new Role("CADASTRAR_ESTAGIO"));
            roleRepository.save(new Role("REMOVER_ESTAGIO"));
            roleRepository.save(new Role("PESQUISAR_ESTAGIO"));

            roleRepository.save(new Role("CADASTRAR_SEGMENTO"));
            roleRepository.save(new Role("REMOVER_SEGMENTO"));
            roleRepository.save(new Role("PESQUISAR_SEGMENTO"));

            roleRepository.save(new Role("CADASTRAR_MOTIVO"));
            roleRepository.save(new Role("REMOVER_MOTIVO"));
            roleRepository.save(new Role("PESQUISAR_MOTIVO"));

            roleRepository.save(new Role("USER"));
        };
    }

    @Bean
    CommandLineRunner initTableUsuario(UsuarioService usuarioService, RoleRepository roleRepository) {
        return args -> {
            usuarioService.salvar(new Usuario("admin", "Danton Issler Rodrigues", "danton.issler18@gmail.com", "123321", "123321", true, "98ad18d9-d419-4099-864d-6a9317e6ec29_1.jpg", "/assets/img/perfil/98ad18d9-d419-4099-864d-6a9317e6ec29_1.jpg",
                    new HashSet<>(roleRepository.findAll())));
            usuarioService.salvar(new Usuario("user", "user", "user@gmail.com", "123321", "123321", true, null, null, new HashSet<>()));

        };
    }

    @Bean
    CommandLineRunner initTableCampanha(CampanhaRepository campanhaRepository) {
        return args -> campanhaRepository.save(new Campanha(null, "Sem Campanha", ""));
    }

    @Bean
    CommandLineRunner initTableFonteNegociacao(FonteNegociacaoRepository fonteNegociacaoRepository) {
        return args -> {
            fonteNegociacaoRepository.save(new FonteNegociacao("Cliente Ativo"));
            fonteNegociacaoRepository.save(new FonteNegociacao("Contato pelo Site"));
            fonteNegociacaoRepository.save(new FonteNegociacao("Contato por E-mail"));
            fonteNegociacaoRepository.save(new FonteNegociacao("Contato por Telefone"));
            fonteNegociacaoRepository.save(new FonteNegociacao("E-mail Marketing"));
            fonteNegociacaoRepository.save(new FonteNegociacao("Feiras e Eventos"));
            fonteNegociacaoRepository.save(new FonteNegociacao("Google e Outros Buscadores"));
            fonteNegociacaoRepository.save(new FonteNegociacao("Indicação por Clientes"));
            fonteNegociacaoRepository.save(new FonteNegociacao("Indicação por Parceiros"));
            fonteNegociacaoRepository.save(new FonteNegociacao("Prospecção Ativa"));
            fonteNegociacaoRepository.save(new FonteNegociacao("Redes Sociais"));
        };
    }

    @Bean
    CommandLineRunner initTableEstagio(EstagioNegociacaoRepository estagioNegociacaoRepository) {
        return args -> estagioNegociacaoRepository.saveAll(Arrays.asList(new EstagioNegociacao("Sem contato", "SC", 1), new EstagioNegociacao("Contrato Feito", "CF", 2), new EstagioNegociacao("Identificação de Interece", "IDI", 3),
                new EstagioNegociacao("Apresentação", "A", 4), new EstagioNegociacao("Proposta Enviada", "PE", 5)));
    }

    @Bean
    CommandLineRunner initTablePerda(MotivoPerdaRepository motivoPerdaRepository) {
        return args -> {
            motivoPerdaRepository.save(new MotivoPerda(null, "Cliente optou por não realizar o projeto", null));
            motivoPerdaRepository.save(new MotivoPerda(null, "Demora no follow", null));
            motivoPerdaRepository.save(new MotivoPerda(null, "Fechou com outra empresa", null));
            motivoPerdaRepository.save(new MotivoPerda(null, "Não gostou do produto/serviço", null));
            motivoPerdaRepository.save(new MotivoPerda(null, "Outros", null));
            motivoPerdaRepository.save(new MotivoPerda(null, "Preço", null));
        };
    }

    @Bean
    CommandLineRunner initTableSegmento(SegmentoService segmentoService) {
        return args -> {
            segmentoService.salvar(new Segmento("Advocacia"));
            segmentoService.salvar(new Segmento("Agropecuária"));
            segmentoService.salvar(new Segmento("Arquitetura"));
            segmentoService.salvar(new Segmento("Bancos"));
            segmentoService.salvar(new Segmento("Blogs e Sites Pessoais"));
            segmentoService.salvar(new Segmento("Clinicas / Saúde"));
            segmentoService.salvar(new Segmento("Concessionárias de Veículos"));
            segmentoService.salvar(new Segmento("Contabilidade"));
            segmentoService.salvar(new Segmento("Demolição e terraplanagem"));
            segmentoService.salvar(new Segmento("Distribuidoras / Atacadistas"));
            segmentoService.salvar(new Segmento("Engenharia"));
            segmentoService.salvar(new Segmento("Esoterismo"));
            segmentoService.salvar(new Segmento("Eventos e Entretenimento"));
            segmentoService.salvar(new Segmento("Fotografia"));
            segmentoService.salvar(new Segmento("Gráfica"));
            segmentoService.salvar(new Segmento("Indústria"));
            segmentoService.salvar(new Segmento("Instituições Religiosas"));
            segmentoService.salvar(new Segmento("Músicos e Bandas"));
            segmentoService.salvar(new Segmento("Pessoa física"));
            segmentoService.salvar(new Segmento("Pet Shop"));
            segmentoService.salvar(new Segmento("Poder público"));
            segmentoService.salvar(new Segmento("Publicidade"));
            segmentoService.salvar(new Segmento("Serviços"));
            segmentoService.salvar(new Segmento("Shoppings e Feiras Comerciais"));
            segmentoService.salvar(new Segmento("Sindicato Patronal"));
            segmentoService.salvar(new Segmento("Tecnologia"));
            segmentoService.salvar(new Segmento("Telefonia"));
            segmentoService.salvar(new Segmento("Terceiro Setor"));
            segmentoService.salvar(new Segmento("Turismo"));
        };
    }
}
