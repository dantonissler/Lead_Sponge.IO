package br.com.blinkdev.leadsponge.models.tarefa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarefaResumo {
	private Long id;
	private String assunto;
	private LocalDateTime horaMarcada;
	private TipoTarefa tipo;
	private String usuario;
}
