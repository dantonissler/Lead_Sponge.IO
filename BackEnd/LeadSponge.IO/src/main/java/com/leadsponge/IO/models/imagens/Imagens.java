//package com.leadsponge.IO.models.imagens;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.TableGenerator;
//import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.NotNull;
//
//import com.fasterxml.jackson.annotation.JsonView;
//import com.leadsponge.IO.models.View;
//
//import lombok.Data;
//
//@Entity
//@Data
//@Table(name = "imagens", uniqueConstraints = { @UniqueConstraint(columnNames = { "nomeImagem" }) })
//@TableGenerator(name = "imagem_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "imagens", allocationSize = 1, initialValue = 0)
//public class Imagens {
//
//	@Id
//	@Column(name = "id")
//	@JsonView(View.Usuario.class)
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "usuario_id")
//	private Long id;
//
//	@NotNull
//	private String nomeImagem;
//
//	@NotNull
//	private String tipoImagem;
//
//	public Imagens(@NotNull String nomeImagem, @NotNull String tipoImagem) {
//		this.nomeImagem = nomeImagem;
//		this.tipoImagem = tipoImagem;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getNomeImagem() {
//		return nomeImagem;
//	}
//
//	public void setNomeImagem(String nomeImagem) {
//		this.nomeImagem = nomeImagem;
//	}
//
//	public String getTipoImagem() {
//		return tipoImagem;
//	}
//
//	public void setTipoImagem(String tipoImagem) {
//		this.tipoImagem = tipoImagem;
//	}
//
//}
