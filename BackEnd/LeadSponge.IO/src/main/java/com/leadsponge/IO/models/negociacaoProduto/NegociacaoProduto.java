package com.leadsponge.IO.models.negociacaoProduto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.negociacao.TipoReincidencia;
import com.leadsponge.IO.models.produto.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "negociacao_produto")
@TableGenerator(name = "negociacao_produto_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "negociacao_produto", allocationSize = 1, initialValue = 0)
public class NegociacaoProduto extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.NegociacaoProduto.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "negociacao_produto_id")
	private Long id;

	@NotNull
	@Column(name = "quantidade")
	private Integer quantidade;

	@NotNull
	@Column(name = "valor")
	private BigDecimal valor;

	@NotNull
	@Column(name = "tipo_reincidencia")
	private TipoReincidencia reincidencia;

	@Column(name = "desconto")
	private BigDecimal desconto;

	@Column(name = "tem_desconto")
	private Boolean temDesconto;

	@Column(name = "total")
	private BigDecimal total;

	@Column(name = "tipo_desconto")
	private TipoDesconto tipoDesconto;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "negociacao_id")
	private Negociacao negociacao;

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

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
