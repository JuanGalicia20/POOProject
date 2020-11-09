package com.project.app.aplicacionmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class Internet extends AppCompatActivity {

    private LinearLayout connected;
    private LinearLayout disconnected;
    private Button tryagain;


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
            Toast.makeText(this, "No está conectado a internet", Toast.LENGTH_SHORT).show();

            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_internet);

        connected = (LinearLayout) findViewById(R.id.connected);
        disconnected = (LinearLayout) findViewById(R.id.disconnected);
        tryagain = (Button) findViewById(R.id.tryagain);

        connected.setVisibility(View.GONE);
        disconnected.setVisibility(View.VISIBLE);
        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean con = conexion();
                if(con)
                {
                    connected.setVisibility(View.VISIBLE);
                    disconnected.setVisibility(View.GONE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Internet.this, MainActivity.class);
                            startActivity(intent);
                        }
                    },1000);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Sigues desconectado, verifica tu conexion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}