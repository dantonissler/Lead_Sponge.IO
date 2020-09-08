package com.leadsponge.IO.models.produto;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produtos")
@TableGenerator(name = "produto_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "produtos", allocationSize = 1, initialValue = 0)
public class Produto extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Produto.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "produto_id")
	private Long id;

	@Size(min = 4, max = 100, message = "{produto.nome.size}")
	private String nome;

	@Size(max = 150, message = "{produto.descricao.size}")
	private String descricao;

	@NotNull
	private BigDecimal valor;

	@NotNull
	private Boolean visibilidade;

	@JsonIgnore
	@JsonIgnoreProperties(value = { "negociacao", "produto" })
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Valid
	private List<NegociacaoProduto> negociacaoProdutos;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
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
