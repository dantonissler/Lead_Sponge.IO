package br.com.blinkdev.leadsponge.endPoints.cliente.repository;

import br.com.blinkdev.leadsponge.endPoints.cliente.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>, ClienteRepositoryQuery{
}
