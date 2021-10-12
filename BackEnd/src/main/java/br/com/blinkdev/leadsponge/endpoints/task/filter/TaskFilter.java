package br.com.blinkdev.leadsponge.endpoints.task.filter;

import br.com.blinkdev.leadsponge.endpoints.task.enumeration.TypeTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskFilter {
    private String assunto;
    private String descricao;
    private LocalDateTime horaMarcada;
    private Boolean realizada;
    private LocalDateTime horaRealizada;
    private TypeTask tipo;
}
