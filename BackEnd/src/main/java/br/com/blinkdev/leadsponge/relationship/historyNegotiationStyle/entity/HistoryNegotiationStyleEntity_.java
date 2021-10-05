package br.com.blinkdev.leadsponge.relationship.historyNegotiationStyle.entity;

import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.entity.NegotiationStyleEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HistoryNegotiationStyleEntity.class)
public abstract class HistoryNegotiationStyleEntity_ {
    public static volatile SingularAttribute<HistoryNegotiationStyleEntity, Long> id;
    public static volatile SingularAttribute<HistoryNegotiationStyleEntity, String> apelido;
    public static volatile SingularAttribute<HistoryNegotiationStyleEntity, String> idEstagio;
    public static volatile SingularAttribute<HistoryNegotiationStyleEntity, LocalDate> dataInicio;
    public static volatile SingularAttribute<HistoryNegotiationStyleEntity, LocalDate> dataMudanca;
    public static volatile SingularAttribute<HistoryNegotiationStyleEntity, NegotiationEntity> negotiation;
    public static volatile SingularAttribute<HistoryNegotiationStyleEntity, NegotiationStyleEntity> negotiationStyle;
}
