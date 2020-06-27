package com.leadsponge.IO.repository.projection;

import java.util.Date;

import com.leadsponge.IO.models.tarefa.TipoTarefa;

public class ResumoTarefa {

	private Long id;
	private String assunto;
	private Date horaMarcada;
	private TipoTarefa tipo;
	private String usuario;
	private String cliente;

	public ResumoTarefa(Long id, String assunto, Date horaMarcada, TipoTarefa tipo, String usuario, String cliente) {
		this.id = id;
		this.assunto = assunto;
		this.horaMarcada = horaMarcada;
		this.tipo = tipo;
		this.usuario = usuario;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Date getHoraMarcada() {
		return horaMarcada;
	}

	public void setHoraMarcada(Date horaMarcada) {
		this.horaMarcada = horaMarcada;
	}

	public TipoTarefa getTipo() {
		return tipo;
	}

	public void setTipo(TipoTarefa tipo) {
		this.tipo = tipo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

}
