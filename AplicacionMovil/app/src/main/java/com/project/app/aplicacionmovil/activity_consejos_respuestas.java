package com.project.app.aplicacionmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class activity_consejos_respuestas extends AppCompatActivity {
    private String type;

    private final static FirebaseFirestore db = FirebaseFirestore.getInstance();



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String type1;

        Intent intent = getIntent();
        this.type = intent.getStringExtra("tipo");

        setContentView(R.layout.activity_consejos_respuestas);
        final ImageView imagen = (ImageView)findViewById(R.id.imagenP);
        final TextView titulo = (TextView)findViewById(R.id.txtTitulo);
        final TextView pregunta1 = (TextView)findViewById(R.id.txtPregunta1);
        final TextView r1 = (TextView)findViewById(R.id.txtRespuesta1);
        final TextView pregunta2 = (TextView)findViewById(R.id.txtPregunta2);
        final TextView r2 = (TextView)findViewById(R.id.txtRespuesta2);
        final TextView pregunta3 = (TextView)findViewById(R.id.txtPregunta2);//cambiar target
        final TextView r3 = (TextView)findViewById(R.id.txtRespuesta2);//cambiar target

        if(type.equals("Estudiar")){
            type1 = "Study";
            getType(pregunta1, r1, pregunta2, r2, pregunta3, r3, type1);
            titulo.setText(type);
            imagen.setImageResource(R.drawable.catconsejos1);
        }
        else if (type.equals("Online")){
            type1 = "Clases en linea";
            getType(pregunta1, r1, pregunta2, r2, pregunta3, r3, type);
            titulo.setText(type1);
            imagen.setImageResource(R.drawable.catconsejos2);
        }
        else if (type.equals("Apuntes")){
            type1 = "Notes";
            getType(pregunta1, r1, pregunta2, r2, pregunta3, r3, type1);
            titulo.setText(type);
            imagen.setImageResource(R.drawable.catconsejo3);
        }
        else if (type.equals("Examenes")){
            type1 = "Exams";
            getType(pregunta1, r1, pregunta2, r2, pregunta3, r3, type1);
            titulo.setText(type);
            imagen.setImageResource(R.drawable.catconsejo4);
        }
        else if (type.equals("Tareas")){
            type1 = "Homework";
            getType(pregunta1, r1, pregunta2, r2, pregunta3, r3, type1);
            titulo.setText(type);
            imagen.setImageResource(R.drawable.catconsejo5);
        }
        else if (type.equals("Team")){
            type1 = "Trabajo en Equipo";
            getType(pregunta1, r1, pregunta2, r2, pregunta3, r3, type);
            titulo.setText(type1);
            imagen.setImageResource(R.drawable.catconsejo6);
        }
        else {
            Toast.makeText(getApplicationContext(), "Failed, try again ", Toast.LENGTH_SHORT).show();
        }

    }


    private void getType(final TextView txt1, final TextView txtR1, final TextView txt2, final TextView txtR2, final TextView txt3, final TextView txtR3, String type){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final ArrayList<String> preguntas = new ArrayList<String>();
        final ArrayList<String> respuestas = new ArrayList<String>();
        CollectionReference ConsejosReference = db.collection("Consejos").document(type).collection("preguntas");

        ConsejosReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot doc : task.getResult().getDocuments()){

                           preguntas.add((String)(doc.get("pregunta")));
                           respuestas.add((String)(doc.get("respuesta_1")));

                    }
                    txt1.setText((preguntas.get(0)));
                    txtR1.setText(respuestas.get(0));
                    txt2.setText(preguntas.get(1));
                    txtR2.setText(respuestas.get(1));
                    txt3.setText(preguntas.get(2));
                    txtR3.setText(respuestas.get(2));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Failed, try again ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    }




