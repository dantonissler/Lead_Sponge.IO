package com.leadsponge.IO.models.historicoEstagioNegociacao;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.leadsponge.IO.models.negociacao.Negociacao;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HistEstagioNegociacao.class)
public abstract class HistEstagioNegociacao_ {
	public static volatile SingularAttribute<HistEstagioNegociacao, Long> id;
	public static volatile SingularAttribute<HistEstagioNegociacao, LocalDate> dataInicio;
	public static volatile SingularAttribute<HistEstagioNegociacao, LocalDate> dataMudanca;
	public static volatile SingularAttribute<HistEstagioNegociacao, LocalDate> dataVenda;
	public static volatile SingularAttribute<HistEstagioNegociacao, Negociacao> negociacaoHistEstagioNegociacao;
	public static final String ID = "id";
	public static final String DATA_INICIO = "dataInicio";
	public static final String DATA_MUDANCA = "dataMudanca";
	public static final String DATA_VENDA = "dataVenda";
	public static final String NEGOCIACAO = "negociacao";
}
