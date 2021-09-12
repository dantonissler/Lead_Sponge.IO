package br.com.blinkdev.leadsponge.endPoints.negotiationProduct.entity;

import br.com.blinkdev.leadsponge.endPoints.Product.entity.ProductEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiation.enumeration.RecidivismType;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.enumeration.DiscountType;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NegotiationProductEntity.class)
public abstract class NegotiationProductEntity_ {

    public static volatile SingularAttribute<NegotiationProductEntity, Long> id;
    public static volatile SingularAttribute<NegotiationProductEntity, Integer> quantidade;
    public static volatile SingularAttribute<NegotiationProductEntity, RecidivismType> reincidencia;
    public static volatile SingularAttribute<NegotiationProductEntity, BigDecimal> total;
    public static volatile SingularAttribute<NegotiationProductEntity, DiscountType> tipoDesconto;
    public static volatile SingularAttribute<NegotiationProductEntity, Boolean> temDesconto;
    public static volatile SingularAttribute<NegotiationProductEntity, BigDecimal> valor;
    public static volatile SingularAttribute<NegotiationProductEntity, BigDecimal> desconto;
    public static volatile SingularAttribute<NegotiationProductEntity, ProductEntity> produto;
    public static volatile SingularAttribute<NegotiationProductEntity, NegotiationEntity> negociacao;
}
