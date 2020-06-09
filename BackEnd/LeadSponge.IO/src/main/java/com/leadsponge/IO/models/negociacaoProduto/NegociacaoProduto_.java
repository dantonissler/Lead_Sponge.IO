package com.leadsponge.IO.models.negociacaoProduto;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.produto.Produto;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NegociacaoProduto.class)
public abstract class NegociacaoProduto_ {

	public static volatile SingularAttribute<NegociacaoProduto, Long> id;
	public static volatile SingularAttribute<NegociacaoProduto, Integer> quantidadeProdutos;
	public static volatile SingularAttribute<NegociacaoProduto, Produto> produto;
	public static volatile SingularAttribute<NegociacaoProduto, Negociacao> negociacao;
	public static final String ID = "id";
	public static final String QUANTIDADE_PRODUTOS = "quantidadeProdutos";
	public static final String PRODUTO = "produto";
	public static final String NEGOCIACAO = "negociacao";
}
