package com.project.app.aplicacionmovil;

import java.util.ArrayList;

public class Horario_dia {
    private String name;
    private ArrayList<Periodo> periodos = new ArrayList<>();

    public Horario_dia(String name) {
        this.name = name;
    }

    public Horario_dia(String name, ArrayList<Periodo> periodos) {
        this.name = name;
        this.periodos = periodos;
    }

    public void setPeriodos(ArrayList<Periodo> periodos) {
        this.periodos = periodos;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Periodo> getPeriodos() {
        return periodos;
    }
}
