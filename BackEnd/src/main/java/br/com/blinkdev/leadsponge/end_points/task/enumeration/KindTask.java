package br.com.blinkdev.leadsponge.end_points.task.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KindTask {
    EMAIL("E-mail"), MEETING("Reunião"), VISIT("Visita"), TASK("Tarefa"), LUNCH("Almoço");

    private final String description;

}
