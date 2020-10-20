package com.leadsponge.IO.services;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.models.enumerate.TipoDesconto;
import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;
import com.leadsponge.IO.repository.NegociacaoProdutoRepository;
import com.leadsponge.IO.repository.negociacao.NegociacaoRepository;
import com.leadsponge.IO.repository.produto.ProdutoRepository;

@Service
public class NegociacaoProdutoService extends ErroMessage{

	@Autowired
	private NegociacaoProdutoRepository repository;
	
	@Autowired
	private ProdutoRepository produtoR;
	
	@Autowired
	private NegociacaoRepository negociacaoR;

	public NegociacaoProduto salvar(NegociacaoProduto nProduto) {
		produtoR.findById(nProduto.getProduto().getId()).orElseThrow(() -> notFouldId(nProduto.getProduto().getId(), "o produto"));
		negociacaoR.findById(nProduto.getNegociacao().getId()).orElseThrow(() -> notFouldId(nProduto.getNegociacao().getId(), "a negociação"));
		valorTotal(nProduto);
		return repository.save(nProduto);
	}

	public NegociacaoProduto atualizar(Long id, NegociacaoProduto negociacaoProduto) {
		NegociacaoProduto fonteNegociacaoProduto = buscarNegociacaoProdutoExistente(id);
		valorTotal(negociacaoProduto);
		BeanUtils.copyProperties(negociacaoProduto, fonteNegociacaoProduto, "id");
		return repository.save(fonteNegociacaoProduto);
	}

	private NegociacaoProduto buscarNegociacaoProdutoExistente(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "o produto"));
	}

	private NegociacaoProduto valorTotal(NegociacaoProduto negociacaoProduto) {
		try {
			BigDecimal valor = negociacaoProduto.getValor();
			Integer qtd = negociacaoProduto.getQuantidade();
			BigDecimal total = valor.multiply(BigDecimal.valueOf(qtd.longValue()));
			if (negociacaoProduto.getTipoDesconto() == TipoDesconto.PORCENTAGEM && negociacaoProduto.getTemDesconto()) {
				BigDecimal desconto = BigDecimal.valueOf(1)
						.subtract(negociacaoProduto.getDesconto().divide(BigDecimal.valueOf(100)));
				negociacaoProduto.setTotal(total.multiply(desconto));
			} else if (negociacaoProduto.getTipoDesconto() == TipoDesconto.VALOR
					&& negociacaoProduto.getTemDesconto()) {
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
}
