package br.com.blinkdev.leadsponge.models.campanha;

import br.com.blinkdev.leadsponge.models.negociacao.Negociacao;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CampanhaEntity.class)
public abstract class Campanha_ {
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String NEGOCIACOES = "negociacoes";
    public static volatile SingularAttribute<CampanhaEntity, Long> id;
    public static volatile SingularAttribute<CampanhaEntity, String> nome;
    public static volatile SingularAttribute<CampanhaEntity, String> descricao;
    public static volatile ListAttribute<CampanhaEntity, Negociacao> negociacoes;
}
