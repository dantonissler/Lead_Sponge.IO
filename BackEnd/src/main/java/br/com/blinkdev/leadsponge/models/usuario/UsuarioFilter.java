package br.com.blinkdev.leadsponge.models.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioFilter {
	private String nomeCompleto;
	private String email;
	private String username;
}
