package br.com.blinkdev.leadsponge.endPoints.phone.filter;

import br.com.blinkdev.leadsponge.endPoints.phone.enumeration.TypePhone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneFilter {
    private String numero;
    private TypePhone tipo;
}
