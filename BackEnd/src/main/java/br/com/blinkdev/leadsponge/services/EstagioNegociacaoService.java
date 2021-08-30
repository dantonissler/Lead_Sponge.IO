package br.com.blinkdev.leadsponge.services;

import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacao;
import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacaoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface EstagioNegociacaoService {
	EstagioNegociacao salvar(EstagioNegociacao estagioNegociacao);

	EstagioNegociacao atualizar(Long id, EstagioNegociacao estagioNegociacao);

	EstagioNegociacao deletar(Long id);

	EstagioNegociacao detalhar(Long id);

	Page<EstagioNegociacao> filtrar(EstagioNegociacaoFilter estagioNegociacaoFilter, Pageable pageable);
}
