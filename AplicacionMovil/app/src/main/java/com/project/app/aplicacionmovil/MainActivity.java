package com.project.app.aplicacionmovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.project.app.aplicacionmovil.R;

public class MainActivity extends AppCompatActivity {
    private Button buttonLogin;
    private Button buttonRegistrarse;
    private EditText txtUsuario;
    private EditText txtContra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup
        setup();


        buttonRegistrarse = (Button) findViewById(R.id.registrarse);
        buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActividad(2);
            }
        });


    }

    public void openActividad(int opcion)
    {
        if(opcion == 1) {
            Intent intent = new Intent(this, MenuP.class);
            startActivity(intent);
        }
        else if(opcion==2)
        {
            Intent intent = new Intent(this, Registrarse.class);
            startActivity(intent);
        }
    }

    public void setup()
    {
        String title = "Autenticacion";
        txtUsuario = (EditText)findViewById(R.id.edittxtUs);
        txtContra = (EditText)findViewById(R.id.edittxtContra);
        buttonLogin = (Button) findViewById(R.id.ingresar);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = txtUsuario.getText().toString();
                String contrasena = txtContra.getText().toString();
                if(!usuario.equals("") && !contrasena.equals("")) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(usuario,contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                            }
                            else
                            {

                            }
                        }
                    });
                    openActividad(1);
                }
            }
        });
    }
}

