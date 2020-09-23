

package com.project.app.aplicacionmovil;

import java.util.ArrayList;
import java.util.Map;

public class Pregunta {
    private String name;
    private Map consejo;

    public Map getConsejo() {
        return consejo;
    }

    public String getName() {
        return name;
    }

    public Pregunta(String name, Map consejo) {
        this.name = name;
        this.consejo = consejo;
    }

    public Pregunta(){

    }


    @Override
    public String toString() {
        return "Pregunta{" +
                "name='" + name + '\'' +
                ", consejo='" + consejo + '\'' +
                '}';
    }
}
