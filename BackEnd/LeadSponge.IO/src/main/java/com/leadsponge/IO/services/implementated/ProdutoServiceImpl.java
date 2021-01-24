package com.leadsponge.IO.services.implementated;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.models.produto.Produto;
import com.leadsponge.IO.repository.Filter.ProdutoFilter;
import com.leadsponge.IO.repository.produto.ProdutoRepository;
import com.leadsponge.IO.services.ProdutoService;

@Service
public class ProdutoServiceImpl extends ErroMessage implements ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Override
	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}

	@Override
	public void atualizarPropriedadeVisibilidade(Long id, Boolean visibilidade) {
		Produto produtoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
		produtoSalva.setVisibilidade(visibilidade);
		repository.save(produtoSalva);
	}

	@Override
	public Produto atualizar(Long id, Produto produto) {
		Produto produtoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
		BeanUtils.copyProperties(produto, produtoSalva, "id");
		return repository.save(produtoSalva);
	}

	@Override
	public Page<Produto> filtrar(ProdutoFilter produtoFilter, Pageable pageable) {
		return repository.filtrar(produtoFilter, pageable);
	}

	@Override
	public Produto deletar(Long id) {
		Produto produtoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
		repository.deleteById(id);
		return produtoSalvo;
	}

	@Override
	public Produto detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
	}
}
