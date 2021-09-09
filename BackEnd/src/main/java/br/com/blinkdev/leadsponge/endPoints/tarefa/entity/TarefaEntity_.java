package br.com.blinkdev.leadsponge.endPoints.tarefa.entity;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.tarefa.enumeration.TipoTarefa;
import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TarefaEntity.class)
public abstract class TarefaEntity_ {
	public static volatile SingularAttribute<TarefaEntity, Long> id;
	public static volatile SingularAttribute<TarefaEntity, String> assunto;
	public static volatile SingularAttribute<TarefaEntity, String> descricao;
	public static volatile SingularAttribute<TarefaEntity, LocalDateTime> horaMarcada;
	public static volatile SingularAttribute<TarefaEntity, TipoTarefa> tipo;
	public static volatile SingularAttribute<TarefaEntity, UserEntity> usuario;
	public static volatile SingularAttribute<TarefaEntity, Boolean> realizda;
	public static volatile SingularAttribute<TarefaEntity, LocalDateTime> horaRealizada;
	public static volatile SingularAttribute<TarefaEntity, NegociacaoEntity> negociacao;
}
