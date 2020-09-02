package com.project.app.aplicacionmovil;

public class Actividades {
    private String name;
    private String color;

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Actividades(String name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Actividades{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
