package br.com.blinkdev.leadsponge.endPoints.tarefa.model;

import br.com.blinkdev.leadsponge.endPoints.tarefa.enumeration.TipoTarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarefaModel {
	private Long id;
	private String assunto;
	private LocalDateTime horaMarcada;
	private TipoTarefa tipo;
	private String usuario;
}
