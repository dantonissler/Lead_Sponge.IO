package com.leadsponge.IO.models.negociacaoProduto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.produto.Produto;

import lombok.Data;

@Entity
@Data
@Table(name = "negociacao_produto")
@TableGenerator(name = "negociacao_produto_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "negociacao_produto", allocationSize = 1, initialValue = 0)
public class NegociacaoProduto extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.NegociacaoProduto.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "negociacao_produto_id")
	private Long id;

	@NotNull
	@Column(name = "quantidade_produtos")
	private Integer quantidadeProdutos;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_id")
	@JsonBackReference("negociacaoProdutoP")
	private Produto produtoNegociacaoProduto;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "negociacao_id")
	@JsonBackReference("negociacaoProdutoN")
	private Negociacao negociacaoProdutoNegociacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidadeProdutos() {
		return quantidadeProdutos;
	}

	public void setQuantidadeProdutos(Integer quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}

	public Produto getProdutoNegociacaoProduto() {
		return produtoNegociacaoProduto;
	}

	public void setProdutoNegociacaoProduto(Produto produtoNegociacaoProduto) {
		this.produtoNegociacaoProduto = produtoNegociacaoProduto;
	}

	public Negociacao getNegociacaoProdutoNegociacao() {
		return negociacaoProdutoNegociacao;
	}

	public void setNegociacaoProdutoNegociacao(Negociacao negociacaoProdutoNegociacao) {
		this.negociacaoProdutoNegociacao = negociacaoProdutoNegociacao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NegociacaoProduto other = (NegociacaoProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
