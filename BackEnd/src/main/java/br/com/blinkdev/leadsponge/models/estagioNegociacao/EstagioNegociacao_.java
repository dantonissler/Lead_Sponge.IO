package br.com.blinkdev.leadsponge.models.estagioNegociacao;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.blinkdev.leadsponge.models.negociacao.Negociacao;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EstagioNegociacao.class)
public abstract class EstagioNegociacao_ {
	public static volatile SingularAttribute<EstagioNegociacao, Long> id;
	public static volatile SingularAttribute<EstagioNegociacao, String> nome;
	public static volatile SingularAttribute<EstagioNegociacao, String> apelido;
	public static volatile SingularAttribute<EstagioNegociacao, Integer> posicao;
	public static volatile ListAttribute<EstagioNegociacao, Negociacao> negociacoes;
}
