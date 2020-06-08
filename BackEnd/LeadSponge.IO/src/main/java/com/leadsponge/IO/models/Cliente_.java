package com.leadsponge.IO.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {
	public static volatile SingularAttribute<Cliente, Long> id;
	public static volatile SingularAttribute<Cliente, String> nome;
	public static volatile SingularAttribute<Cliente, String> url;
	public static volatile SingularAttribute<Cliente, String> resumo;
	public static volatile ListAttribute<Cliente, Oportunidade> oportunidades;
	public static volatile ListAttribute<Cliente, Contato> contatos;
	public static volatile SingularAttribute<Cliente, Segmento> segmento;
	public static volatile ListAttribute<Cliente, Tarefa> tarefas;
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String URL = "url";
	public static final String RESUMO = "resumo";
	public static final String OPORTUNIDADE = "oportunidade";
	public static final String CONTATO = "contato";
	public static final String SEGENTO = "segmento";
	public static final String TAREFAS = "tarefas";
}