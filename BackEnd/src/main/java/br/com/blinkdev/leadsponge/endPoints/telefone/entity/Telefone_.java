package br.com.blinkdev.leadsponge.endPoints.telefone.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.blinkdev.leadsponge.endPoints.contato.entity.ContatoEntity;
import br.com.blinkdev.leadsponge.endPoints.telefone.enumeration.TipoTelefone;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Telefone.class)
public abstract class Telefone_ {
	public static volatile SingularAttribute<Telefone, Long> id;
	public static volatile SingularAttribute<Telefone, String> numero;
	public static volatile SingularAttribute<Telefone, TipoTelefone> tipo;
	public static volatile SingularAttribute<Telefone, ContatoEntity> contato;
}
