package br.com.blinkdev.leadsponge.models.negociacao;

import br.com.blinkdev.leadsponge.models.campanha.CampanhaEntity;
import br.com.blinkdev.leadsponge.models.cliente.Cliente;
import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacao;
import br.com.blinkdev.leadsponge.models.fonteNegociacao.FonteNegociacao;
import br.com.blinkdev.leadsponge.models.historicoEstagioNegociacao.HistEstagioNegociacao;
import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProduto;
import br.com.blinkdev.leadsponge.models.tarefa.Tarefa;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Negociacao.class)
public abstract class Negociacao_ {
    public static volatile SingularAttribute<Negociacao, Long> id;
    public static volatile SingularAttribute<Negociacao, String> nome;
    public static volatile SingularAttribute<Negociacao, EstatusNegociacao> estatus;
    public static volatile SingularAttribute<Negociacao, MotivoPerda> motivoPerda;
    public static volatile SingularAttribute<Negociacao, CampanhaEntity> campanha;
    public static volatile ListAttribute<Negociacao, NegociacaoProduto> negociacaoProdutos;
    public static volatile SingularAttribute<Negociacao, Integer> avaliacao;
    public static volatile SingularAttribute<Negociacao, Date> dataPrevistaEncerramento;
    public static volatile SingularAttribute<Negociacao, BigDecimal> valorTotal;
    public static volatile SingularAttribute<Negociacao, BigDecimal> valorMensal;
    public static volatile SingularAttribute<Negociacao, BigDecimal> valorUnico;
    public static volatile SingularAttribute<Negociacao, Cliente> cliente;
    public static volatile SingularAttribute<Negociacao, EstagioNegociacao> estagio;
    public static volatile SingularAttribute<Negociacao, FonteNegociacao> fonte;
    public static volatile ListAttribute<Negociacao, HistEstagioNegociacao> histEstagioNegociacoes;
    public static volatile ListAttribute<Negociacao, Tarefa> tarefas;
}
