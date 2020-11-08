package com.project.app.aplicacionmovil;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

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
                boolean con = conexion();
                if(con)
                {
                    openMenuPrincipal(2, user);
                }
                else
                {
                    Intent intent = new Intent(MenuP.this, Internet.class);
                    startActivity(intent);
                }


            }
        });

        btnTareas=(ImageButton)findViewById(R.id.btntTareas);
        btnTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean con = conexion();
                if(con)
                {
                    openMenuPrincipal(3, user);
                }
                else
                {
                    Intent intent = new Intent(MenuP.this, Internet.class);
                    startActivity(intent);
                }

            }
        });

        btnConsejos=(ImageButton)findViewById(R.id.btnConsejos);
        btnConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean con = conexion();
                if(con)
                {
                    openMenuPrincipal(4, user );
                }
                else
                {
                    Intent intent = new Intent(MenuP.this, Internet.class);
                    startActivity(intent);
                }

            }
        });

        btnPlanificacion=(ImageButton)findViewById(R.id.btnPlanificacion);
        btnPlanificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean con = conexion();
                if(con)
                {
                    openMenuPrincipal(5,user);
                }
                else
                {
                    Intent intent = new Intent(MenuP.this, Internet.class);
                    startActivity(intent);
                }

            }
        });


        button = (ImageButton) findViewById(R.id.options);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean con = conexion();
                if(con)
                {
                    openMenuPrincipal(1, user);
                }
                else
                {
                    Intent intent = new Intent(MenuP.this, Internet.class);
                    startActivity(intent);
                }

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
        else if(opcion == 3)
        {
            Intent intent = new Intent(this, Tareas.class);
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

    public boolean conexion()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo!=null && networkInfo.isConnected())
        {
            return true;
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No está conectado a internet", Toast.LENGTH_SHORT).show();

            return false;
        }
    }
}