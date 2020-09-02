package com.project.app.aplicacionmovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.project.app.aplicacionmovil.R;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText txtUsuario;
    private EditText txtContra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario = (EditText)findViewById(R.id.edittxtUs);
        txtContra = (EditText)findViewById(R.id.edittxtContra);
        button = (Button) findViewById(R.id.ingresar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = txtUsuario.getText().toString();
                String contrasena = txtContra.getText().toString();
                if(usuario.equals("Juancho") && contrasena.equals("UVG2020POO")) {
                    openMenuPrincipal();
                }
            }
        });
    }

    public void openMenuPrincipal()
    {
        Intent intent = new Intent(this, MenuP.class);
        startActivity(intent);
    }
}

