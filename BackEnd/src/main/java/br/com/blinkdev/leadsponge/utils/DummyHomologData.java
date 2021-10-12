package br.com.blinkdev.leadsponge.utils;

import br.com.blinkdev.leadsponge.endpoints.campaign.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.endpoints.campaign.repository.CampaignRepository;
import br.com.blinkdev.leadsponge.endpoints.negotiationSource.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiationSource.repository.NegotiationSourceRepository;
import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.repository.NegotiationStyleRepository;
import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.repository.ReasonForLossRepository;
import br.com.blinkdev.leadsponge.endpoints.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.endpoints.role.repository.RoleRepository;
import br.com.blinkdev.leadsponge.endpoints.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.endpoints.segment.service.SegmentService;
import br.com.blinkdev.leadsponge.endpoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.endpoints.user.service.UserService;
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
    CommandLineRunner initTableUsuario(UserService usuarioService, RoleRepository roleRepository) {
        return args -> {
            usuarioService.save(new UserEntity("admin", "Danton Issler Rodrigues", "danton.issler18@gmail.com", "123321", "123321", true, "98ad18d9-d419-4099-864d-6a9317e6ec29_1.jpg", "/assets/img/perfil/98ad18d9-d419-4099-864d-6a9317e6ec29_1.jpg",
                    new HashSet<>(roleRepository.findAll())));
            usuarioService.save(new UserEntity("user", "user", "user@gmail.com", "123321", "123321", true, null, null, new HashSet<>()));

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
    CommandLineRunner initTableSegmento(SegmentService segmentoService) {
        return args -> {
            segmentoService.save(new SegmentEntity("Advocacia"));
            segmentoService.save(new SegmentEntity("Agropecuária"));
            segmentoService.save(new SegmentEntity("Arquitetura"));
            segmentoService.save(new SegmentEntity("Bancos"));
            segmentoService.save(new SegmentEntity("Blogs e Sites Pessoais"));
            segmentoService.save(new SegmentEntity("Clinicas / Saúde"));
            segmentoService.save(new SegmentEntity("Concessionárias de Veículos"));
            segmentoService.save(new SegmentEntity("Contabilidade"));
            segmentoService.save(new SegmentEntity("Demolição e terraplanagem"));
            segmentoService.save(new SegmentEntity("Distribuidoras / Atacadistas"));
            segmentoService.save(new SegmentEntity("Engenharia"));
            segmentoService.save(new SegmentEntity("Esoterismo"));
            segmentoService.save(new SegmentEntity("Eventos e Entretenimento"));
            segmentoService.save(new SegmentEntity("Fotografia"));
            segmentoService.save(new SegmentEntity("Gráfica"));
            segmentoService.save(new SegmentEntity("Indústria"));
            segmentoService.save(new SegmentEntity("Instituições Religiosas"));
            segmentoService.save(new SegmentEntity("Músicos e Bandas"));
            segmentoService.save(new SegmentEntity("Pessoa física"));
            segmentoService.save(new SegmentEntity("Pet Shop"));
            segmentoService.save(new SegmentEntity("Poder público"));
            segmentoService.save(new SegmentEntity("Publicidade"));
            segmentoService.save(new SegmentEntity("Serviços"));
            segmentoService.save(new SegmentEntity("Shoppings e Feiras Comerciais"));
            segmentoService.save(new SegmentEntity("Sindicato Patronal"));
            segmentoService.save(new SegmentEntity("Tecnologia"));
            segmentoService.save(new SegmentEntity("Telefonia"));
            segmentoService.save(new SegmentEntity("Terceiro Setor"));
            segmentoService.save(new SegmentEntity("Turismo"));
        };
    }
}
