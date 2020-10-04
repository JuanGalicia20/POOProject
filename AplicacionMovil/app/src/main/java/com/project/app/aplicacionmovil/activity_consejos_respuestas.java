package com.project.app.aplicacionmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class activity_consejos_respuestas extends AppCompatActivity {
    private final static FirebaseFirestore db = FirebaseFirestore.getInstance();

    private EditText txtPregunta;
    private EditText txtRespuesta1;
    private EditText txtRespuesta2;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        txtPregunta = (EditText) findViewById(R.id.txtPregunta1);

        super.onCreate(savedInstanceState);
        final String[] consejoInfo = new String[3];
        Toast.makeText(getApplicationContext(), "Pregunta Estudio", Toast.LENGTH_SHORT).show();

        /**db.collection("Consejos").document("Study").collection("preguntas").document("01").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                TextView txtTitulo = (TextView) findViewById(R.id.txttitulo);
                TextView txtPregunta = (TextView) findViewById(R.id.txtPregunta1);

                TextView txtRespuesta = (TextView) findViewById(R.id.txtRepuesta1);
                TextView txtRespuesta1 = (TextView) findViewById(R.id.txtRespuesta2);

                consejoInfo[0] = (String) documentSnapshot.get("pregunta");
                consejoInfo[1] = (String) documentSnapshot.get("respuesta_1");
                consejoInfo[2] = (String) documentSnapshot.get("respuesta_2");

                Toast.makeText(getApplicationContext(), "Pregunta "+consejoInfo[0], Toast.LENGTH_SHORT).show();

                txtPregunta.setText(consejoInfo[0]);
                txtRespuesta.setText(consejoInfo[1]);
                txtRespuesta1.setText(consejoInfo[2]);


        });*/
    }

}