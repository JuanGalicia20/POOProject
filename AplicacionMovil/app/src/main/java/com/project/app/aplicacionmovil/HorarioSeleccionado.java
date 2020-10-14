package com.project.app.aplicacionmovil;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HorarioSeleccionado extends AppCompatActivity {

    private ImageView lunes;
    private ImageView martes;
    private ImageView miercoles;
    private ImageView jueves;
    private ImageView viernes;
    private ImageView sabado;
    private ImageView domingo;

    private LinearLayout lunesLl;
    private LinearLayout martesLl;
    private LinearLayout miercolesLl;
    private LinearLayout juevesLl;
    private LinearLayout viernesLl;
    private LinearLayout sabadoLl;
    private LinearLayout domingoLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_seleccionado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        lunes = (ImageView)findViewById(R.id.imglunes);
        martes = (ImageView)findViewById(R.id.imgmartes);
        miercoles = (ImageView)findViewById(R.id.imgmiercoles);
        jueves = (ImageView)findViewById(R.id.imgjueves);
        viernes = (ImageView)findViewById(R.id.imgviernes);
        sabado = (ImageView)findViewById(R.id.imgsabado);
        domingo = (ImageView)findViewById(R.id.imgdomingo);

        lunesLl = (LinearLayout)findViewById(R.id.nuevosLunes);
        martesLl = (LinearLayout)findViewById(R.id.nuevosMartes);
        miercolesLl=(LinearLayout)findViewById(R.id.nuevosMiercoles);
        juevesLl=(LinearLayout)findViewById(R.id.nuevosJueves);
        viernesLl=(LinearLayout)findViewById(R.id.nuevosViernes);
        sabadoLl=(LinearLayout)findViewById(R.id.nuevosSabado);
        domingoLl=(LinearLayout)findViewById(R.id.nuevosDomingo);

        lunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        martes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        miercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        jueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        viernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sabado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        domingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}