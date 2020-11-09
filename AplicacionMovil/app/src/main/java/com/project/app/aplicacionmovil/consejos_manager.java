package com.project.app.aplicacionmovil;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
                openConsejos("Estudiar");
            }
        });

        final CardView onl = (CardView) findViewById(R.id.Online);
        onl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConsejos("Online");
            }
        });

        final CardView apun = (CardView) findViewById(R.id.Apuntes);
        apun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConsejos("Apuntes");
            }
        });

        final CardView exam = (CardView) findViewById(R.id.Examenes);
        exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConsejos("Examenes");
            }
        });

        final CardView tareas = (CardView) findViewById(R.id.Tareas);
        tareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConsejos("Tareas");
            }
        });

        final CardView team = (CardView) findViewById(R.id.Team);
        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConsejos("Team");
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

    public void openConsejos(String type) {
        boolean con = conexion();
        if(con)
        {
            Intent intent = new Intent(this, activity_consejos_respuestas.class);
            intent.putExtra("tipo", type);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(consejos_manager.this, Internet.class);
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