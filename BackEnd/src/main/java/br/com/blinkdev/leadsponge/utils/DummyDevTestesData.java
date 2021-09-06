package br.com.blinkdev.leadsponge.utils;

import br.com.blinkdev.leadsponge.models.role.Role;
import br.com.blinkdev.leadsponge.models.usuario.Usuario;
import br.com.blinkdev.leadsponge.repository.role.RoleRepository;
import br.com.blinkdev.leadsponge.services.usuario.UsuarioService;
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
}
