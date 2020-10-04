package com.project.app.aplicacionmovil;

import java.util.HashMap;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.prefs.Preferences;

import kotlin.Suppress;

public class Registrarse extends AppCompatActivity {
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Button finRegistrarse;
    private EditText txtBoxUsuario;
    private EditText txtBoxContra;
    private EditText txtBoxNombre;
    private EditText txtBoxEmail;
    private EditText txtBoxCumple;
    private EditText txtBoxConfirm;
    private static String Kusuario = "";
    private static String Kcontra="";
    private static String Knombre="";
    private static String Kemail="";
    private static String Kcumple="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarse_layout);
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        finRegistrarse = (Button) findViewById(R.id.finRegistrarse);
        txtBoxUsuario = (EditText) findViewById(R.id.txtBoxUsuario);
        txtBoxContra = (EditText) findViewById(R.id.txtBoxContra);
        txtBoxNombre = (EditText) findViewById(R.id.txtBoxNombre);
        txtBoxEmail = (EditText) findViewById(R.id.txtBoxEmail);
        txtBoxCumple = (EditText) findViewById(R.id.txtBoxCumple);
        txtBoxConfirm = (EditText) findViewById(R.id.txtBoxConfirm);


        finRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String contra = txtBoxContra.getText().toString();
                String confirm = txtBoxConfirm.getText().toString();

                if(contra.equals(confirm)){
                    HashMap<String, String> data = new HashMap<String, String>();
                    data.put("user", txtBoxUsuario.getText().toString());
                    data.put("email", txtBoxEmail.getText().toString());
                    data.put("password", txtBoxContra.getText().toString());
                    data.put("name", txtBoxNombre.getText().toString());
                    data.put("birthday", txtBoxCumple.getText().toString());
                    db.collection("Users").document(txtBoxUsuario.getText().toString()).set(data);
                    openMenuPrincipal();
                    Toast.makeText(getApplicationContext(), "Registrado Correctamente", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Contraseña incorrecta, intente de nuevo", Toast.LENGTH_LONG).show();
                }




               /** if (txtBoxUsuario.getText().toString().matches("") || txtBoxContra.getText().toString().matches("") || txtBoxNombre.getText().toString().matches("") ||
                        txtBoxEmail.getText().toString().matches("") || txtBoxCumple.getText().toString().matches("")) {
                    Toast.makeText(Registrarse.this, "Por favor llenar todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Kusuario",txtBoxUsuario.getText().toString());
                    editor.putString("Kcontra",txtBoxContra.getText().toString());
                    editor.putString("Knombre",txtBoxNombre.getText().toString());
                    editor.putString("Kemail",txtBoxEmail.getText().toString());
                    editor.putString("Kcumple",txtBoxCumple.getText().toString());
                    editor.commit();

                    String usu = pref.getString("Kusuario","");
                    String con = pref.getString("Kcontra","");
                    String nom = pref.getString("Knombre","");
                    String em = pref.getString("Kemail","");
                    String cum = pref.getString("Kcumple","");

                    if(usu != "" && con !="" && nom !="" && em !="" && cum !="")
                    {
                        showAlert("Bienvenido","Usuario registrado correctamente"+usu);
                        System.out.println(usu+" "+con+" "+nom+" "+em+" "+cum);
                        openMenuPrincipal();
                    }
                    else
                    {
                        System.out.println(usu+" "+con+" "+nom+" "+em+" "+cum);
                        Toast.makeText(Registrarse.this, "Ocurrió un error", Toast.LENGTH_LONG).show();
                    }

                }*/
            }
        });
    }
    public void openMenuPrincipal()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void showAlert(String titulo,String message)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(titulo);
        alert.setMessage(message);
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
