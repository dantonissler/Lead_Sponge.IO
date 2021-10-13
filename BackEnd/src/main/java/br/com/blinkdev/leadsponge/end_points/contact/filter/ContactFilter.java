package br.com.blinkdev.leadsponge.end_points.contact.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactFilter {
    private String name;
    private String office;

}
