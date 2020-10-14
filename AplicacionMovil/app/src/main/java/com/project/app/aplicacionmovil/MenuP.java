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
    private String user;
    private ImageButton button;
    private ImageButton btnHorarios;
    private ImageButton btnTareas;
    private ImageButton btnConsejos;
    private ImageButton btnPlanificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        /**
         * Cambiar para objeto tipo user
         */
        Intent intent = getIntent();
        this.user = intent.getStringExtra("User");

        btnHorarios = (ImageButton)findViewById(R.id.btnhorarios);
        btnHorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openMenuPrincipal(2, user);

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
            openMenuPrincipal(4, user );
            }
        });

        btnPlanificacion=(ImageButton)findViewById(R.id.btnPlanificacion);
        btnPlanificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuPrincipal(5,user);
            }
        });


        button = (ImageButton) findViewById(R.id.options);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuPrincipal(1, user);
            }
        });
    }

    public void openMenuPrincipal(int opcion, String user)
    {
        if(opcion == 1) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if(opcion == 2)
        {
            Intent intent = new Intent(this,Horarios.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }

        else if(opcion == 4)
        {
            Intent intent = new Intent(this, consejos_manager.class);
            startActivity(intent);
        }

        else if(opcion == 5)
        {
            Intent intent = new Intent(this, Planificacion.class);
            startActivity(intent);
        }
    }
}