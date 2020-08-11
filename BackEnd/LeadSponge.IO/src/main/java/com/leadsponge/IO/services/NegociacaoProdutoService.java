package com.leadsponge.IO.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;
import com.leadsponge.IO.models.negociacaoProduto.TipoDesconto;
import com.leadsponge.IO.repository.NegociacaoProdutoRepository;

@Service
public class NegociacaoProdutoService {

	@Autowired
	private NegociacaoProdutoRepository repository;

	public NegociacaoProduto salvar(NegociacaoProduto nProduto) {
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
		Optional<NegociacaoProduto> negociacaoProdutoSalvo = repository.findById(id);
		if (!negociacaoProdutoSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return negociacaoProdutoSalvo.get();
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
			throw new IllegalArgumentException();
		}
		return negociacaoProduto;
	}

}
