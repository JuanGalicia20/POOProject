package com.project.app.aplicacionmovil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView txtNombre;
    private String nombreH;
    private String user;
    private String[] diasCom={"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
    private String[] diaslv={"Lunes","Martes","Miercoles","Jueves","Viernes"};
    private ImageView crearAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.nombreH = intent.getStringExtra("Horario");
        this.user = intent.getStringExtra("User");
        setContentView(R.layout.activity_horario_seleccionado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        final CardView newAct = (CardView) findViewById(R.id.newactivity);
        newAct.setVisibility(View.GONE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newAct.setVisibility(View.VISIBLE);
            }
        });

        ImageView close = (ImageView) findViewById(R.id.imageClose);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                newAct.setVisibility(View.GONE);
            }
        });

        Spinner daySpinner = (Spinner) findViewById(R.id.daySpinner);
        daySpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, diasCom));
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });

        crearAct = (ImageView)findViewById(R.id.imageButtonCrearHorario);
        crearAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Actividad creada correctamente",Toast.LENGTH_SHORT).show();
                closeKeyboard();
                newAct.setVisibility(View.GONE);
            }
        });

        txtNombre=(TextView)findViewById(R.id.nombreH);
        txtNombre.setText(nombreH);
        setTitle(nombreH);
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

    private void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if(view != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}