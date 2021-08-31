package br.com.blinkdev.leadsponge.services.negociacaoProduto;

import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProduto;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NegociacaoProdutoService {
	NegociacaoProduto salvar(NegociacaoProduto nProduto);

	NegociacaoProduto atualizar(Long id, NegociacaoProduto negociacaoProduto);

	NegociacaoProduto deletar(Long id);

	NegociacaoProduto detalhar(Long id);

	Page<NegociacaoProduto> filtrar(NegociacaoProdutoFilter negociacaoProdutoFilter, Pageable pageable);
}
