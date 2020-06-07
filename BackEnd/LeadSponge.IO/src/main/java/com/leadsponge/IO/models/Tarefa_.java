package com.leadsponge.IO.models;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tarefa.class)
public abstract class Tarefa_ {
	public static volatile SingularAttribute<Tarefa, Long> id;
	public static volatile SingularAttribute<Tarefa, String> assunto;
	public static volatile SingularAttribute<Tarefa, String> descricao;
	public static volatile SingularAttribute<Tarefa, LocalDateTime> horaMarcada;
	public static volatile SingularAttribute<Tarefa, TipoTarefa> tipo;
	public static volatile ListAttribute<Tarefa, Usuario> usuario;
	public static volatile SingularAttribute<Tarefa, Cliente> cliente;
	public static volatile SingularAttribute<Tarefa, Oportunidade> oportunidade;
	public static final String ID = "id";
	public static final String ASSUNTO = "assunto";
	public static final String DESCRICAO = "descricao";
	public static final String HORA_MARCADA = "horaMarcada";
	public static final String TIPO = "tipo";
	public static final String USUARIO = "usuario";
	public static final String CLIENTE = "cliente";
	public static final String OPORTUNIDADE = "oportunidade";
}
