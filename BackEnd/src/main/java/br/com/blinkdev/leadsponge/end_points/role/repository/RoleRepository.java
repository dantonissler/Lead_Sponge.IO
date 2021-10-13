package br.com.blinkdev.leadsponge.end_points.role.repository;

import br.com.blinkdev.leadsponge.end_points.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>, RoleRepositoryQuery {
}
