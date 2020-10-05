package com.project.app.aplicacionmovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Horarios extends AppCompatActivity {
    private CardView anadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
    }



    public void openHorarioSeleccionado() {
        Intent intent = new Intent(this, HorarioSeleccionado.class);
        startActivity(intent);
    }
}