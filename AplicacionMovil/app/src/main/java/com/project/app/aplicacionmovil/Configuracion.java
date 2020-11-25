package com.project.app.aplicacionmovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Configuracion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private String user;
    private NavigationView navigationView;
    private ImageView abrirMenu;
    private ImageView cusuario, ccontra, ccorreo, cborrar;
    private Button btnusuario, btncontra,btncorreo, btnborrar;
    private Button canU, conU, canCon, conCon, canCor, conCor, canBor, conBor;
    private CardView  cdcontra, cdcorreo, cdborrar;
    private ConstraintLayout constraintEdit;
    private TextView nom, txtNewContra, txtConfirmNewPass, txtViejaContra, txtEmail, txtConfContra;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_config);

        final Intent intent = getIntent();
        this.user = intent.getStringExtra("User");
        nom=findViewById(R.id.nombreConfig);
        nom.setText(user);

        txtNewContra = (TextView)findViewById(R.id.txtViewCPassword);
        txtConfirmNewPass = (TextView)findViewById(R.id.txtViewCNPassword);
        txtViejaContra = (TextView)findViewById(R.id.txtViewConfirmarPP);
        txtEmail = (TextView)findViewById(R.id.txtViewCEmail);
        txtConfContra = (TextView)findViewById(R.id.txtViewConfirmarPE);


        navigationView = (NavigationView)findViewById(R.id.nav_config);
        navigationView.setItemIconTintList(null);
        abrirMenu = (ImageView)findViewById(R.id.abrirMenuConfig);
        abrirMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout navDrawer = findViewById(R.id.drawer_layout);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(GravityCompat.START)) navDrawer.openDrawer(GravityCompat.START);
                else navDrawer.closeDrawer(GravityCompat.END);
            }
        });

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        constraintEdit=(ConstraintLayout)findViewById(R.id.constraintEdit);
        constraintEdit.setVisibility(View.VISIBLE);
        cdcontra = (CardView)findViewById(R.id.changePassword);
        cdcontra.setVisibility(View.GONE);
        cdcorreo = (CardView)findViewById(R.id.changeEmail);
        cdcorreo.setVisibility(View.GONE);
        cdborrar = (CardView)findViewById(R.id.DeleteAccount);
        cdborrar.setVisibility(View.GONE);


        ccontra=findViewById(R.id.imageCloseP);
        ccontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cdcontra.setVisibility(View.GONE);
                cdcorreo.setVisibility(View.GONE);
                cdborrar.setVisibility(View.GONE);
                constraintEdit.setVisibility(View.VISIBLE);
            }
        });

        ccorreo=findViewById(R.id.imageCloseEmail);
        ccorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cdcontra.setVisibility(View.GONE);
                cdcorreo.setVisibility(View.GONE);
                cdborrar.setVisibility(View.GONE);
                constraintEdit.setVisibility(View.VISIBLE);
            }
        });

        cborrar=findViewById(R.id.imageCloseDelete);
        cborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cdcontra.setVisibility(View.GONE);
                cdcorreo.setVisibility(View.GONE);
                cdborrar.setVisibility(View.GONE);
                constraintEdit.setVisibility(View.VISIBLE);
            }
        });


        canCon= findViewById(R.id.btnCancelP);
        canCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdcontra.setVisibility(View.GONE);
                cdcorreo.setVisibility(View.GONE);
                cdborrar.setVisibility(View.GONE);
                constraintEdit.setVisibility(View.VISIBLE);
            }
        });

        conCon = findViewById(R.id.btnChangeP);
        conCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                if(txtNewContra.getText().toString().equals(txtConfirmNewPass.getText().toString())){
                    db.collection("Users").document(user).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(documentSnapshot.exists() == true){
                                String contra = (String) documentSnapshot.get("password");
                                if(contra.equals(txtViejaContra.getText().toString())){
                                    db.collection("Users").document(user).update("password", txtNewContra.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getApplicationContext(), "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show();
                                            cdcontra.setVisibility(View.GONE);
                                            cdcorreo.setVisibility(View.GONE);
                                            cdborrar.setVisibility(View.GONE);
                                            constraintEdit.setVisibility(View.VISIBLE);
                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Parece que la contraseña anterior no coincide con la ingresada, intenta de nuevo!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "El usuario ingresado no existe, intenta de nuevo!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(getApplicationContext(), "Parece que las contraseñas ingresadas no coinciden, intenta de nuevo!", Toast.LENGTH_SHORT).show();
                }



            }
        });

        canCor=findViewById(R.id.btnCancelE);
        canCor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cdcontra.setVisibility(View.GONE);
                cdcorreo.setVisibility(View.GONE);
                cdborrar.setVisibility(View.GONE);
                constraintEdit.setVisibility(View.VISIBLE);
            }
        });

        conCor=findViewById(R.id.btnChangeE);
        conCor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Users").document(user).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists() == true){
                            String contra = (String) documentSnapshot.get("password");
                            if(contra.equals(txtConfContra.getText().toString())){
                                db.collection("Users").document(user).update("email", txtEmail.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getApplicationContext(), "Email cambiado exitosamente", Toast.LENGTH_SHORT).show();
                                        cdcontra.setVisibility(View.GONE);
                                        cdcorreo.setVisibility(View.GONE);
                                        cdborrar.setVisibility(View.GONE);
                                        constraintEdit.setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Parece que la contraseña ingresada es incorrecta, intenta de nuevo!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "El usuario ingresado no existe, intenta de nuevo!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        canBor=findViewById(R.id.btnCancelD);
        canBor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdcontra.setVisibility(View.GONE);
                cdcorreo.setVisibility(View.GONE);
                cdborrar.setVisibility(View.GONE);
                constraintEdit.setVisibility(View.VISIBLE);
            }
        });

        conBor=findViewById(R.id.btnAcceptD);
        conBor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Users").document(user).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(), "Cuenta borrada exitosamente!", Toast.LENGTH_SHORT).show();

                    }
                });
                cdcontra.setVisibility(View.GONE);
                cdcorreo.setVisibility(View.GONE);
                cdborrar.setVisibility(View.GONE);
                constraintEdit.setVisibility(View.VISIBLE);
            }
        });

        btncontra=(Button)findViewById(R.id.btnCPassword);
        btncontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdcontra.setVisibility(View.VISIBLE);
                cdcorreo.setVisibility(View.GONE);
                cdborrar.setVisibility(View.GONE);
                constraintEdit.setVisibility(View.GONE);
            }
        });

        btncorreo = (Button)findViewById(R.id.btnCEmail);
        btncorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdcontra.setVisibility(View.GONE);
                cdcorreo.setVisibility(View.VISIBLE);
                cdborrar.setVisibility(View.GONE);
                constraintEdit.setVisibility(View.GONE);
            }
        });

        btnborrar = (Button)findViewById(R.id.btnDeleteC);
        btnborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdcontra.setVisibility(View.GONE);
                cdcorreo.setVisibility(View.GONE);
                cdborrar.setVisibility(View.VISIBLE);
                constraintEdit.setVisibility(View.GONE);
            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navConfig) {
            Toast.makeText(getApplicationContext(), "Ya te encuentras en Configuracion", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.navHome)
        {
            Intent intent = new Intent(this, MenuP.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }
        return true;
    }
}
