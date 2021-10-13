package br.com.blinkdev.leadsponge.end_points.task.filter;

import br.com.blinkdev.leadsponge.end_points.task.enumeration.KindTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskFilter {
    private String subject;
    private String description;
    private LocalDateTime appointment;
    private Boolean done;
    private LocalDateTime timePerformed;
    private KindTask kind;
}
