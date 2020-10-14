package com.project.app.aplicacionmovil;

import java.util.ArrayList;
import java.util.*;

public class Pregunta {
    private String nombre;
    private ArrayList<String> respuestas;

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }

    public String getName() {
        return nombre;
    }

    public Pregunta(String nombre, ArrayList<String> respuestas) {
        this.nombre= nombre;
        this.respuestas = respuestas;
    }

    public Pregunta(){

    }


    @Override
    public String toString() {
        return "Pregunta{" +
                "name='" + nombre + '\'' +
                ", respuesta='" + respuestas.get(0) + '\'' + respuestas.get(1)+
                '}';
    }
}
