package br.com.blinkdev.leadsponge.relationship.historyNegotiationStyle.entity;

import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HistoryNegotiationStyle.class)
public abstract class HistoryNegotiationStyle_ {
    public static volatile SingularAttribute<HistoryNegotiationStyle, Long> id;
    public static volatile SingularAttribute<HistoryNegotiationStyle, String> apelido;
    public static volatile SingularAttribute<HistoryNegotiationStyle, String> idEstagio;
    public static volatile SingularAttribute<HistoryNegotiationStyle, LocalDate> dataInicio;
    public static volatile SingularAttribute<HistoryNegotiationStyle, LocalDate> dataMudanca;
    public static volatile SingularAttribute<HistoryNegotiationStyle, NegotiationEntity> negociacao;
}
