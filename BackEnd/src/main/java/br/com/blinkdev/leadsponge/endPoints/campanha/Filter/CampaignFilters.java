package br.com.blinkdev.leadsponge.endPoints.campanha.Filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignFilters {
	private String nome;
	private String descricao;
}
