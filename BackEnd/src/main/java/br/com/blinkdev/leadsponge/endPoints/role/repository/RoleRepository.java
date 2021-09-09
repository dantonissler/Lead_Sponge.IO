package br.com.blinkdev.leadsponge.endPoints.role.repository;

import br.com.blinkdev.leadsponge.endPoints.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>, RoleRepositoryQuery {
}
