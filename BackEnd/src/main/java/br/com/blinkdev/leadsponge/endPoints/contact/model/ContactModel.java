package br.com.blinkdev.leadsponge.endPoints.contact.model;

import br.com.blinkdev.leadsponge.resources.address.model.AddressModel;
import br.com.blinkdev.leadsponge.endPoints.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.resources.email.model.EmailModel;
import br.com.blinkdev.leadsponge.resources.phone.model.PhoneModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "contacts", itemRelation = "contact")
@JsonInclude(Include.NON_NULL)
public class ContactModel extends RepresentationModel<ContactModel> {
    private Long id;
    private CustomerEntity cliente;
    private String nome;
    private String cargo;

    private List<PhoneModel> phone;
    private List<EmailModel> email;
    private List<AddressModel> address;
}
