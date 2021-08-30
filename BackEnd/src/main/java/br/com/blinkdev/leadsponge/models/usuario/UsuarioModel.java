package br.com.blinkdev.leadsponge.models.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel extends RepresentationModel<UsuarioModel> {
    private Long id;
    private String username;
    private String nomeCompleto;
    private String email;
    private Boolean enabled;
}
