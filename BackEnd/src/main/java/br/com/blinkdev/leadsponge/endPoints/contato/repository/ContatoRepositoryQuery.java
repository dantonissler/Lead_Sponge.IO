package br.com.blinkdev.leadsponge.endPoints.contato.repository;

import br.com.blinkdev.leadsponge.endPoints.contato.entity.ContatoEntity;
import br.com.blinkdev.leadsponge.endPoints.contato.filter.ContatoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepositoryQuery {
	Page<ContatoEntity> filtrar(ContatoFilter contatoFilter, Pageable pageable);
}
