package com.project.app.aplicacionmovil;

import java.util.ArrayList;
import java.util.Map;

public class Pregunta {
    private String nombre;
    private Map consejos;

    public Map getConsejo() {
        return consejos;
    }

    public String getName() {
        return nombre;
    }

    public Pregunta(String nombre, Map consejos) {
        this.nombre= nombre;
        this.consejos = consejos;
    }

    public Pregunta(){

    }


    @Override
    public String toString() {
        return "Pregunta{" +
                "name='" + nombre + '\'' +
                ", consejos='" + consejos + '\'' +
                '}';
    }
}
