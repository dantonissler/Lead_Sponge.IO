package com.leadsponge.IO.repository.telefone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.leadsponge.IO.models.telefone.Telefone;
import com.leadsponge.IO.repository.Filter.TelefoneFilter;

@Repository
public interface TelefoneRepositoryQuery {
	public Page<Telefone> filtrar(TelefoneFilter telefoneFilter, Pageable pageable);
}
