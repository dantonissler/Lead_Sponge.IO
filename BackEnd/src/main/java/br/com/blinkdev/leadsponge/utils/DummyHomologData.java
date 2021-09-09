package br.com.blinkdev.leadsponge.utils;

import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.entity.FonteNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampanhaEntity;
import br.com.blinkdev.leadsponge.endPoints.campanha.repository.CampanhaRepository;
import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.entity.EstagioNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.motivoPerda.entity.MotivoPerdaEntity;
import br.com.blinkdev.leadsponge.endPoints.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.endPoints.segmento.entity.SegmentoEntity;
import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.endPoints.user.service.UsuarioService;
import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.repository.EstagioNegociacaoRepository;
import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.repository.FonteNegociacaoRepository;
import br.com.blinkdev.leadsponge.endPoints.motivoPerda.repository.MotivoPerdaRepository;
import br.com.blinkdev.leadsponge.endPoints.role.repository.RoleRepository;
import br.com.blinkdev.leadsponge.endPoints.segmento.service.SegmentoService;
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
    CommandLineRunner initTableUsuario(UsuarioService usuarioService, RoleRepository roleRepository) {
        return args -> {
            usuarioService.salvar(new UserEntity("admin", "Danton Issler Rodrigues", "danton.issler18@gmail.com", "123321", "123321", true, "98ad18d9-d419-4099-864d-6a9317e6ec29_1.jpg", "/assets/img/perfil/98ad18d9-d419-4099-864d-6a9317e6ec29_1.jpg",
                    new HashSet<>(roleRepository.findAll())));
            usuarioService.salvar(new UserEntity("user", "user", "user@gmail.com", "123321", "123321", true, null, null, new HashSet<>()));

        };
    }

    @Bean
    CommandLineRunner initTableCampanha(CampanhaRepository campanhaRepository) {
        return args -> campanhaRepository.save(new CampanhaEntity(null, "Sem Campanha", ""));
    }

    @Bean
    CommandLineRunner initTableFonteNegociacao(FonteNegociacaoRepository fonteNegociacaoRepository) {
        return args -> {
            fonteNegociacaoRepository.save(new FonteNegociacaoEntity("Cliente Ativo"));
            fonteNegociacaoRepository.save(new FonteNegociacaoEntity("Contato pelo Site"));
            fonteNegociacaoRepository.save(new FonteNegociacaoEntity("Contato por E-mail"));
            fonteNegociacaoRepository.save(new FonteNegociacaoEntity("Contato por Telefone"));
            fonteNegociacaoRepository.save(new FonteNegociacaoEntity("E-mail Marketing"));
            fonteNegociacaoRepository.save(new FonteNegociacaoEntity("Feiras e Eventos"));
            fonteNegociacaoRepository.save(new FonteNegociacaoEntity("Google e Outros Buscadores"));
            fonteNegociacaoRepository.save(new FonteNegociacaoEntity("Indicação por Clientes"));
            fonteNegociacaoRepository.save(new FonteNegociacaoEntity("Indicação por Parceiros"));
            fonteNegociacaoRepository.save(new FonteNegociacaoEntity("Prospecção Ativa"));
            fonteNegociacaoRepository.save(new FonteNegociacaoEntity("Redes Sociais"));
        };
    }

    @Bean
    CommandLineRunner initTableEstagio(EstagioNegociacaoRepository estagioNegociacaoRepository) {
        return args -> estagioNegociacaoRepository.saveAll(Arrays.asList(new EstagioNegociacaoEntity("Sem contato", "SC", 1), new EstagioNegociacaoEntity("Contrato Feito", "CF", 2), new EstagioNegociacaoEntity("Identificação de Interece", "IDI", 3),
                new EstagioNegociacaoEntity("Apresentação", "A", 4), new EstagioNegociacaoEntity("Proposta Enviada", "PE", 5)));
    }

    @Bean
    CommandLineRunner initTablePerda(MotivoPerdaRepository motivoPerdaRepository) {
        return args -> {
            motivoPerdaRepository.save(new MotivoPerdaEntity(null, "Cliente optou por não realizar o projeto", null));
            motivoPerdaRepository.save(new MotivoPerdaEntity(null, "Demora no follow", null));
            motivoPerdaRepository.save(new MotivoPerdaEntity(null, "Fechou com outra empresa", null));
            motivoPerdaRepository.save(new MotivoPerdaEntity(null, "Não gostou do produto/serviço", null));
            motivoPerdaRepository.save(new MotivoPerdaEntity(null, "Outros", null));
            motivoPerdaRepository.save(new MotivoPerdaEntity(null, "Preço", null));
        };
    }

    @Bean
    CommandLineRunner initTableSegmento(SegmentoService segmentoService) {
        return args -> {
            segmentoService.salvar(new SegmentoEntity("Advocacia"));
            segmentoService.salvar(new SegmentoEntity("Agropecuária"));
            segmentoService.salvar(new SegmentoEntity("Arquitetura"));
            segmentoService.salvar(new SegmentoEntity("Bancos"));
            segmentoService.salvar(new SegmentoEntity("Blogs e Sites Pessoais"));
            segmentoService.salvar(new SegmentoEntity("Clinicas / Saúde"));
            segmentoService.salvar(new SegmentoEntity("Concessionárias de Veículos"));
            segmentoService.salvar(new SegmentoEntity("Contabilidade"));
            segmentoService.salvar(new SegmentoEntity("Demolição e terraplanagem"));
            segmentoService.salvar(new SegmentoEntity("Distribuidoras / Atacadistas"));
            segmentoService.salvar(new SegmentoEntity("Engenharia"));
            segmentoService.salvar(new SegmentoEntity("Esoterismo"));
            segmentoService.salvar(new SegmentoEntity("Eventos e Entretenimento"));
            segmentoService.salvar(new SegmentoEntity("Fotografia"));
            segmentoService.salvar(new SegmentoEntity("Gráfica"));
            segmentoService.salvar(new SegmentoEntity("Indústria"));
            segmentoService.salvar(new SegmentoEntity("Instituições Religiosas"));
            segmentoService.salvar(new SegmentoEntity("Músicos e Bandas"));
            segmentoService.salvar(new SegmentoEntity("Pessoa física"));
            segmentoService.salvar(new SegmentoEntity("Pet Shop"));
            segmentoService.salvar(new SegmentoEntity("Poder público"));
            segmentoService.salvar(new SegmentoEntity("Publicidade"));
            segmentoService.salvar(new SegmentoEntity("Serviços"));
            segmentoService.salvar(new SegmentoEntity("Shoppings e Feiras Comerciais"));
            segmentoService.salvar(new SegmentoEntity("Sindicato Patronal"));
            segmentoService.salvar(new SegmentoEntity("Tecnologia"));
            segmentoService.salvar(new SegmentoEntity("Telefonia"));
            segmentoService.salvar(new SegmentoEntity("Terceiro Setor"));
            segmentoService.salvar(new SegmentoEntity("Turismo"));
        };
    }
}
