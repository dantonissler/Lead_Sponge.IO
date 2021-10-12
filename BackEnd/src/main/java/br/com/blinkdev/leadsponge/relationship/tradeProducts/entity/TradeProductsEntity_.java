package br.com.blinkdev.leadsponge.relationship.tradeProducts.entity;

import br.com.blinkdev.leadsponge.endpoints.Product.entity.ProductEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiation.enumeration.KindRecidivism;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.enumeration.KindDiscount;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TradeProductsEntity.class)
public abstract class TradeProductsEntity_ {

    public static volatile SingularAttribute<TradeProductsEntity, Long> id;
    public static volatile SingularAttribute<TradeProductsEntity, Integer> quantidade;
    public static volatile SingularAttribute<TradeProductsEntity, KindRecidivism> reincidencia;
    public static volatile SingularAttribute<TradeProductsEntity, BigDecimal> total;
    public static volatile SingularAttribute<TradeProductsEntity, KindDiscount> tipoDesconto;
    public static volatile SingularAttribute<TradeProductsEntity, Boolean> temDesconto;
    public static volatile SingularAttribute<TradeProductsEntity, BigDecimal> valor;
    public static volatile SingularAttribute<TradeProductsEntity, BigDecimal> desconto;
    public static volatile SingularAttribute<TradeProductsEntity, ProductEntity> product;
    public static volatile SingularAttribute<TradeProductsEntity, NegotiationEntity> negotiation;
}
