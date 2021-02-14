package com.leadsponge.IO.repository.usuario;

import com.leadsponge.IO.models.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {

    Optional<Usuario> findByUsername(String username);

    List<Usuario> findByRoles(String nomeRole);

    List<Usuario> findByRolesNome(String permissaoDescricao);
}
