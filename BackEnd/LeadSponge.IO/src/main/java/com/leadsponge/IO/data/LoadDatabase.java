package com.leadsponge.IO.data;

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
import com.leadsponge.IO.repository.RoleRepository;
import com.leadsponge.IO.services.CampanhaService;
import com.leadsponge.IO.services.EstagioNegociacaoService;
import com.leadsponge.IO.services.FonteNegociacaoService;
import com.leadsponge.IO.services.MotivoPerdaService;
import com.leadsponge.IO.services.SegmentoService;
import com.leadsponge.IO.services.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
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
	CommandLineRunner initTableUsuario(UsuarioService repository, RoleRepository roleRepository) {
		return args -> {
			repository.save(new Usuario("danton", "Danton Issler Rodrigues", "danton@danton.com", "214255", "214255",
					true, new HashSet<>(roleRepository.findAll()),"165eb90c-568b-4a0b-9804-44bf30412dc2_ninguem.png"));
		};
	}

	@Bean
	CommandLineRunner initTableCampanha(CampanhaService repository) {
		return args -> {
			repository.save(new Campanha("Sem Campanha", ""));
		};
	}

	@Bean
	CommandLineRunner initTableFonteNegociacao(FonteNegociacaoService repository) {
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
	CommandLineRunner initTableEstagio(EstagioNegociacaoService repository) {
		return args -> {
			repository.save(new EstagioNegociacao("Sem contato", "SC", 1));
			repository.save(new EstagioNegociacao("Contrato Feito", "CF", 2));
			repository.save(new EstagioNegociacao("Identificação de Interece", "IDI", 3));
			repository.save(new EstagioNegociacao("Apresentação", "A", 4));
			repository.save(new EstagioNegociacao("Proposta Enviada", "PE", 5));
		};
	}

	@Bean
	CommandLineRunner initTablePerda(MotivoPerdaService repository) {
		return args -> {
			repository.save(new MotivoPerda("Cliente optou por não realizar o projeto"));
			repository.save(new MotivoPerda("Demora no follow"));
			repository.save(new MotivoPerda("Fechou com outra empresa"));
			repository.save(new MotivoPerda("Não gostou do produto/serviço"));
			repository.save(new MotivoPerda("Outros"));
			repository.save(new MotivoPerda("Preço"));

		};
	}

	@Bean
	CommandLineRunner initTableSegmento(SegmentoService repository) {
		return args -> {
			repository.save(new Segmento("Advocacia"));
			repository.save(new Segmento("Agropecuária"));
			repository.save(new Segmento("Arquitetura"));
			repository.save(new Segmento("Bancos"));
			repository.save(new Segmento("Blogs e Sites Pessoais"));
			repository.save(new Segmento("Clinicas / Saúde"));
			repository.save(new Segmento("Concessionárias de Veículos"));
			repository.save(new Segmento("Contabilidade"));
			repository.save(new Segmento("Demolição e terraplanagem"));
			repository.save(new Segmento("Distribuidoras / Atacadistas"));
			repository.save(new Segmento("Engenharia"));
			repository.save(new Segmento("Esoterismo"));
			repository.save(new Segmento("Eventos e Entretenimento"));
			repository.save(new Segmento("Fotografia"));
			repository.save(new Segmento("Gráfica"));
			repository.save(new Segmento("Indústria"));
			repository.save(new Segmento("Instituições Religiosas"));
			repository.save(new Segmento("Músicos e Bandas"));
			repository.save(new Segmento("Pessoa física"));
			repository.save(new Segmento("Pet Shop"));
			repository.save(new Segmento("Poder público"));
			repository.save(new Segmento("Publicidade"));
			repository.save(new Segmento("Serviços"));
			repository.save(new Segmento("Shoppings e Feiras Comerciais"));
			repository.save(new Segmento("Sindicato Patronal"));
			repository.save(new Segmento("Tecnologia"));
			repository.save(new Segmento("Telefonia"));
			repository.save(new Segmento("Terceiro Setor"));
			repository.save(new Segmento("Turismo"));
		};
	}
}
