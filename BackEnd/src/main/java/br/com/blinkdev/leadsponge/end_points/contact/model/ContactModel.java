package br.com.blinkdev.leadsponge.end_points.contact.model;

import br.com.blinkdev.leadsponge.resources.address.model.AddressModel;
import br.com.blinkdev.leadsponge.end_points.customer.entity.CustomerEntity;
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
    private String name;
    private String office;

    private CustomerEntity cliente;
    private List<PhoneModel> phone;
    private List<EmailModel> email;
    private List<AddressModel> address;
}
