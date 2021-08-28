package br.com.blinkdev.leadsponge.repository.projection;

import java.time.LocalDateTime;

import br.com.blinkdev.leadsponge.models.tarefa.TipoTarefa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
