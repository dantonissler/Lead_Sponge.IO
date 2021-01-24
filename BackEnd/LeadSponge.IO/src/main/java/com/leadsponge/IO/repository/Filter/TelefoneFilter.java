package com.leadsponge.IO.repository.Filter;

import com.leadsponge.IO.models.telefone.TipoTelefone;

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
