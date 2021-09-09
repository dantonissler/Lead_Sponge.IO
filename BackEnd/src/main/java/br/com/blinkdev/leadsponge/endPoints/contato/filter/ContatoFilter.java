package br.com.blinkdev.leadsponge.endPoints.contato.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContatoFilter {
	private String nome;
	private String cargo;

}
