package com.project.app.aplicacionmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;


public class Horarios extends AppCompatActivity {
    private ArrayList<Horario_class> horarios = new ArrayList<>();

    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<String> names = new ArrayList<String>();
    private String user;
    private CardView anadir;
    private Button newButton;
    private CardView formulario;
    private ImageView close;
    private ImageButton crearHorario;
    private RadioGroup opcion;
    private RadioButton seleccionado;
    private EditText nombreHorario;
    private int intRadioID = 2131362098;
    private int contador=-1;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
        Intent intent = getIntent();
        this.user = intent.getStringExtra("User");
        createHorario();

        /**
         * Cambiar para objeto tipo user
         */



        nombreHorario = (EditText)findViewById(R.id.txtViewNHorario);
        opcion = (RadioGroup) findViewById(R.id.radioGroup);
        formulario = (CardView) findViewById(R.id.nuevohorario);
        formulario.setVisibility(View.GONE);
        anadir = (CardView) findViewById(R.id.anadir);
        this.anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formulario.setVisibility(View.VISIBLE);
            }
        });

        close = (ImageView) findViewById(R.id.imageClose);
        this.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                formulario.setVisibility(View.GONE);
            }
        });


        crearHorario = (ImageButton)findViewById(R.id.imageButtonCrearHorario);
        crearHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean con = conexion();
                if(con)
                {
                    closeKeyboard();
                    addHorario();
                    formulario.setVisibility(View.GONE);
                    nombreHorario.setText("");
                }
                else
                {
                    Intent intent = new Intent(Horarios.this, Internet.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void addHorario()
    {
        ArrayList<Horario_dia> dias_horario = new ArrayList<>();
        contador+=1;
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        String nombre = nombreHorario.getText().toString();

           String radioSelected;
        if(intRadioID == 2131362098){
            radioSelected = "Weekend";
        }
        else{
            radioSelected = "Week";
        }
        // Key : value
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("name", nombreHorario.getText().toString() );
        data.put("length", radioSelected);
        db.collection("Users").document(user).collection("Horarios").document(nombre).set(data);

        if(intRadioID == 2131362098){
            HashMap<String, String> data2 = new HashMap<String, String>();
            data.put("0:00-0:00", "Main" );
            Horario_dia Lunes = new Horario_dia("Lunes");
            Horario_dia Martes = new Horario_dia("Martes");
            Horario_dia Miercoles = new Horario_dia("Miercoles");
            Horario_dia Jueves = new Horario_dia("Jueves");
            Horario_dia Viernes = new Horario_dia("Viernes");
            Horario_dia Sabado = new Horario_dia("Sabado");
            Horario_dia Domingo = new Horario_dia("Domingo");

            db.collection("Users").document(user).collection("Horarios").document(nombre).collection("Lunes").document("Hora0").set(data2);
            db.collection("Users").document(user).collection("Horarios").document(nombre).collection("Martes").document("Hora0").set(data2);
            db.collection("Users").document(user).collection("Horarios").document(nombre).collection("Miercoles").document("Hora0").set(data2);
            db.collection("Users").document(user).collection("Horarios").document(nombre).collection("Jueves").document("Hora0").set(data2);
            db.collection("Users").document(user).collection("Horarios").document(nombre).collection("Viernes").document("Hora0").set(data2);
            db.collection("Users").document(user).collection("Horarios").document(nombre).collection("Sabado").document("Hora0").set(data2);
            db.collection("Users").document(user).collection("Horarios").document(nombre).collection("Domingo").document("Hora0").set(data2);

            dias_horario.add(Lunes);
            dias_horario.add(Martes);
            dias_horario.add(Miercoles);
            dias_horario.add(Jueves);
            dias_horario.add(Viernes);
            dias_horario.add(Sabado);
            dias_horario.add(Domingo);

        }
        else{
            HashMap<String, String> data2 = new HashMap<String, String>();
            data.put("0:00-0:00", "Main" );
            Horario_dia Lunes = new Horario_dia("Lunes");
            Horario_dia Martes = new Horario_dia("Martes");
            Horario_dia Miercoles = new Horario_dia("Miercoles");
            Horario_dia Jueves = new Horario_dia("Jueves");
            Horario_dia Viernes = new Horario_dia("Viernes");

            db.collection("Users").document(user).collection("Horarios").document(nombre).collection("Lunes").document("Hora0").set(data2);
            db.collection("Users").document(user).collection("Horarios").document(nombre).collection("Martes").document("Hora0").set(data2);
            db.collection("Users").document(user).collection("Horarios").document(nombre).collection("Miercoles").document("Hora0").set(data2);
            db.collection("Users").document(user).collection("Horarios").document(nombre).collection("Jueves").document("Hora0").set(data2);
            db.collection("Users").document(user).collection("Horarios").document(nombre).collection("Viernes").document("Hora0").set(data2);

            dias_horario.add(Lunes);
            dias_horario.add(Martes);
            dias_horario.add(Miercoles);
            dias_horario.add(Jueves);
            dias_horario.add(Viernes);

        }
        System.out.println(dias_horario.get(0).getName());
        System.out.println(nombre);
        System.out.println(radioSelected);
        Horario_class hor = new Horario_class(dias_horario, nombre, radioSelected);
        this.horarios.add(hor);

        LinearLayout layout = (LinearLayout) findViewById(R.id.rootlayout);
        newButton = new Button(this);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "noooooooo", Toast.LENGTH_SHORT).show();
            }
        });

        newButton.setId(contador);
        newButton.setTag(nombreHorario.getText().toString());
        newButton.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        newButton.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        newButton.setBackgroundResource(R.drawable.horarioback);
        newButton.setTextSize(20);


        /*
        <Button
                android:id="@+id/button1"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginLeft="16dp"
                android:layout_marginRight="16dp"
                android:layout_marginBottom="16dp"
                android:background="@drawable/horarioback"
                android:fontFamily="@font/amaranth"
                android:padding="10dp"
                android:text="Buttons"
                android:textColor="@android:color/secondary_text_light"
                android:textSize="25dp" />
         */
        newButton.setText(nombre);
        layout.addView(newButton);
        Toast.makeText(this,"Nuevo horario Creado exitosamente",Toast.LENGTH_SHORT).show();
        finish();
        startActivity(getIntent());
    }

    public void createHorario(){
        boolean con = conexion();
        if(con)
        {

            final LinearLayout layout = (LinearLayout) findViewById(R.id.rootlayout);

            final ArrayList<String> names = new ArrayList<String>();

            CollectionReference ConsejosReference = db.collection("Users").document(user).collection("Horarios");
            ConsejosReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc : task.getResult().getDocuments()){

                            names.add((String)(doc.getId()));

                        }
                        for (int i = 0; i < names.size(); i++){
                            newButton = new Button(getApplicationContext());
                            newButton.setTag(names.get(i));
                            newButton.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
                            newButton.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                            newButton.setBackgroundResource(R.drawable.horarioback);
                            newButton.setTextSize(20);
                            newButton.setText(names.get(i));
                            final int finalI = i;
                            newButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    openHorarioSeleccionado(names.get(finalI));
                                }
                            });
                            layout.addView(newButton);

                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Failed, try again ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else
        {
            Intent intent = new Intent(Horarios.this, Internet.class);
            startActivity(intent);
        }

    }


    public void checkButton(View v)
    {
        int radioId = opcion.getCheckedRadioButtonId();
        intRadioID = radioId;
        seleccionado = (RadioButton)findViewById(radioId);
        Toast.makeText(this,"Selected: "+seleccionado.getText() + radioId,Toast.LENGTH_SHORT).show();
    }

    public void openHorarioSeleccionado(String nombreH) {
        Intent intent = new Intent(this, HorarioSeleccionado.class);
        intent.putExtra("User" ,user);
        intent.putExtra("Horario",nombreH);
        startActivity(intent);
    }

    private void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if(view != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
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