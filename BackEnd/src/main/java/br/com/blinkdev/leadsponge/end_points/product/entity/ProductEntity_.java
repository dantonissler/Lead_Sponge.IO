package br.com.blinkdev.leadsponge.end_points.product.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductEntity.class)
public abstract class ProductEntity_ {
    public static volatile SingularAttribute<ProductEntity, Long> id;
    public static volatile SingularAttribute<ProductEntity, String> name;
    public static volatile SingularAttribute<ProductEntity, String> description;
    public static volatile SingularAttribute<ProductEntity, BigDecimal> value;
    public static volatile SingularAttribute<ProductEntity, Boolean> visibility;
}
