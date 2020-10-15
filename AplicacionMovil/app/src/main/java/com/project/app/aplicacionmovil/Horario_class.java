package com.project.app.aplicacionmovil;

import java.util.ArrayList;

public class Horario_class {

    private ArrayList<Horario_dia> Dias;
    private String name;
    private String length;

    public Horario_class(ArrayList<Horario_dia> dias, String name, String length) {
        Dias = dias;
        this.name = name;
        this.length = length;
    }

    public ArrayList<Horario_dia> getDias() {
        return Dias;
    }

    public String getName() {
        return name;
    }

    public String getLength() {
        return length;
    }


}
