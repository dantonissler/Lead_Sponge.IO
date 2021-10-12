package br.com.blinkdev.leadsponge.endpoints.role.repository;

import br.com.blinkdev.leadsponge.endpoints.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>, RoleRepositoryQuery {
}
