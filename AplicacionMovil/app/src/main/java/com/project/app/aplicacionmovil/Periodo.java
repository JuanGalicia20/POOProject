package com.project.app.aplicacionmovil;


public class Periodo {
    private int horaInicial;
    private int horaFinal;
    private int minInicial;
    private int minFinal;
    private Actividades actividad;

    public Periodo(int horaInicial, int horaFinal, int minInicial, int minFinal, Actividades actividad) {
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.minInicial = minInicial;
        this.minFinal = minInicial;
        this.actividad = actividad;
    }

    public Actividades getActividad() {
        return actividad;
    }

    public int getHoraInicial() {
        return horaInicial;
    }

    public int getHoraFinal() {
        return horaFinal;
    }

    public int getMinInicial() {
        return minInicial;
    }

    public int getMinFinal() {
        return minFinal;
    }

    public void setActividad(Actividades actividad) {
        this.actividad = actividad;
    }

    public void setHoraInicial() {
        this.horaInicial = horaInicial;
    }

    public void setHoraFinal() {
        this.horaFinal = horaFinal;
    }

    public void setMinInicial() {
        this.minInicial = minInicial;
    }

    public void setMinFinal() {
        this.minFinal = minFinal;
    }

    


    
    @Override
    public String toString() {
        return "Periodo{" +
                "actividad='" + actividad + '\'' +
                ", hora='" + horaInicial + ":"+ minInicial + "-" + horaFinal + ":" + minFinal + '\'' +
                '}';
    }
}
