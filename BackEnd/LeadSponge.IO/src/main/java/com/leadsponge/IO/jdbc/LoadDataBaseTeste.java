package com.leadsponge.IO.jdbc;

import com.leadsponge.IO.models.role.Role;
import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.repository.role.RoleRepository;
import com.leadsponge.IO.services.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashSet;

@Profile("teste")
@Configuration
public class LoadDataBaseTeste {
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
}
