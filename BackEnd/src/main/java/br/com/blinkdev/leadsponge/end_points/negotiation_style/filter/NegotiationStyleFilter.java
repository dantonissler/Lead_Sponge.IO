package br.com.blinkdev.leadsponge.end_points.negotiation_style.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegotiationStyleFilter {
    private String name;
    private String surname;
    private Integer position;
}
