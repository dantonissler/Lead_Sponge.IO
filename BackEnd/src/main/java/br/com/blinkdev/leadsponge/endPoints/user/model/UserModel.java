package br.com.blinkdev.leadsponge.endPoints.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends RepresentationModel<UserModel> {
    private Long id;
    private String username;
    private String nomeCompleto;
    private String email;
    private Boolean enabled;
}
