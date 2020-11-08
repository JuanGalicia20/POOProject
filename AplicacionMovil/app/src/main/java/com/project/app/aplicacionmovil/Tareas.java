package com.project.app.aplicacionmovil;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Tareas extends AppCompatActivity {

    private String descripcion;
    private String titulo;
    private boolean estado; //finalizado o pendiente
    private FloatingActionButton btnNuevaTarea;
    private ArrayList<ObjetoTareas> listaTareas;
    private EditText titleTarea;
    private EditText editDTarea;
    private ImageButton iBtnTarea;
    private CardView nuevaTareaCard;
    private ImageView imageClose;
    private RecyclerView recycler;
    private AdaptadorTareas nuevoAdaptador;

    protected void onCreate(Bundle savedInstanceState ){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);
        recycler = (RecyclerView) findViewById(R.id.recyclerTareas);
        listaTareas = new ArrayList<ObjetoTareas>();
        recycler.setLayoutManager(new GridLayoutManager(this,2));
        nuevoAdaptador = new AdaptadorTareas(listaTareas);
        recycler.setAdapter(nuevoAdaptador);
        titleTarea = (EditText) findViewById(R.id.titleTarea);
        editDTarea = (EditText) findViewById(R.id.editDTarea);
        iBtnTarea = (ImageButton) findViewById(R.id.iBtnTarea);
        nuevaTareaCard = (CardView) findViewById(R.id.nuevaTareaCard);
        nuevaTareaCard.setVisibility(View.GONE);
        imageClose = (ImageView) findViewById(R.id.imageClose);
        btnNuevaTarea = (FloatingActionButton) findViewById(R.id.actionBtnTarea);
        btnNuevaTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuevaTareaCard.setVisibility(View.VISIBLE);
            }
        });

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuevaTareaCard.setVisibility(View.GONE);
            }
        });

        iBtnTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulo = titleTarea.getText().toString();
                descripcion = editDTarea.getText().toString();
                nuevaTareaCard.setVisibility(View.GONE);
            }
        });
        nuevasTareas();
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

    private void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if(view != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    private void nuevasTareas(){
        listaTareas.add(new ObjetoTareas("Ejercicio", "3 EJERCICIOS DE CALCULO"));
        listaTareas.add(new ObjetoTareas("Almuerzos", "COMIDAAS"));
        listaTareas.add(new ObjetoTareas("Tb4tf", "3 bt4efbte"));
        listaTareas.add(new ObjetoTareas("reomp", "ve0ijmbi0´qepbnvm0i´3pqr"));
        listaTareas.add(new ObjetoTareas("Ej", "3 ECALCULO"));
    }

}
