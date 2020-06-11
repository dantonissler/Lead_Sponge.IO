package com.leadsponge.IO.models.negociacao;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao;
import com.leadsponge.IO.models.historicoEstagioNegociacao.HistEstagioNegociacao;
import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;
import com.leadsponge.IO.models.tarefa.Tarefa;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Negociacao.class)
public abstract class Negociacao_ {
	public static volatile SingularAttribute<Negociacao, Long> id;
	public static volatile SingularAttribute<Negociacao, String> nome;
	public static volatile SingularAttribute<Negociacao, Campanha> campanhaNegociacoes;
	public static volatile SingularAttribute<Negociacao, NegociacaoProduto> negociacaoProdutoN;
	public static volatile SingularAttribute<Negociacao, Integer> avaliacao;
	public static volatile SingularAttribute<Negociacao, BigDecimal> valorTotal;
	public static volatile SingularAttribute<Negociacao, BigDecimal> valorMensal;
	public static volatile SingularAttribute<Negociacao, BigDecimal> valorUnico;
	public static volatile SingularAttribute<Negociacao, Cliente> clienteNegociacao;
	public static volatile SingularAttribute<Negociacao, EstagioNegociacao> estagioNegociacaoNegociacao;
	public static volatile SingularAttribute<Negociacao, FonteNegociacao> fonteNegociacaoNegociacao;
	public static volatile ListAttribute<Negociacao, HistEstagioNegociacao> histEstagioNegociacaoNegociacao;
	public static volatile SingularAttribute<Negociacao, Tarefa> tarefaNegociacao;
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String CAMPANHA = "campanhaNegociacoes";
	public static final String NEGOCIACAO_PRODUTO = "negociacaoProdutoN";
	public static final String AVALIACAO = "avaliacao";
	public static final String VALOR_TOTAL = "valorTotal";
	public static final String VALOR_MENSAL = "valorMensal";
	public static final String VALOR_UNICO = "valorUnico";
	public static final String CLIENTE = "clienteNegociacao";
	public static final String ESTAGIO_NEGOCIACAO = "estagioNegociacaoNegociacao";
	public static final String FONTE_NEGOCIACAO = "fonteNegociacaoNegociacao";
	public static final String HIST_ESTAGIO_NEGOCIACAO = "histEstagioNegociacaoNegociacao";
	public static final String TAREFA = "tarefaNegociacao";
}
