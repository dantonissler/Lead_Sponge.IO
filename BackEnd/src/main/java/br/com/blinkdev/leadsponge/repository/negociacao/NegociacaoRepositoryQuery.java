package br.com.blinkdev.leadsponge.repository.negociacao;

import br.com.blinkdev.leadsponge.models.negociacao.Negociacao;
import br.com.blinkdev.leadsponge.models.negociacao.NegociacaoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NegociacaoRepositoryQuery {
	Page<Negociacao> filtrar(NegociacaoFilter negociacaoFilter, Pageable pageable);
}
