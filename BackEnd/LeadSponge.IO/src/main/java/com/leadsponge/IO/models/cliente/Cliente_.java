package com.leadsponge.IO.models.cliente;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.models.tarefa.Tarefa;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {
	public static volatile SingularAttribute<Cliente, Long> id;
	public static volatile SingularAttribute<Cliente, String> nome;
	public static volatile SingularAttribute<Cliente, String> url;
	public static volatile SingularAttribute<Cliente, String> resumo;
	public static volatile ListAttribute<Cliente, Negociacao> negociacoesCliente;
	public static volatile ListAttribute<Cliente, Contato> contatosCliente;
	public static volatile SingularAttribute<Cliente, Segmento> segmentoCliente;
	public static volatile ListAttribute<Cliente, Tarefa> tarefasCliente;
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String URL = "url";
	public static final String RESUMO = "resumo";
	public static final String NEGOCIACOES = "negociacoes";
	public static final String CONTATO = "contato";
	public static final String SEGENTO = "segmento";
	public static final String TAREFAS = "tarefas";
}