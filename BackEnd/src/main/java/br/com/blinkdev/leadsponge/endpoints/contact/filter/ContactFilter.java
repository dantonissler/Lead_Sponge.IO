package br.com.blinkdev.leadsponge.endpoints.contact.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactFilter {
    private String nome;
    private String cargo;

}
