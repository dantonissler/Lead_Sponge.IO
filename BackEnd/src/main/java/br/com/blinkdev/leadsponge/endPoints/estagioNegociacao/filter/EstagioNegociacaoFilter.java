package br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstagioNegociacaoFilter {
	private String nome;
	private String apelido;
	private Integer posicao;
}
