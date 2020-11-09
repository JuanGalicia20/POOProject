package com.project.app.aplicacionmovil;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class Tareas extends AppCompatActivity {

    private String descripcion;
    private String user;
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
        Intent intent = getIntent();
        user = intent.getStringExtra("User");

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
                addTarea();

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

    private void addTarea(){
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        HashMap<String, String> data = new HashMap<>();

        data.put("name", titleTarea.getText().toString() );
        data.put("description", editDTarea.getText().toString());


        db.collection("Users").document(user).collection("Tareas").document(titleTarea.getText().toString()).set(data);
        finish();
        startActivity(getIntent());
    }

    private void nuevasTareas(){

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final ArrayList<ObjetoTareas> temp = new ArrayList<>();
        db.collection("Users").document(user).collection("Tareas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                recycler = (RecyclerView) findViewById(R.id.recyclerTareas);
                recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                final ArrayList<ObjetoTareas> temp2 = new ArrayList<>();
                listaTareas = new ArrayList<ObjetoTareas>();
                if(task.isSuccessful()){

                    for(DocumentSnapshot doc : task.getResult().getDocuments()) {

                        String[] info = new String[2];
                        info[0] = doc.get("name").toString();
                        info[1] = doc.get("description").toString();

                        ObjetoTareas temp1 = new ObjetoTareas(info[0], info[1]);
                        listaTareas.add(temp1);


                        // listaTareas.add(new ObjetoTareas(info[0], info[1]));

                    }

                    nuevoAdaptador = new AdaptadorTareas(listaTareas);
                    recycler.setAdapter(nuevoAdaptador);


                }

                else{
                    Toast.makeText(getApplicationContext(), "Parece que hubo un error, intenta de nuevo.", Toast.LENGTH_LONG).show();
                }



            }

        });

        /*listaTareas.add(new ObjetoTareas("Ejercicio", "3 EJERCICIOS DE CALCULO"));
                        listaTareas.add(new ObjetoTareas("Almuerzos", "COMIDAAS"));
                        listaTareas.add(new ObjetoTareas("Tb4tf", "3 bt4efbte"));
                        listaTareas.add(new ObjetoTareas("reomp", "ve0ijmbi0´qepbnvm0i´3pqr"));
                        listaTareas.add(new ObjetoTareas("Ej", "3 ECALCULO"));*/




    }



}
