package br.com.blinkdev.leadsponge.endpoints.Product.entity;

import br.com.blinkdev.leadsponge.relationship.tradeProducts.entity.TradeProductsEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductEntity.class)
public abstract class ProductEntity_ {
    public static volatile SingularAttribute<ProductEntity, Long> id;
    public static volatile SingularAttribute<ProductEntity, String> nome;
    public static volatile SingularAttribute<ProductEntity, String> descricao;
    public static volatile SingularAttribute<ProductEntity, BigDecimal> valor;
    public static volatile ListAttribute<ProductEntity, TradeProductsEntity> negociacaoProdutos;
    public static volatile SingularAttribute<ProductEntity, Boolean> visibilidade;
}
