package com.leadsponge.IO.models.tarefa;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.usuario.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tarefa.class)
public abstract class Tarefa_ {
	public static volatile SingularAttribute<Tarefa, Long> id;
	public static volatile SingularAttribute<Tarefa, String> assunto;
	public static volatile SingularAttribute<Tarefa, String> descricao;
	public static volatile SingularAttribute<Tarefa, LocalDateTime> horaMarcada;
	public static volatile SingularAttribute<Tarefa, TipoTarefa> tipo;
	public static volatile SingularAttribute<Tarefa, Usuario> usuarioTarefa;
	public static volatile SingularAttribute<Tarefa, Cliente> clienteTarefa;
	public static volatile SingularAttribute<Tarefa, Negociacao> negociacaoTarefa;
	public static final String ID = "id";
	public static final String ASSUNTO = "assunto";
	public static final String DESCRICAO = "descricao";
	public static final String HORA_MARCADA = "horaMarcada";
	public static final String TIPO = "tipo";
	public static final String USUARIO = "usuario";
	public static final String CLIENTE = "cliente";
	public static final String NEGOCIACAO = "negociacao";
}
