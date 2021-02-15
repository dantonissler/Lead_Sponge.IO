package com.leadsponge.IO.models.motivoPerda;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.leadsponge.IO.models.negociacao.Negociacao;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MotivoPerda.class)
public abstract class MotivoPerda_ {
	public static volatile SingularAttribute<MotivoPerda, Long> id;
	public static volatile SingularAttribute<MotivoPerda, String> nome;
	public static volatile ListAttribute<MotivoPerda, Negociacao> negociacoes;
}
