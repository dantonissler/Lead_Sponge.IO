package br.com.blinkdev.leadsponge.endPoints.contato.repository;

import br.com.blinkdev.leadsponge.endPoints.cliente.entity.ClienteEntity;
import br.com.blinkdev.leadsponge.endPoints.contato.entity.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long>, ContatoRepositoryQuery {
	Iterable<ContatoEntity> findByClienteContato(ClienteEntity cliente);
}
