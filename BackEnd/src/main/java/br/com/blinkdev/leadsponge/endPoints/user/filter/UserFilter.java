package br.com.blinkdev.leadsponge.endPoints.user.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFilter {
	private String nomeCompleto;
	private String email;
	private String username;
}
