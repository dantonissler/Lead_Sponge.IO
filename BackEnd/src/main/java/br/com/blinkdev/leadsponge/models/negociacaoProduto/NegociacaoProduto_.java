package br.com.blinkdev.leadsponge.models.negociacaoProduto;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.blinkdev.leadsponge.models.negociacao.Negociacao;
import br.com.blinkdev.leadsponge.models.negociacao.TipoReincidencia;
import br.com.blinkdev.leadsponge.models.produto.Produto;

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
}
