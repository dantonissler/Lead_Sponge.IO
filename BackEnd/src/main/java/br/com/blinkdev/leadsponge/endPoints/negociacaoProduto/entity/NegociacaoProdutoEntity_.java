package br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.entity;

import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.negociacao.enumeration.TipoReincidencia;
import br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.enumeration.TipoDesconto;
import br.com.blinkdev.leadsponge.endPoints.produto.entity.ProdutoEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NegociacaoProdutoEntity.class)
public abstract class NegociacaoProdutoEntity_ {

	public static volatile SingularAttribute<NegociacaoProdutoEntity, Long> id;
	public static volatile SingularAttribute<NegociacaoProdutoEntity, Integer> quantidade;
	public static volatile SingularAttribute<NegociacaoProdutoEntity, TipoReincidencia> reincidencia;
	public static volatile SingularAttribute<NegociacaoProdutoEntity, BigDecimal> total;
	public static volatile SingularAttribute<NegociacaoProdutoEntity, TipoDesconto> tipoDesconto;
	public static volatile SingularAttribute<NegociacaoProdutoEntity, Boolean> temDesconto;
	public static volatile SingularAttribute<NegociacaoProdutoEntity, BigDecimal> valor;
	public static volatile SingularAttribute<NegociacaoProdutoEntity, BigDecimal> desconto;
	public static volatile SingularAttribute<NegociacaoProdutoEntity, ProdutoEntity> produto;
	public static volatile SingularAttribute<NegociacaoProdutoEntity, NegociacaoEntity> negociacao;
}
