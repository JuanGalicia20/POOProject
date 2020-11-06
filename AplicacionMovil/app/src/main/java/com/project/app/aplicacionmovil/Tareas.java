package com.project.app.aplicacionmovil;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Tareas extends AppCompatActivity {

    private String descripcion;
    private String titulo;
    private String color;
    private boolean estado; //finalizado o pendiente

    protected void onCreate(Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
    }

}
