package br.com.blinkdev.leadsponge.endPoints.tarefa.filter;

import br.com.blinkdev.leadsponge.endPoints.tarefa.enumeration.TipoTarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaFilter {
    private String assunto;
    private String descricao;
    private LocalDateTime horaMarcada;
    private Boolean realizada;
    private LocalDateTime horaRealizada;
    private TipoTarefa tipo;
}
