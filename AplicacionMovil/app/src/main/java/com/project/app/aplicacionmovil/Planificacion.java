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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

        date = new Date(120,10,3);
        eventosTotales = new HashMap<>();
        setContentView(R.layout.activity_planificacion);
        tituloPlanificacion=(TextView)findViewById(R.id.tituloPlanificacion);

        listaEventos = new ArrayList<>();
        recyclerEventos = findViewById(R.id.recyclerview);
        recyclerEventos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdaptadorPlanificacion(listaEventos);
        recyclerEventos.setAdapter(adapter);



        tituloCalendario=(TextView)findViewById(R.id.tituloCalendario);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        String currentTime = meses[month]+" / "+year;
        tituloCalendario.setText(currentTime.toString());
        calendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        calendarView.setUseThreeLetterAbbreviation(true);
        calendarView.setShouldShowMondayAsFirstDay(true);

        llenarEventos();

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
        Dialog dialog = new Dialog(date);
        dialog.show(getSupportFragmentManager(),"Example Dialog");
    }

    public void openSelectedEvent(List<Event> eventos,ArrayList<ObjetosPlanificacion> enviarSeleccion)
    {
        DialogEvent dialogEvent = new DialogEvent(enviarSeleccion);
        dialogEvent.show(getSupportFragmentManager(),"Example");
    }


    public void llenarEventos()
    {
        boolean con = conexion();
        if(con)
        {
            listaEventos.add(new ObjetosPlanificacion("Reunion", new Date(120,9,26),"12:20","14:20","#01DFD7",""));
            listaEventos.add(new ObjetosPlanificacion("Partido",new Date(120,10,27),"10:00","11:00","#0174DF",""));
            listaEventos.add(new ObjetosPlanificacion("Proyecto",new Date(120,10,20),"00:00","23:59","#00FF40",""));
            listaEventos.add(new ObjetosPlanificacion("Serie",new Date(120,11,25),"12:20","14:20","#FF8000",""));
            Event ev11 = new Event(Color.RED,1603670400000L, "Cumpleaños tio");
            calendarView.addEvent(ev11);
            Event ev2 = new Event(Color.BLUE,1606435200000L, "Feriado");
            calendarView.addEvent(ev2);
            Event ev3 = new Event(Color.BLACK,1605830400000L, "Vacaciones");
            calendarView.addEvent(ev3);
            Event ev4 = new Event(Color.YELLOW,1608854400000L, "Navidad");
            calendarView.addEvent(ev4);

            ArrayList<ObjetosPlanificacion> a1 = new ArrayList<>();
            a1.add(new ObjetosPlanificacion("Reunion", new Date(120,9,26),"12:20","14:20","#01DFD7",""));
            eventosTotales.put("202010",a1);
            ArrayList<ObjetosPlanificacion> a2 = new ArrayList<>();
            a2.add(new ObjetosPlanificacion("Partido",new Date(120,10,27),"10:00","11:00","#0174DF",""));
            a2.add(new ObjetosPlanificacion("Proyecto",new Date(120,10,20),"00:00","23:59","#00FF40",""));
            eventosTotales.put("202011",a2);
            ArrayList<ObjetosPlanificacion> a3 = new ArrayList<>();
            a3.add(new ObjetosPlanificacion("Serie",new Date(120,11,25),"12:20","14:20","#FF8000",""));
            eventosTotales.put("202010",a3);
        }
        else
        {
            Intent intent = new Intent(Planificacion.this, Internet.class);
            startActivity(intent);
        }
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