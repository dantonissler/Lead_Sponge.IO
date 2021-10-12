package br.com.blinkdev.leadsponge.endpoints.task.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeTask {
    EMAIL("E-mail"), REUNIAO("Reunião"), VISITA("Visita"), TAREFA("Tarefa"), ALMOCO("Almoço");

    private final String descricao;

}
