package br.com.blinkdev.leadsponge.models.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteFilter {
	private String nome;
	private String url;
	private String resumo;
}
