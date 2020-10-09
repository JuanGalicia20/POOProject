package com.project.app.aplicacionmovil;


public class Periodo {
    private Actividades actividad;
    private int hora;

    public Periodo(Actividades actividad, String color) {
        this.actividad = actividad;
        this.hora = hora;
    }

    public Actividades getActividad() {
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
