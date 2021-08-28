package br.com.blinkdev.leadsponge.repository.Filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaFilter {
	private String nome;
	private String descricao;
}
