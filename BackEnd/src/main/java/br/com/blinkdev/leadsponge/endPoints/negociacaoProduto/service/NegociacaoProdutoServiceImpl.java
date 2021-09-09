package br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.service;

import br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.entity.NegociacaoProdutoEntity;
import br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.enumeration.TipoDesconto;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProdutoFilter;
import br.com.blinkdev.leadsponge.endPoints.negociacao.repository.NegociacaoRepository;
import br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.repository.NegociacaoProdutoRepository;
import br.com.blinkdev.leadsponge.endPoints.produto.repository.ProdutoRepository;
import br.com.blinkdev.leadsponge.endPoints.negociacao.service.NegociacaoService;
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
	private NegociacaoService negociacaoService;

	@Override
	public NegociacaoProdutoEntity salvar(NegociacaoProdutoEntity nProduto) {
		produtoR.findById(nProduto.getProduto().getId()).orElseThrow(() -> notFouldId(nProduto.getProduto().getId(), "o produto"));
		negociacaoR.findById(nProduto.getNegociacao().getId()).orElseThrow(() -> notFouldId(nProduto.getNegociacao().getId(), "a negociação do produto"));
		valorTotal(nProduto);
		negociacaoService.calculo(nProduto.getNegociacao().getId());
		return repository.save(nProduto);
	}

	@Override
	public NegociacaoProdutoEntity atualizar(Long id, NegociacaoProdutoEntity negociacaoProduto) {
		NegociacaoProdutoEntity fonteNegociacaoProduto = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação do produto"));
		valorTotal(negociacaoProduto);
		BeanUtils.copyProperties(negociacaoProduto, fonteNegociacaoProduto, "id");
		negociacaoService.calculo(negociacaoProduto.getNegociacao().getId());
		return repository.save(fonteNegociacaoProduto);
	}

	private NegociacaoProdutoEntity valorTotal(NegociacaoProdutoEntity negociacaoProduto) {
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
	public NegociacaoProdutoEntity deletar(Long id) {
		NegociacaoProdutoEntity megociacaoProduto = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação do produto"));
		repository.deleteById(id);
		return megociacaoProduto;
	}

	@Override
	public NegociacaoProdutoEntity detalhar(Long id) {
		// TODO fazer as devidas validações
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação do produto"));
	}

	@Override
	public Page<NegociacaoProdutoEntity> filtrar(NegociacaoProdutoFilter negociacaoProdutoFilter, Pageable pageable) {
		return repository.filtrar(negociacaoProdutoFilter, pageable);
	}
}
