package com.leadsponge.IO.models.fonteNegociacao;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.leadsponge.IO.models.negociacao.Negociacao;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FonteNegociacao.class)
public abstract class FonteNegociacao_ {
	public static volatile SingularAttribute<FonteNegociacao, Long> id;
	public static volatile SingularAttribute<FonteNegociacao, String> nome;
	public static volatile SingularAttribute<FonteNegociacao, Negociacao> negociacaoFonteNegociacao;
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String NEGOCIACAO = "negociacaoFonteNegociacao";

}
