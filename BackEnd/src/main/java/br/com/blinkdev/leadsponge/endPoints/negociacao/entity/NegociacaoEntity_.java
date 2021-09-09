package br.com.blinkdev.leadsponge.endPoints.negociacao.entity;

import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.entity.FonteNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampanhaEntity;
import br.com.blinkdev.leadsponge.endPoints.cliente.entity.ClienteEntity;
import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.entity.EstagioNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.historicoEstagioNegociacao.entity.HistEstagioNegociacao;
import br.com.blinkdev.leadsponge.endPoints.motivoPerda.entity.MotivoPerdaEntity;
import br.com.blinkdev.leadsponge.endPoints.negociacao.enumeration.EstatusNegociacao;
import br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.entity.NegociacaoProdutoEntity;
import br.com.blinkdev.leadsponge.endPoints.tarefa.entity.TarefaEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NegociacaoEntity.class)
public abstract class NegociacaoEntity_ {
    public static volatile SingularAttribute<NegociacaoEntity, Long> id;
    public static volatile SingularAttribute<NegociacaoEntity, String> nome;
    public static volatile SingularAttribute<NegociacaoEntity, EstatusNegociacao> estatus;
    public static volatile SingularAttribute<NegociacaoEntity, MotivoPerdaEntity> motivoPerda;
    public static volatile SingularAttribute<NegociacaoEntity, CampanhaEntity> campanha;
    public static volatile ListAttribute<NegociacaoEntity, NegociacaoProdutoEntity> negociacaoProdutos;
    public static volatile SingularAttribute<NegociacaoEntity, Integer> avaliacao;
    public static volatile SingularAttribute<NegociacaoEntity, Date> dataPrevistaEncerramento;
    public static volatile SingularAttribute<NegociacaoEntity, BigDecimal> valorTotal;
    public static volatile SingularAttribute<NegociacaoEntity, BigDecimal> valorMensal;
    public static volatile SingularAttribute<NegociacaoEntity, BigDecimal> valorUnico;
    public static volatile SingularAttribute<NegociacaoEntity, ClienteEntity> cliente;
    public static volatile SingularAttribute<NegociacaoEntity, EstagioNegociacaoEntity> estagio;
    public static volatile SingularAttribute<NegociacaoEntity, FonteNegociacaoEntity> fonte;
    public static volatile ListAttribute<NegociacaoEntity, HistEstagioNegociacao> histEstagioNegociacoes;
    public static volatile ListAttribute<NegociacaoEntity, TarefaEntity> tarefas;
}
