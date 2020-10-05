package com.project.app.aplicacionmovil;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.widget.Toast;

public class consejos_manager extends AppCompatActivity {
    private final static FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos_manager);

        final CardView est = (CardView) findViewById(R.id.Estudiar);
        est.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConsejos();
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    public void openConsejos() {
        Intent intent = new Intent(this, activity_consejos_respuestas.class);
        startActivity(intent);
    }
}