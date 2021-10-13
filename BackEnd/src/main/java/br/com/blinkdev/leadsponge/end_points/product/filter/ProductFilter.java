package br.com.blinkdev.leadsponge.end_points.product.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilter {
    private String name;
    private String description;
}
