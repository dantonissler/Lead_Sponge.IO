package br.com.blinkdev.leadsponge.endPoints.historicoEstagioNegociacao.entity;

import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HistEstagioNegociacao.class)
public abstract class HistEstagioNegociacao_ {
	public static volatile SingularAttribute<HistEstagioNegociacao, Long> id;
	public static volatile SingularAttribute<HistEstagioNegociacao, String> apelido;
	public static volatile SingularAttribute<HistEstagioNegociacao, String> idEstagio;
	public static volatile SingularAttribute<HistEstagioNegociacao, LocalDate> dataInicio;
	public static volatile SingularAttribute<HistEstagioNegociacao, LocalDate> dataMudanca;
	public static volatile SingularAttribute<HistEstagioNegociacao, NegociacaoEntity> negociacao;
}
