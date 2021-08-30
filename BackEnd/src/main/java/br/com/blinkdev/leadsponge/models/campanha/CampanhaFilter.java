package br.com.blinkdev.leadsponge.models.campanha;

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
