package com.leadsponge.IO.models.tarefa;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

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
	public static volatile SingularAttribute<Tarefa, Usuario> usuario;
	public static volatile SingularAttribute<Tarefa, Boolean> realizda;
	public static volatile SingularAttribute<Tarefa, LocalDateTime> horaRealizada;
	public static volatile SingularAttribute<Tarefa, Negociacao> negociacao;
}
