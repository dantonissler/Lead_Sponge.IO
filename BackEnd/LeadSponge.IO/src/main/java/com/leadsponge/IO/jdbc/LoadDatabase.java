package com.leadsponge.IO.jdbc;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao;
import com.leadsponge.IO.models.motivoPerda.MotivoPerda;
import com.leadsponge.IO.models.role.Role;
import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.repository.campanha.CampanhaRepository;
import com.leadsponge.IO.repository.estagioNegociacao.EstagioNegociacaoRepository;
import com.leadsponge.IO.repository.fonteNegociacao.FonteNegociacaoRepository;
import com.leadsponge.IO.repository.motivoPerda.MotivoPerdaRepository;
import com.leadsponge.IO.repository.role.RoleRepository;
import com.leadsponge.IO.services.SegmentoService;
import com.leadsponge.IO.services.UsuarioService;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
class LoadDatabase {
    @Bean
    CommandLineRunner initTableRole(RoleRepository repository) {
        return args -> {
            repository.save(new Role("CADASTRAR_USUARIO"));
            repository.save(new Role("REMOVER_USUARIO"));
            repository.save(new Role("PESQUISAR_USUARIO"));

            repository.save(new Role("CADASTRAR_CLIENTE"));
            repository.save(new Role("REMOVER_CLIENTE"));
            repository.save(new Role("PESQUISAR_CLIENTE"));

            repository.save(new Role("CADASTRAR_NEGOCIACAO"));
            repository.save(new Role("REMOVER_NEGOCIACAO"));
            repository.save(new Role("PESQUISAR_NEGOCIACAO"));

            repository.save(new Role("CADASTRAR_CAMPANHA"));
            repository.save(new Role("REMOVER_CAMPANHA"));
            repository.save(new Role("PESQUISAR_CAMPANHA"));

            repository.save(new Role("CADASTRAR_CONTATO"));
            repository.save(new Role("REMOVER_CONTATO"));
            repository.save(new Role("PESQUISAR_CONTATO"));

            repository.save(new Role("CADASTRAR_FONTE"));
            repository.save(new Role("REMOVER_FONTE"));
            repository.save(new Role("PESQUISAR_FONTE"));

            repository.save(new Role("CADASTRAR_PRODUTO"));
            repository.save(new Role("REMOVER_PRODUTO"));
            repository.save(new Role("PESQUISAR_PRODUTO"));

            repository.save(new Role("CADASTRAR_TAREFA"));
            repository.save(new Role("REMOVER_TAREFA"));
            repository.save(new Role("PESQUISAR_TAREFA"));

            repository.save(new Role("CADASTRAR_ESTAGIO"));
            repository.save(new Role("REMOVER_ESTAGIO"));
            repository.save(new Role("PESQUISAR_ESTAGIO"));

            repository.save(new Role("CADASTRAR_SEGMENTO"));
            repository.save(new Role("REMOVER_SEGMENTO"));
            repository.save(new Role("PESQUISAR_SEGMENTO"));

            repository.save(new Role("CADASTRAR_MOTIVO"));
            repository.save(new Role("REMOVER_MOTIVO"));
            repository.save(new Role("PESQUISAR_MOTIVO"));

            repository.save(new Role("USER"));
        };
    }

    @Bean
    CommandLineRunner initTableUsuario(UsuarioService service, RoleRepository roleRepository) {
        return args -> {
            service.salvar(new Usuario("admin", "Danton Issler Rodrigues", "danton.issler18@gmail.com", "123321", "123321", true, "98ad18d9-d419-4099-864d-6a9317e6ec29_1.jpg", "/assets/img/perfil/98ad18d9-d419-4099-864d-6a9317e6ec29_1.jpg",
                    new HashSet<>(roleRepository.findAll())));
            service.salvar(new Usuario("user", "user", "user@gmail.com", "123321", "123321", true, null, null, new HashSet<>()));

        };
    }

