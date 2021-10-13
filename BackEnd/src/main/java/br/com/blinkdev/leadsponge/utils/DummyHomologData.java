package br.com.blinkdev.leadsponge.utils;

import br.com.blinkdev.leadsponge.end_points.campaign.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.end_points.campaign.repository.CampaignRepository;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.repository.NegotiationSourceRepository;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.repository.NegotiationStyleRepository;
import br.com.blinkdev.leadsponge.end_points.reason_for_loss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.end_points.reason_for_loss.repository.ReasonForLossRepository;
import br.com.blinkdev.leadsponge.end_points.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.end_points.role.repository.RoleRepository;
import br.com.blinkdev.leadsponge.end_points.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.end_points.segment.service.SegmentService;
import br.com.blinkdev.leadsponge.end_points.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.end_points.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.HashSet;

@Profile("homolog")
@PropertySource("classpath:application-homolog.properties")
@Configuration
class DummyHomologData {
    @Bean
    CommandLineRunner initTableRole(RoleRepository roleRepository) {
        return args -> {
            roleRepository.save(new RoleEntity("CADASTRAR_USUARIO"));
            roleRepository.save(new RoleEntity("REMOVER_USUARIO"));
            roleRepository.save(new RoleEntity("PESQUISAR_USUARIO"));

            roleRepository.save(new RoleEntity("CADASTRAR_CLIENTE"));
            roleRepository.save(new RoleEntity("REMOVER_CLIENTE"));
            roleRepository.save(new RoleEntity("PESQUISAR_CLIENTE"));

            roleRepository.save(new RoleEntity("CADASTRAR_NEGOCIACAO"));
            roleRepository.save(new RoleEntity("REMOVER_NEGOCIACAO"));
            roleRepository.save(new RoleEntity("PESQUISAR_NEGOCIACAO"));

            roleRepository.save(new RoleEntity("CADASTRAR_CAMPANHA"));
            roleRepository.save(new RoleEntity("REMOVER_CAMPANHA"));
            roleRepository.save(new RoleEntity("PESQUISAR_CAMPANHA"));

            roleRepository.save(new RoleEntity("CADASTRAR_CONTATO"));
            roleRepository.save(new RoleEntity("REMOVER_CONTATO"));
            roleRepository.save(new RoleEntity("PESQUISAR_CONTATO"));

            roleRepository.save(new RoleEntity("CADASTRAR_FONTE"));
            roleRepository.save(new RoleEntity("REMOVER_FONTE"));
            roleRepository.save(new RoleEntity("PESQUISAR_FONTE"));

            roleRepository.save(new RoleEntity("CADASTRAR_PRODUTO"));
            roleRepository.save(new RoleEntity("REMOVER_PRODUTO"));
            roleRepository.save(new RoleEntity("PESQUISAR_PRODUTO"));

            roleRepository.save(new RoleEntity("CADASTRAR_TAREFA"));
            roleRepository.save(new RoleEntity("REMOVER_TAREFA"));
            roleRepository.save(new RoleEntity("PESQUISAR_TAREFA"));

            roleRepository.save(new RoleEntity("CADASTRAR_ESTAGIO"));
            roleRepository.save(new RoleEntity("REMOVER_ESTAGIO"));
            roleRepository.save(new RoleEntity("PESQUISAR_ESTAGIO"));

            roleRepository.save(new RoleEntity("CADASTRAR_SEGMENTO"));
            roleRepository.save(new RoleEntity("REMOVER_SEGMENTO"));
            roleRepository.save(new RoleEntity("PESQUISAR_SEGMENTO"));

            roleRepository.save(new RoleEntity("CADASTRAR_MOTIVO"));
            roleRepository.save(new RoleEntity("REMOVER_MOTIVO"));
            roleRepository.save(new RoleEntity("PESQUISAR_MOTIVO"));

            roleRepository.save(new RoleEntity("USER"));
        };
    }

    @Bean
    CommandLineRunner initTableUsuario(UserService userService, RoleRepository roleRepository) {
        return args -> {
            userService.save(new UserEntity("admin", "Danton Issler Rodrigues", "danton.issler18@gmail.com", "123321", "123321", true, "98ad18d9-d419-4099-864d-6a9317e6ec29_1.jpg", "/assets/img/perfil/98ad18d9-d419-4099-864d-6a9317e6ec29_1.jpg",
                    new HashSet<>(roleRepository.findAll())));
            userService.save(new UserEntity("user", "user", "user@gmail.com", "123321", "123321", true, null, null, new HashSet<>()));

        };
    }

    @Bean
    CommandLineRunner initTableCampanha(CampaignRepository campanhaRepository) {
        return args -> campanhaRepository.save(new CampaignEntity(null, "Sem Campanha", ""));
    }

