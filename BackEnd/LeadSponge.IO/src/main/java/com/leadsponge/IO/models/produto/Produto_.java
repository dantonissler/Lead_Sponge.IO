package com.leadsponge.IO.models.produto;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {
	public static volatile SingularAttribute<Produto, Long> id;
	public static volatile SingularAttribute<Produto, String> nome;
	public static volatile SingularAttribute<Produto, String> descricao;
	public static volatile SingularAttribute<Produto, BigDecimal> valor;
	public static volatile SingularAttribute<Produto, NegociacaoProduto> negociacaoProduto;
	public static volatile SingularAttribute<Produto, Boolean> visibilidade;
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String DESCRICAO = "descricao";
	public static final String VALOR = "valor";
	public static final String NEGOCIACAO_PRODUTO = "negociacaoProduto";
}
