package br.com.blinkdev.leadsponge.utils;

import br.com.blinkdev.leadsponge.endpoints.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.endpoints.role.repository.RoleRepository;
import br.com.blinkdev.leadsponge.endpoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.endpoints.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashSet;

@Profile("dev-testes")
@Configuration
class DummyDevTestesData {
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
}
