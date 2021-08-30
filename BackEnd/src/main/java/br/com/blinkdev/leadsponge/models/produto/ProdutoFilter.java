package br.com.blinkdev.leadsponge.models.produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoFilter {

    private String nome;

    private String descricao;

}
