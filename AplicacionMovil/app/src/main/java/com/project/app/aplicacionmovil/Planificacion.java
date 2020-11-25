package com.project.app.aplicacionmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.type.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class Planificacion extends AppCompatActivity implements Dialog.DialogListener {

    private CompactCalendarView calendarView;
    private TextView fecha;
    private TextView tituloPlanificacion, tituloCalendario;
    private FloatingActionButton nuevoEvento;
    private String user;

    private ArrayList<ObjetosPlanificacion> listaEventos, eventosSeleccionados;
    private RecyclerView recyclerEventos, recyclerSelecionados;

    private AdaptadorPlanificacion adapter;
    private AdaptadorEventoSeleccionado adapterSeleccionado;
    private Date date;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    private String[] meses = {"January","February","March","April","May","June","July","August","September","October","November","December"};

    private ArrayList<ObjetosPlanificacion> existentes;
    private HashMap<String, ArrayList<ObjetosPlanificacion>> eventosTotales;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = intent.getStringExtra("User");
        date = new Date(120,10,3);
        eventosTotales = new HashMap<>();
        setContentView(R.layout.activity_planificacion);
        tituloPlanificacion=(TextView)findViewById(R.id.tituloPlanificacion);






        tituloCalendario=(TextView)findViewById(R.id.tituloCalendario);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        String currentTime = meses[month]+" / "+year;
        tituloCalendario.setText(currentTime.toString());


        llenarPlanificacion();




        nuevoEvento = (FloatingActionButton)findViewById(R.id.floatingNewEvent);
        nuevoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean con = conexion();
                if(con)
                {
                    openDialog();
                }
                else
                {
                    Intent intent = new Intent(Planificacion.this, Internet.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void openDialog()
    {
        Dialog dialog = new Dialog(date, user);
        dialog.show(getSupportFragmentManager(),"Example Dialog");
    }

    public void openSelectedEvent(List<Event> eventos,ArrayList<ObjetosPlanificacion> enviarSeleccion)
    {
        DialogEvent dialogEvent = new DialogEvent(enviarSeleccion);
        dialogEvent.show(getSupportFragmentManager(),"Example");
    }


    public void llenarPlanificacion(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Users").document(user).collection("Planificaciones").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                listaEventos = new ArrayList<>();
                calendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
                calendarView.setUseThreeLetterAbbreviation(true);
                calendarView.setShouldShowMondayAsFirstDay(true);
                calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                    @Override
                    public void onDayClick(Date dateClicked) {
                        date = dateClicked;
                        Context context = getApplicationContext();
                        long diaEpoch = dateClicked.getTime();

                        ArrayList<ObjetosPlanificacion> enviarSeleccion = new ArrayList<>();
                        //ya se tienen los eventos por MesAño (verificar) falta mostrarlo en el popup}
                        for(ObjetosPlanificacion objP : listaEventos)
                        {
                            if(objP.getFecha().getTime() == diaEpoch)
                            {
                                enviarSeleccion.add(objP);
                            }
                        }
                        System.out.println(enviarSeleccion);

                        ArrayList<Event> events = (ArrayList<Event>) calendarView.getEvents(diaEpoch);
                        if(enviarSeleccion.size() > 0)
                        {
                            openSelectedEvent(events,enviarSeleccion);
                        }
                        else
                        {
                            Toast.makeText(context,"No hay eventos",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onMonthScroll(Date firstDayOfNewMonth) {
                        tituloCalendario.setText(dateFormatMonth.format(firstDayOfNewMonth));
                    }
                });

                if(task.isSuccessful()){
                    for(DocumentSnapshot doc : task.getResult().getDocuments()){
                        String[] info = new String[6];

                        info[0] = doc.get("name").toString();
                        info[1] = doc.get("descripcion").toString();
                        info[2] = doc.get("fecha").toString();
                        info[3] = doc.get("horaInicio").toString();
                        info[4] = doc.get("horaFinal").toString();
                        info[5] = doc.get("color").toString();


                        Long fecha1 = Long.parseLong(info[2]);
                        Date d = new Date(fecha1);
                        Event ev1 = new Event(Color.parseColor(info[5]), fecha1, info[0]);
                        calendarView.addEvent(ev1);
                       listaEventos.add(new ObjetosPlanificacion(info[0], d, info[3], info[4], info[5], info[1]));

                    }
                    recyclerEventos = findViewById(R.id.recyclerview);
                    recyclerEventos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new AdaptadorPlanificacion(listaEventos);
                    recyclerEventos.setAdapter(adapter);
                }
            }
        });
    }
    public void addEvento(String titulo, Date fecha,String horaIn,String horaFin,String color, String descripcion)
    {
        boolean con = conexion();
        if(con)
        {
            ObjetosPlanificacion nuevoObjeto = new ObjetosPlanificacion(titulo,fecha,horaIn,horaFin,color,descripcion);
            listaEventos.add(nuevoObjeto);
            int color2 = (Color.parseColor(color));
            adapter.notifyItemInserted(listaEventos.size()-1);


            Event evNew = new Event(color2,fecha.getTime(), titulo);
            calendarView.addEvent(evNew);
        }
        else
        {
            Intent intent = new Intent(Planificacion.this, Internet.class);
            startActivity(intent);
        }
    }

    @Override
    public void sendValues(String titulo, Date fecha, String horaIn, String horaFin, String color, String descripcion) {
        addEvento(titulo, fecha, horaIn, horaFin, color,descripcion);
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