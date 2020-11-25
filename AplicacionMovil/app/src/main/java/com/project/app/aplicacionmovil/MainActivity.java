package com.project.app.aplicacionmovil;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.app.aplicacionmovil.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observer;

import kotlin.jvm.internal.Intrinsics;

public class MainActivity extends AppCompatActivity{
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "";
    private Button buttonLogin;
    private Button buttonRegistrarse;
    private EditText txtUsuario;
    private EditText txtContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        setup();

        //setup


        buttonRegistrarse = (Button) findViewById(R.id.registrarse);
        buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean con = conexion();
                if(con)
                {
                    openActividad(2);
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, Internet.class);
                    startActivity(intent);
                }
            }
        });


    }

    public boolean conexion()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
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

    public void openActividad(int opcion)
    {

        if(opcion == 1) {
            Intent intent = new Intent(this, MenuP.class);
            intent.putExtra("User", txtUsuario.getText().toString());
            startActivity(intent);
            finish();
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

                boolean con = conexion();
                if(con)
                {
                    final String[] userProv = new String[2];
                    final String[] passProv = new String[1];

                    final String usuario = txtUsuario.getText().toString();
                    final String contrasena = txtContra.getText().toString();



                    db.collection("Users").document(usuario).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(documentSnapshot.exists() == true){
                                userProv[0] = (String) documentSnapshot.get("user");
                                userProv[1] = (String) documentSnapshot.get("name");
                                passProv[0] = (String) documentSnapshot.get("password");


                                if(usuario.equals(userProv[0]) && contrasena.equals(passProv[0])){
                                    openActividad(1);
                                }

                                Toast.makeText(getApplicationContext(), "Bienvenido " +userProv[1], Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "No existe", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                    /**if(!usuario.equals("") && !contrasena.equals("")) {
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
                     }*/
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, Internet.class);
                    startActivity(intent);
                }


            }
        });
    }


}

