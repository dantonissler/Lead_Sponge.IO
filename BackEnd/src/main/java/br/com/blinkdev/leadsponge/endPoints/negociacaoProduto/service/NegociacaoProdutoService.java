package br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.service;

import br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.entity.NegociacaoProdutoEntity;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NegociacaoProdutoService {
	NegociacaoProdutoEntity salvar(NegociacaoProdutoEntity nProduto);

	NegociacaoProdutoEntity atualizar(Long id, NegociacaoProdutoEntity negociacaoProduto);

	NegociacaoProdutoEntity deletar(Long id);

	NegociacaoProdutoEntity detalhar(Long id);

	Page<NegociacaoProdutoEntity> filtrar(NegociacaoProdutoFilter negociacaoProdutoFilter, Pageable pageable);
}
