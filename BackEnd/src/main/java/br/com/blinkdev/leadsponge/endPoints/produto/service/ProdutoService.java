package br.com.blinkdev.leadsponge.endPoints.produto.service;

import br.com.blinkdev.leadsponge.endPoints.produto.entity.ProdutoEntity;
import br.com.blinkdev.leadsponge.endPoints.produto.filter.ProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProdutoService {
	ProdutoEntity salvar(ProdutoEntity produto);

	void atualizarPropriedadeVisibilidade(Long id, Boolean visibilidade);

	ProdutoEntity atualizar(Long id, ProdutoEntity produto);

	ProdutoEntity deletar(Long id);

	ProdutoEntity detalhar(Long id);

	Page<ProdutoEntity> filtrar(ProdutoFilter negociacaoFilter, Pageable pageable);
}
