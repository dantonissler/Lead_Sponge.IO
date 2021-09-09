package br.com.blinkdev.leadsponge.endPoints.cliente.repository;

import br.com.blinkdev.leadsponge.endPoints.cliente.entity.ClienteEntity;
import br.com.blinkdev.leadsponge.endPoints.cliente.filter.ClienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositoryQuery {
	Page<ClienteEntity> filtrar(ClienteFilter lancamentoFilter, Pageable pageable);
}
