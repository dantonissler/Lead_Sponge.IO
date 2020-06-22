package com.leadsponge.IO.models.motivoPerda;

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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.negociacao.Negociacao;

import lombok.Data;

@Entity
@Data
@Table(name = "motivo_perda")
@TableGenerator(name = "motivo_perda_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "motivo_perda", allocationSize = 1, initialValue = 0)
public class MotivoPerda extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.MotivoPerda.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "motivo_perda_id")
	private Long id;

	@Size(min = 4, max = 50, message = "{nome.size}")
	@Column(name = "nome")
	private String nome;
	
	@OneToMany(mappedBy = "motivoPerdaNegociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Negociacao> negociacaoMotivoPerda;

	public MotivoPerda() {
		// TODO Auto-generated constructor stub
	}

	public MotivoPerda(String nome) {
		super();
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
