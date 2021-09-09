package br.com.blinkdev.leadsponge.endPoints.produto.entity;

import br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.entity.NegociacaoProdutoEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProdutoEntity.class)
public abstract class ProdutoEntity_ {
	public static volatile SingularAttribute<ProdutoEntity, Long> id;
	public static volatile SingularAttribute<ProdutoEntity, String> nome;
	public static volatile SingularAttribute<ProdutoEntity, String> descricao;
	public static volatile SingularAttribute<ProdutoEntity, BigDecimal> valor;
	public static volatile ListAttribute<ProdutoEntity, NegociacaoProdutoEntity> negociacaoProdutos;
	public static volatile SingularAttribute<ProdutoEntity, Boolean> visibilidade;
}
