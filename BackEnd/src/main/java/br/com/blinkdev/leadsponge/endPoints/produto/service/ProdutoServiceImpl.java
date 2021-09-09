package br.com.blinkdev.leadsponge.endPoints.produto.service;


import br.com.blinkdev.leadsponge.endPoints.produto.entity.ProdutoEntity;
import br.com.blinkdev.leadsponge.endPoints.produto.filter.ProdutoFilter;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.endPoints.produto.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl extends ErroMessage implements ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Override
	public ProdutoEntity salvar(ProdutoEntity produto) {
		return repository.save(produto);
	}

	@Override
	public void atualizarPropriedadeVisibilidade(Long id, Boolean visibilidade) {
		ProdutoEntity produtoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
		produtoSalva.setVisibilidade(visibilidade);
		repository.save(produtoSalva);
	}

	@Override
	public ProdutoEntity atualizar(Long id, ProdutoEntity produto) {
		ProdutoEntity produtoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
		BeanUtils.copyProperties(produto, produtoSalva, "id");
		return repository.save(produtoSalva);
	}

	@Override
	public Page<ProdutoEntity> filtrar(ProdutoFilter produtoFilter, Pageable pageable) {
		return repository.filtrar(produtoFilter, pageable);
	}

	@Override
	public ProdutoEntity deletar(Long id) {
		ProdutoEntity produtoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
		repository.deleteById(id);
		return produtoSalvo;
	}

	@Override
	public ProdutoEntity detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
	}
}
