package com.project.app.aplicacionmovil;

import android.app.Activity;
import android.app.AuthenticationRequiredException;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.api.Distribution;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HorarioSeleccionado extends AppCompatActivity {
    private final FirebaseFirestore db1 = FirebaseFirestore.getInstance();
    private ImageView lunes;
    private ImageView martes;
    private ImageView miercoles;
    private ImageView jueves;
    private ImageView viernes;
    private ImageView sabado;
    private ImageView domingo;

    private LinearLayout lunesLl;
    private LinearLayout martesLl;
    private LinearLayout miercolesLl;
    private LinearLayout juevesLl;
    private LinearLayout viernesLl;
    private LinearLayout sabadoLl;
    private LinearLayout domingoLl;
    private TextView txtNombre;
    private TextView txtNomAct;
    private String nombreH;
    private String user;
    private String[] diasCom={"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
    private String[] diaslv={"Lunes","Martes","Miercoles","Jueves","Viernes"};
    private ImageView crearAct;

    private TextView txtDesc;
    private TextView txtInicio;
    private TextView txtFin;
    private Button iniciar;
    private Button fin;
    private int t1H,t1M,t2H,t2M;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.nombreH = intent.getStringExtra("Horario");
        this.user = intent.getStringExtra("User");
        setContentView(R.layout.activity_horario_seleccionado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        cargarActividades();

        final CardView newAct = (CardView) findViewById(R.id.newactivity);
        newAct.setVisibility(View.GONE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newAct.setVisibility(View.VISIBLE);
            }
        });

        ImageView close = (ImageView) findViewById(R.id.imageClose);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                newAct.setVisibility(View.GONE);
            }
        });
        final Spinner daySpinner = (Spinner) findViewById(R.id.daySpinner);
        String length;

        db1.collection("Users").document(user).collection("Horarios").document(nombreH).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists() == true){
                    sabadoLl = (LinearLayout) findViewById(R.id.linearSabado);
                    domingoLl = (LinearLayout) findViewById(R.id.linearDomingo);


                    if(((String)documentSnapshot.get("length")).equals("Weekend")){
                        daySpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, diasCom));
                    }
                    else if (((String) documentSnapshot.get("length")).equals("Week")){
                        daySpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, diaslv));
                        sabadoLl.setVisibility(View.GONE);
                        domingoLl.setVisibility(View.GONE);
                    }
                }
            }
        });

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });


        txtDesc = (TextView)findViewById(R.id.txtDescripcion);
        txtNomAct = (TextView)findViewById(R.id.txtNomActividad);
        txtNombre=(TextView)findViewById(R.id.nombreH);
        txtNombre.setText(nombreH);
        setTitle(nombreH);
        lunes = (ImageView)findViewById(R.id.imglunes);
        martes = (ImageView)findViewById(R.id.imgmartes);
        miercoles = (ImageView)findViewById(R.id.imgmiercoles);
        jueves = (ImageView)findViewById(R.id.imgjueves);
        viernes = (ImageView)findViewById(R.id.imgviernes);
        sabado = (ImageView)findViewById(R.id.imgsabado);
        domingo = (ImageView)findViewById(R.id.imgdomingo);

        lunesLl = (LinearLayout)findViewById(R.id.nuevosLunes);
        martesLl = (LinearLayout)findViewById(R.id.nuevosMartes);
        miercolesLl=(LinearLayout)findViewById(R.id.nuevosMiercoles);
        juevesLl=(LinearLayout)findViewById(R.id.nuevosJueves);
        viernesLl=(LinearLayout)findViewById(R.id.nuevosViernes);
        sabadoLl=(LinearLayout)findViewById(R.id.nuevosSabado);
        domingoLl=(LinearLayout)findViewById(R.id.nuevosDomingo);

        lunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        martes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        miercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        jueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        viernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sabado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        domingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        final TextView txtInicio = (TextView)findViewById(R.id.txtinicio);
        final TextView txtFin = (TextView)findViewById(R.id.txtfin);
        Button iniciar = (Button) findViewById(R.id.inicio);
        Button fin = (Button)findViewById(R.id.fin);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        HorarioSeleccionado.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1H = hourOfDay;
                                t1M = minute;

                                String time = t1H + ":" + t1M;
                                SimpleDateFormat f24Horas = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Horas.parse(time);
                                    SimpleDateFormat f12Horas = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    txtInicio.setText(f12Horas.format(date));

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false

                );

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t1H,t1M);
                timePickerDialog.show();
            }
        });

        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        HorarioSeleccionado.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t2H = hourOfDay;
                                t2M = minute;

                                String time = t2H + ":" + t2M;
                                SimpleDateFormat f24Horas = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Horas.parse(time);
                                    SimpleDateFormat f12Horas = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    txtFin.setText(f12Horas.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t1H,t1M);
                timePickerDialog.show();
            }
        });

        crearAct = (ImageView)findViewById(R.id.imageButtonCrearHorario);
        crearAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String dia = daySpinner.getSelectedItem().toString();
                    addActividad(dia, txtInicio.getText().toString(), txtFin.getText().toString());
                    Toast.makeText(getApplicationContext(),"Actividad creada correctamente ",Toast.LENGTH_SHORT).show();
                    closeKeyboard();
                    newAct.setVisibility(View.GONE);
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Parece que hubo un error, revise los campos e intente nuevamente. ",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void addActividad(String daySpinner, String horaIni, String horaFin ){
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("name", txtNomAct.getText().toString());
        data.put("inicio", horaIni);
        data.put("final", horaFin);
        data.put("description", txtDesc.getText().toString());

       db.collection("Users").document(user).collection("Horarios").document(nombreH).collection(daySpinner).document(txtNomAct.getText().toString()).set(data);
       db.collection("Users").document(user).collection("Horarios").document(nombreH).collection(daySpinner).document("Hora0").delete();
        finish();
        startActivity(getIntent());

    }
    public void cargarActividades(){

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        lunesLl = (LinearLayout)findViewById(R.id.nuevosLunes);
        martesLl = (LinearLayout)findViewById(R.id.nuevosMartes);
        miercolesLl = (LinearLayout)findViewById(R.id.nuevosMiercoles);
        juevesLl = (LinearLayout)findViewById(R.id.nuevosJueves);
        viernesLl = (LinearLayout)findViewById(R.id.nuevosViernes);
        sabadoLl = (LinearLayout)findViewById(R.id.nuevosSabado);
        domingoLl = (LinearLayout)findViewById(R.id.nuevosDomingo);

        final ArrayList<LinearLayout> diasLl = new ArrayList<>();
        diasLl.add(lunesLl);
        diasLl.add(martesLl);
        diasLl.add(miercolesLl);
        diasLl.add(juevesLl);
        diasLl.add(viernesLl);
        diasLl.add(sabadoLl);
        diasLl.add(domingoLl);

        db.collection("Users").document(user).collection("Horarios").document(nombreH).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists() == true){


                    if(((String)documentSnapshot.get("length")).equals("Weekend")){
                        for(int i = 1; i <= 7; i++){

                            final int finalI = i;
                            db.collection("Users").document(user).collection("Horarios").document(nombreH).collection(diasCom[i-1]).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                    int j = finalI;
                                    if(task.isSuccessful()){

                                        for(DocumentSnapshot doc : task.getResult().getDocuments()){
                                            ArrayList<String> info = new ArrayList<>();
                                           Map<String, Object> d1 = doc.getData();

                                            for (Map.Entry entry : d1.entrySet())
                                            {
                                                info.add(entry.getValue().toString());
                                            }
                                            String content;
                                            if(info.size() == 0){
                                                content = "Sin registros";
                                                Button newButton = new Button(getApplicationContext());
                                                newButton.setTag(content);
                                                newButton.setText(content);

                                                diasLl.get(j-1).addView(newButton);
                                            }
                                            else {
                                                String[] parts = info.get(0).split("");
                                                if(parts.length == 9 && parts[8].equals("M") && (parts[1].equals("1") || parts[1].equals("0"))){
                                                    content = "" + info.get(1) + "\nDe: " + info.get(0) + "\t Hasta: " + info.get(2);
                                                }
                                                else{
                                                    content = "" + info.get(0) + "\nDe: " + info.get(1) + "\t Hasta: " + info.get(3);
                                                }

                                                Button newButton = new Button(getApplicationContext());
                                                newButton.setTag(info.get(0));
                                                newButton.setText(content);

                                                diasLl.get(j-1).addView(newButton);
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                    else if (((String) documentSnapshot.get("length")).equals("Week")){
                        for(int i = 1; i <= diaslv.length; i++){

                            final int finalI = i;
                            db.collection("Users").document(user).collection("Horarios").document(nombreH).collection(diaslv[i-1]).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    int j = finalI;
                                    if(task.isSuccessful()){

                                        for(DocumentSnapshot doc : task.getResult().getDocuments()){
                                            ArrayList<String> info = new ArrayList<>();
                                            Map<String, Object> d1 = doc.getData();

                                            for (Map.Entry entry : d1.entrySet())
                                            {
                                                info.add(entry.getValue().toString());
                                            }
                                            String content;
                                            if(info.size() == 0){
                                                content = "Sin registros";
                                                Button newButton = new Button(getApplicationContext());
                                                newButton.setTag(content);
                                                newButton.setText(content);

                                                diasLl.get(j-1).addView(newButton);
                                            }
                                            else {
                                                String[] parts = info.get(0).split("");
                                                if(parts.length == 9 && parts[8].equals("M") && (parts[1].equals("1") || parts[1].equals("0"))){
                                                    content = "" + info.get(1) + "\nDe: " + info.get(0) + "\t Hasta: " + info.get(2);
                                                }
                                                else{
                                                    content = "" + info.get(0) + "\nDe: " + info.get(1) + "\t Hasta: " + info.get(3);
                                                }

                                                Button newButton = new Button(getApplicationContext());
                                                newButton.setTag(info.get(0));
                                                newButton.setText(content);

                                                diasLl.get(j-1).addView(newButton);
                                            }


                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });

        db.collection("Users").document(user).collection("Horarios").document(nombreH).collection("Lunes");


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
}