package br.com.blinkdev.leadsponge.repository.telefone;

import br.com.blinkdev.leadsponge.models.telefone.Telefone;
import br.com.blinkdev.leadsponge.repository.Filter.TelefoneFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepositoryQuery {
	Page<Telefone> filtrar(TelefoneFilter telefoneFilter, Pageable pageable);
}
