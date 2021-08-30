package br.com.blinkdev.leadsponge.repository.fonteNegociacao;

import br.com.blinkdev.leadsponge.models.fonteNegociacao.FonteNegociacao;
import br.com.blinkdev.leadsponge.models.fonteNegociacao.FonteNegociacaoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FonteNegociacaoRepositoryQuery {
	Page<FonteNegociacao> filtrar(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable);

}