    @Bean
    CommandLineRunner initTableCampanha(CampanhaRepository repository) {
        return args -> repository.save(new Campanha(null, "Sem Campanha", ""));
    }

    @Bean
    CommandLineRunner initTableFonteNegociacao(FonteNegociacaoRepository repository) {
        return args -> {
            repository.save(new FonteNegociacao("Cliente Ativo"));
            repository.save(new FonteNegociacao("Contato pelo Site"));
            repository.save(new FonteNegociacao("Contato por E-mail"));
            repository.save(new FonteNegociacao("Contato por Telefone"));
            repository.save(new FonteNegociacao("E-mail Marketing"));
            repository.save(new FonteNegociacao("Feiras e Eventos"));
            repository.save(new FonteNegociacao("Google e Outros Buscadores"));
            repository.save(new FonteNegociacao("Indicação por Clientes"));
            repository.save(new FonteNegociacao("Indicação por Parceiros"));
            repository.save(new FonteNegociacao("Prospecção Ativa"));
            repository.save(new FonteNegociacao("Redes Sociais"));
        };
    }

    @Bean
    CommandLineRunner initTableEstagio(EstagioNegociacaoRepository repository) {
        return args -> repository.saveAll(Arrays.asList(new EstagioNegociacao("Sem contato", "SC", 1), new EstagioNegociacao("Contrato Feito", "CF", 2), new EstagioNegociacao("Identificação de Interece", "IDI", 3),
                new EstagioNegociacao("Apresentação", "A", 4), new EstagioNegociacao("Proposta Enviada", "PE", 5)));
    }

    @Bean
    CommandLineRunner initTablePerda(MotivoPerdaRepository repository) {
        return args -> {
            repository.save(new MotivoPerda(null,"Cliente optou por não realizar o projeto",null));
            repository.save(new MotivoPerda(null,"Demora no follow",null));
            repository.save(new MotivoPerda(null,"Fechou com outra empresa",null));
            repository.save(new MotivoPerda(null,"Não gostou do produto/serviço",null));
            repository.save(new MotivoPerda(null,"Outros",null));
            repository.save(new MotivoPerda(null,"Preço",null));
        };
    }

    @Bean
    CommandLineRunner initTableSegmento(SegmentoService repository) {
        return args -> {
            repository.salvar(new Segmento("Advocacia"));
            repository.salvar(new Segmento("Agropecuária"));
            repository.salvar(new Segmento("Arquitetura"));
            repository.salvar(new Segmento("Bancos"));
            repository.salvar(new Segmento("Blogs e Sites Pessoais"));
            repository.salvar(new Segmento("Clinicas / Saúde"));
            repository.salvar(new Segmento("Concessionárias de Veículos"));
            repository.salvar(new Segmento("Contabilidade"));
            repository.salvar(new Segmento("Demolição e terraplanagem"));
            repository.salvar(new Segmento("Distribuidoras / Atacadistas"));
            repository.salvar(new Segmento("Engenharia"));
            repository.salvar(new Segmento("Esoterismo"));
            repository.salvar(new Segmento("Eventos e Entretenimento"));
            repository.salvar(new Segmento("Fotografia"));
            repository.salvar(new Segmento("Gráfica"));
            repository.salvar(new Segmento("Indústria"));
            repository.salvar(new Segmento("Instituições Religiosas"));
            repository.salvar(new Segmento("Músicos e Bandas"));
            repository.salvar(new Segmento("Pessoa física"));
            repository.salvar(new Segmento("Pet Shop"));
            repository.salvar(new Segmento("Poder público"));
            repository.salvar(new Segmento("Publicidade"));
            repository.salvar(new Segmento("Serviços"));
            repository.salvar(new Segmento("Shoppings e Feiras Comerciais"));
            repository.salvar(new Segmento("Sindicato Patronal"));
            repository.salvar(new Segmento("Tecnologia"));
            repository.salvar(new Segmento("Telefonia"));
            repository.salvar(new Segmento("Terceiro Setor"));
            repository.salvar(new Segmento("Turismo"));
        };
    }
}
