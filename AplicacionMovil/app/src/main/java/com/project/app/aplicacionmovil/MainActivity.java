package com.project.app.aplicacionmovil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
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

public class MainActivity extends AppCompatActivity {
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button buttonLogin;
    private Button buttonRegistrarse;
    private EditText txtUsuario;
    private EditText txtContra;
    private Bitmap profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
        });
    }
}

