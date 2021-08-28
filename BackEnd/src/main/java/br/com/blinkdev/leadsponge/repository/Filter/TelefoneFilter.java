package br.com.blinkdev.leadsponge.repository.Filter;

import br.com.blinkdev.leadsponge.models.telefone.TipoTelefone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneFilter {
	private String numero;
	private TipoTelefone tipo;
}
