package br.com.blinkdev.leadsponge.services.implementated;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.models.produto.Produto;
import br.com.blinkdev.leadsponge.models.produto.ProdutoFilter;
import br.com.blinkdev.leadsponge.repository.produto.ProdutoRepository;
import br.com.blinkdev.leadsponge.services.ProdutoService;
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
