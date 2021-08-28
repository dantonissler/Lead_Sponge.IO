package br.com.blinkdev.leadsponge.repository.contato;

import br.com.blinkdev.leadsponge.models.contato.Contato;
import br.com.blinkdev.leadsponge.repository.Filter.ContatoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepositoryQuery {
	Page<Contato> filtrar(ContatoFilter contatoFilter, Pageable pageable);
}