    @Bean
    CommandLineRunner initTableFonteNegociacao(NegotiationSourceRepository fonteNegociacaoRepository) {
        return args -> {
            fonteNegociacaoRepository.save(new NegotiationSourceEntity("Cliente Ativo"));
            fonteNegociacaoRepository.save(new NegotiationSourceEntity("Contato pelo Site"));
            fonteNegociacaoRepository.save(new NegotiationSourceEntity("Contato por E-mail"));
            fonteNegociacaoRepository.save(new NegotiationSourceEntity("Contato por Telefone"));
            fonteNegociacaoRepository.save(new NegotiationSourceEntity("E-mail Marketing"));
            fonteNegociacaoRepository.save(new NegotiationSourceEntity("Feiras e Eventos"));
            fonteNegociacaoRepository.save(new NegotiationSourceEntity("Google e Outros Buscadores"));
            fonteNegociacaoRepository.save(new NegotiationSourceEntity("Indicação por Clientes"));
            fonteNegociacaoRepository.save(new NegotiationSourceEntity("Indicação por Parceiros"));
            fonteNegociacaoRepository.save(new NegotiationSourceEntity("Prospecção Ativa"));
            fonteNegociacaoRepository.save(new NegotiationSourceEntity("Redes Sociais"));
        };
    }

    @Bean
    CommandLineRunner initTableEstagio(NegotiationStyleRepository estagioNegociacaoRepository) {
        return args -> estagioNegociacaoRepository.saveAll(Arrays.asList(new NegotiationStyleEntity("Sem contato", "SC", 1), new NegotiationStyleEntity("Contrato Feito", "CF", 2), new NegotiationStyleEntity("Identificação de Interece", "IDI", 3),
                new NegotiationStyleEntity("Apresentação", "A", 4), new NegotiationStyleEntity("Proposta Enviada", "PE", 5)));
    }

    @Bean
    CommandLineRunner initTablePerda(ReasonForLossRepository motivoPerdaRepository) {
        return args -> {
            motivoPerdaRepository.save(new ReasonForLossEntity(null, "Cliente optou por não realizar o projeto", null));
            motivoPerdaRepository.save(new ReasonForLossEntity(null, "Demora no follow", null));
            motivoPerdaRepository.save(new ReasonForLossEntity(null, "Fechou com outra empresa", null));
            motivoPerdaRepository.save(new ReasonForLossEntity(null, "Não gostou do produto/serviço", null));
            motivoPerdaRepository.save(new ReasonForLossEntity(null, "Outros", null));
            motivoPerdaRepository.save(new ReasonForLossEntity(null, "Preço", null));
        };
    }

    @Bean
    CommandLineRunner initTableSegmento(SegmentService segmentService) {
        return args -> {
            segmentService.save(new SegmentEntity("Advocacia"));
            segmentService.save(new SegmentEntity("Agropecuária"));
            segmentService.save(new SegmentEntity("Arquitetura"));
            segmentService.save(new SegmentEntity("Bancos"));
            segmentService.save(new SegmentEntity("Blogs e Sites Pessoais"));
            segmentService.save(new SegmentEntity("Clinicas / Saúde"));
            segmentService.save(new SegmentEntity("Concessionárias de Veículos"));
            segmentService.save(new SegmentEntity("Contabilidade"));
            segmentService.save(new SegmentEntity("Demolição e terraplanagem"));
            segmentService.save(new SegmentEntity("Distribuidoras / Atacadistas"));
            segmentService.save(new SegmentEntity("Engenharia"));
            segmentService.save(new SegmentEntity("Esoterismo"));
            segmentService.save(new SegmentEntity("Eventos e Entretenimento"));
            segmentService.save(new SegmentEntity("Fotografia"));
            segmentService.save(new SegmentEntity("Gráfica"));
            segmentService.save(new SegmentEntity("Indústria"));
            segmentService.save(new SegmentEntity("Instituições Religiosas"));
            segmentService.save(new SegmentEntity("Músicos e Bandas"));
            segmentService.save(new SegmentEntity("Pessoa física"));
            segmentService.save(new SegmentEntity("Pet Shop"));
            segmentService.save(new SegmentEntity("Poder público"));
            segmentService.save(new SegmentEntity("Publicidade"));
            segmentService.save(new SegmentEntity("Serviços"));
            segmentService.save(new SegmentEntity("Shoppings e Feiras Comerciais"));
            segmentService.save(new SegmentEntity("Sindicato Patronal"));
            segmentService.save(new SegmentEntity("Tecnologia"));
            segmentService.save(new SegmentEntity("Telefonia"));
            segmentService.save(new SegmentEntity("Terceiro Setor"));
            segmentService.save(new SegmentEntity("Turismo"));
        };
    }
}
