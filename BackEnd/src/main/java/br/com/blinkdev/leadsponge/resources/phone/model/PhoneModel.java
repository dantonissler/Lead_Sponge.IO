package br.com.blinkdev.leadsponge.resources.phone.model;

import br.com.blinkdev.leadsponge.resources.phone.enumeration.TypePhone;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "phones", itemRelation = "phone")
@JsonInclude(Include.NON_NULL)
public class PhoneModel extends RepresentationModel<PhoneModel> {
    private Long id;
    private String numero;
    private String codigoArea;
    private String codigoPais;
    private Boolean temWhatsapp;
    private TypePhone tipo;
}
