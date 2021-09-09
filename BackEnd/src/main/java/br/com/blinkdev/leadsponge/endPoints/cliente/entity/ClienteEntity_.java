package br.com.blinkdev.leadsponge.endPoints.cliente.entity;

import br.com.blinkdev.leadsponge.endPoints.contato.entity.ContatoEntity;
import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.segmento.entity.SegmentoEntity;
import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ClienteEntity.class)
public abstract class ClienteEntity_ {
	public static volatile SingularAttribute<ClienteEntity, Long> id;
	public static volatile SingularAttribute<ClienteEntity, String> nome;
	public static volatile SingularAttribute<ClienteEntity, String> url;
	public static volatile SingularAttribute<ClienteEntity, String> resumo;
	public static volatile ListAttribute<ClienteEntity, NegociacaoEntity> negociacoes;
	public static volatile ListAttribute<ClienteEntity, ContatoEntity> contato;
	public static volatile ListAttribute<ClienteEntity, SegmentoEntity> segmentos;
	public static volatile SingularAttribute<ClienteEntity, UserEntity> responsavel;
	public static volatile ListAttribute<ClienteEntity, UserEntity> seguidores;
}