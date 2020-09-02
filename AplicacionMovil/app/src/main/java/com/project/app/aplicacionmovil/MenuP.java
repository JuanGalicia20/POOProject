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
    private int contador=0;
    private HashMap<Integer, String> consejos = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        btnHorarios = (ImageButton)findViewById(R.id.btnhorarios);
        btnHorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Scanner leer = new Scanner(System.in);
                System.out.println("Ingrese el nombre");
                //String nom = leer.nextLine();
                System.out.println("Ingrese el color");
                //String color = leer.next();
                Actividades act = new Actividades("Tarea para mañana","Rojo");
                System.out.println(act.toString());
            }
        });

        btnTareas=(ImageButton)findViewById(R.id.btntTareas);
        btnTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Scanner leer = new Scanner(System.in);
                System.out.println("Ingrese el dia");
                //String dsem = leer.next();
                Dia dia = new Dia("Lunes");
                System.out.println(dia.toString());
            }
        });

        btnConsejos=(ImageButton)findViewById(R.id.btnConsejos);
        btnConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consejos.put(contador,"Levantate cada 20 minutos y mira a 20 metros por 20 segundos");
                contador++;
                Pregunta pr = new Pregunta("Salud",consejos);
                System.out.println(pr.toString());
            }
        });

        btnPlanificacion=(ImageButton)findViewById(R.id.btnPlanificacion);
        btnPlanificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Scanner leer = new Scanner(System.in);
                System.out.println("Ingrese el nombre del pendiente");
                //String nombreP = leer.nextLine();
                System.out.println("Ingrese el mes");
                //int mes = leer.nextInt();
                System.out.println("Ingrese el dia");
                //int dia = leer.nextInt();
                System.out.println("Ingrese la descripcion del pendiente");
                //String descP = leer.nextLine();
                Date date = new Date(120,9 ,2);
                Pendientes pend1 = new Pendientes("Tarea de Calculo",date,"Ejercitacion #5 de Calculo1 sobre aplicaciones de la derivada");
                System.out.println(pend1.toString());
            }
        });

        button = (ImageButton) findViewById(R.id.options);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuPrincipal();
            }
        });
    }

    public void openMenuPrincipal()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}