package br.com.blinkdev.leadsponge.endPoints.user.repository;

import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UserEntity, Long>, UsuarioRepositoryQuery {

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findByRoles(String nomeRole);

    List<UserEntity> findByRolesNome(String permissaoDescricao);
}
