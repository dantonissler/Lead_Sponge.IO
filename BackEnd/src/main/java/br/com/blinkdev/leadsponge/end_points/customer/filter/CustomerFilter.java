package br.com.blinkdev.leadsponge.end_points.customer.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFilter {
    private String name;
    private String url;
    private String summary;
}
