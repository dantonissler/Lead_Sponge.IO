package com.leadsponge.IO.models.negociacaoProduto;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.negociacao.TipoReincidencia;
import com.leadsponge.IO.models.produto.Produto;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NegociacaoProduto.class)
public abstract class NegociacaoProduto_ {

	public static volatile SingularAttribute<NegociacaoProduto, Long> id;
	public static volatile SingularAttribute<NegociacaoProduto, Integer> quantidade;
	public static volatile SingularAttribute<NegociacaoProduto, TipoReincidencia> reincidencia;
	public static volatile SingularAttribute<NegociacaoProduto, BigDecimal> total;
	public static volatile SingularAttribute<NegociacaoProduto, TipoDesconto> tipoDesconto;
	public static volatile SingularAttribute<NegociacaoProduto, Boolean> temDesconto;
	public static volatile SingularAttribute<NegociacaoProduto, BigDecimal> valor;
	public static volatile SingularAttribute<NegociacaoProduto, BigDecimal> desconto;
	public static volatile SingularAttribute<NegociacaoProduto, Produto> produto;
	public static volatile SingularAttribute<NegociacaoProduto, Negociacao> negociacao;
	public static final String ID = "id";
	public static final String QUANTIDADE_PRODUTOS = "quantidadeProdutos";
	public static final String PRODUTO = "produto";
	public static final String NEGOCIACAO = "negociacao";
}
