package com.example.parcial2hpa5;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

    // Título de la tarea
    private String title;

    // Fecha de vencimiento de la tarea
    private Date dueDate;

    // Estado de completado de la tarea
    private boolean completed;

    // Constructor de la clase
    public Task(String title, Date dueDate, boolean completed) {
        this.title = title;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    // Getters y setters de los atributos
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
