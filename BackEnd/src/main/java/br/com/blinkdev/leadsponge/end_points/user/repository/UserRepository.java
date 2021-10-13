package br.com.blinkdev.leadsponge.end_points.user.repository;

import br.com.blinkdev.leadsponge.end_points.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryQuery {

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findByRoles(String nomeRole);

    List<UserEntity> findByRolesName(String permissaoDescricao);
}
