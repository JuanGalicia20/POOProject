package com.project.app.aplicacionmovil;


import java.util.ArrayList;

public class Periodo {
    private ArrayList<Actividades> actividad = new ArrayList<>();
    private int hora;


    public Periodo(ArrayList<Actividades> actividad, int hora) {
        this.actividad = actividad;
        this.hora = hora;
    }

    public Periodo(){

    }

    public ArrayList<Actividades> getActividad() {
        return actividad;
    }

    public int getHora() {
        return hora;
    }

    
    @Override
    public String toString() {
        return "Periodo{" +
                "actividad='" + actividad + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
