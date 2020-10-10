package com.project.app.aplicacionmovil;


public class HorarioDiario extends Horario {

    private String nombreHorario;
    private ArrayList<Periodo> listaPeriodos;

    public HorarioDiario(String nombreHorario) {
        this.nombreHorario = nombreHorario;
        listaPeriodos = new Arraylist<Periodo>(); 
    }

    public String getNombreHorario() {
        return nombreHorario;
    }

    
    public void setNombreHorario(String nombreHorario) {
        this.nombreHorario = nombreHorario;
    }

    public ArrayList<Periodo> getListaPeriodos(){
        return listaPeriodos;
    }

    public void addPeriodo(Periodo newPeriodo){
        listaPeriodo.add(newPeriodo);
    }

    public String toString() {
        return "Horario de " + nombreHorario + "{" +
                "Mis periodos='" + listaPeriodos + '\'' +'}';
    }
}
