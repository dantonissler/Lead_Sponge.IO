package br.com.blinkdev.leadsponge.services.negociacaoProduto;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProduto;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProdutoFilter;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.TipoDesconto;
import br.com.blinkdev.leadsponge.repository.negociacao.NegociacaoRepository;
import br.com.blinkdev.leadsponge.repository.negociacaoProduto.NegociacaoProdutoRepository;
import br.com.blinkdev.leadsponge.repository.produto.ProdutoRepository;
import br.com.blinkdev.leadsponge.services.negociacao.NegociacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NegociacaoProdutoServiceImpl extends ErroMessage implements NegociacaoProdutoService {

	@Autowired
	private NegociacaoProdutoRepository repository;

	@Autowired
	private ProdutoRepository produtoR;

	@Autowired
	private NegociacaoRepository negociacaoR;

	@Autowired
	private NegociacaoService nService;

	@Override
	public NegociacaoProduto salvar(NegociacaoProduto nProduto) {
		produtoR.findById(nProduto.getProduto().getId()).orElseThrow(() -> notFouldId(nProduto.getProduto().getId(), "o produto"));
		negociacaoR.findById(nProduto.getNegociacao().getId()).orElseThrow(() -> notFouldId(nProduto.getNegociacao().getId(), "a negociação do produto"));
		valorTotal(nProduto);
		nService.calculo(nProduto.getNegociacao().getId());
		return repository.save(nProduto);
	}

	@Override
	public NegociacaoProduto atualizar(Long id, NegociacaoProduto negociacaoProduto) {
		NegociacaoProduto fonteNegociacaoProduto = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação do produto"));
		valorTotal(negociacaoProduto);
		BeanUtils.copyProperties(negociacaoProduto, fonteNegociacaoProduto, "id");
		nService.calculo(negociacaoProduto.getNegociacao().getId());
		return repository.save(fonteNegociacaoProduto);
	}

	private NegociacaoProduto valorTotal(NegociacaoProduto negociacaoProduto) {
		try {
			BigDecimal valor = negociacaoProduto.getValor();
			Integer qtd = negociacaoProduto.getQuantidade();
			BigDecimal total = valor.multiply(BigDecimal.valueOf(qtd.longValue()));
			if (negociacaoProduto.getTipoDesconto() == TipoDesconto.PORCENTAGEM && negociacaoProduto.getTemDesconto()) {
				BigDecimal desconto = BigDecimal.valueOf(1).subtract(negociacaoProduto.getDesconto().divide(BigDecimal.valueOf(100)));
				negociacaoProduto.setTotal(total.multiply(desconto));
			} else if (negociacaoProduto.getTipoDesconto() == TipoDesconto.VALOR && negociacaoProduto.getTemDesconto()) {
				negociacaoProduto.setTotal(total.subtract(negociacaoProduto.getDesconto()));
			} else if (!negociacaoProduto.getTemDesconto()) {
				negociacaoProduto.setDesconto(null);
				negociacaoProduto.setTipoDesconto(null);
				negociacaoProduto.setTotal(total);
			}
		} catch (Exception e) {
			throw notFould("o produto");
		}
		return negociacaoProduto;
	}

	@Override
	public NegociacaoProduto deletar(Long id) {
		NegociacaoProduto megociacaoProduto = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação do produto"));
		repository.deleteById(id);
		return megociacaoProduto;
	}

	@Override
	public NegociacaoProduto detalhar(Long id) {
		// TODO fazer as devidas validações
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação do produto"));
	}

	@Override
	public Page<NegociacaoProduto> filtrar(NegociacaoProdutoFilter negociacaoProdutoFilter, Pageable pageable) {
		return repository.filtrar(negociacaoProdutoFilter, pageable);
	}
}
