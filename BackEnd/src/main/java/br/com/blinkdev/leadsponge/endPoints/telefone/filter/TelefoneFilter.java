package br.com.blinkdev.leadsponge.endPoints.telefone.filter;

import br.com.blinkdev.leadsponge.endPoints.telefone.enumeration.TipoTelefone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneFilter {
    private String numero;
    private TipoTelefone tipo;
}
