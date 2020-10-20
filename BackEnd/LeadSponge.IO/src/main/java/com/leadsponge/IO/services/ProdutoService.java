package com.leadsponge.IO.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.exception.UsuarioInativaException;
import com.leadsponge.IO.models.produto.Produto;
import com.leadsponge.IO.repository.produto.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto save(Produto produto) {
		produtoValidar(produto);
		return produtoRepository.save(produto);
	}
	public void atualizarPropriedadeVisibilidade(Long id, Boolean visibilidade) {
		Produto produtoSalva = buscarProdutoExistente(id);
		produtoSalva.setVisibilidade(visibilidade);
		produtoRepository.save(produtoSalva);
	}

	public Produto atualizar(Long id, Produto produto) {
		Produto fonteProduto = buscarProdutoExistente(id);
		BeanUtils.copyProperties(produto, fonteProduto, "id");
		return produtoRepository.save(fonteProduto);
	}

	private Produto buscarProdutoExistente(Long id) {
		Optional<Produto> produtoSalvo = produtoRepository.findById(id);
		if (!produtoSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return produtoSalvo.get();
	}

	private void produtoValidar(Produto produto) {
		if (produto == null) {
			throw new UsuarioInativaException();
		}
	}
}
