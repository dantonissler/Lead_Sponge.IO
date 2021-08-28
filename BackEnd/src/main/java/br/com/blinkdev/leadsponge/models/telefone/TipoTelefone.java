package br.com.blinkdev.leadsponge.models.telefone;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoTelefone {

	COMERCIAL("Comercial"), RESIDENCIAL("Residencial"), PESSOAL("Pessoal"), FAX("Fax");

	private final String descricao;

}
