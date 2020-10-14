package com.project.app.aplicacionmovil;

import java.util.ArrayList;
import java.util.Iterator;


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
        listaPeriodos.add(newPeriodo);
    }

    public String toString() {
        String showPeriodo = "";
        Iterator<Periodo> periodo = listaPeriodos.iterator();
        while (post.hasNext()){
            Periodo elemento = periodo.next();
            showPeriodo = " Horario de " + nombreHorario + "{" + "Mis periodos = " + listaPeriodos + "\n " + "}";
        }
        return showPeriodo;
    }

   

}
