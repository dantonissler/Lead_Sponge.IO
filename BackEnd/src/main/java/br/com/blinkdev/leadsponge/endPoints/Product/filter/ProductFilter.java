package br.com.blinkdev.leadsponge.endPoints.Product.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilter {

    private String nome;

    private String descricao;

}
