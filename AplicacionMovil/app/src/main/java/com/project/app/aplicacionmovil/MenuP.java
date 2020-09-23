package com.project.app.aplicacionmovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.Scanner;
import java.util.HashMap;

public class MenuP extends AppCompatActivity {
    private ImageButton button;
    private ImageButton btnHorarios;
    private ImageButton btnTareas;
    private ImageButton btnConsejos;
    private ImageButton btnPlanificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        btnHorarios = (ImageButton)findViewById(R.id.btnhorarios);
        btnHorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnTareas=(ImageButton)findViewById(R.id.btntTareas);
        btnTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnConsejos=(ImageButton)findViewById(R.id.btnConsejos);
        btnConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openMenuPrincipal(4);
            }
        });

        btnPlanificacion=(ImageButton)findViewById(R.id.btnPlanificacion);
        btnPlanificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button = (ImageButton) findViewById(R.id.options);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuPrincipal(1);
            }
        });
    }

    public void openMenuPrincipal(int opcion)
    {
        if(opcion == 1) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if(opcion == 4)
        {
            Intent intent = new Intent(this, consejos_manager.class);
            startActivity(intent);
        }
    }
}