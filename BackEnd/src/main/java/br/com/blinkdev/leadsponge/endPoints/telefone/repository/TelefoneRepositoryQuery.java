package br.com.blinkdev.leadsponge.endPoints.telefone.repository;

import br.com.blinkdev.leadsponge.endPoints.telefone.entity.Telefone;
import br.com.blinkdev.leadsponge.endPoints.telefone.filter.TelefoneFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepositoryQuery {
	Page<Telefone> filtrar(TelefoneFilter telefoneFilter, Pageable pageable);
}
