package com.leadsponge.IO.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.leadsponge.IO.models.Role;
import com.leadsponge.IO.repository.RoleRepository;

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
			
			repository.save(new Role("CADASTRAR_OPORTUNIDADE"));
			repository.save(new Role("REMOVER_OPORTUNIDADE"));
			repository.save(new Role("PESQUISAR_OPORTUNIDADE"));
			
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
			
			repository.save(new Role("ROLE_USER"));
		};
	}

//	@Bean
//	CommandLineRunner initTableUsuario(UsuarioRepository repository) {
//		return args -> {
//			repository.save(new Usuario("danton", "Danton Issler Rodrigues","danton@danton.com", "123", true));
//		};
//	}
}
