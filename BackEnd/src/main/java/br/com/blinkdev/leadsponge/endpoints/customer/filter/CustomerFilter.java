package br.com.blinkdev.leadsponge.endpoints.customer.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFilter {
    private String nome;
    private String url;
    private String resumo;
}
