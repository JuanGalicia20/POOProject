package com.project.app.aplicacionmovil;


public class Periodo {
    private Actividades actividad;
    private int hora;

    public Periodo(Actividades actividad, String color) {
        this.actividad = actividad;
        this.hora = hora;
    }

    public String getActividad() {
        return actividad;
    }

    public String getHora() {
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
