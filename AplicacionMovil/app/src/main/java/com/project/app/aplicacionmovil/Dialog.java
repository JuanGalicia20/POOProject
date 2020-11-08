package com.project.app.aplicacionmovil;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Dialog  extends AppCompatDialogFragment {

    private EditText tituloEvento;
    private EditText descEvento;
    private Button horaInEvento;
    private Button horaFinEvento;
    private Button elegirColor;
    private int t1H,t1M,t2H,t2M;
    int defaultColor;
    private String titulo, desc ,timeOut,timeIn, sendColor;
    private Date fechaFinal;
    private DialogListener dialogListener;
    private CompactCalendarView calendarView;


    public Dialog(Date date)
    {
        fechaFinal=date;
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);

        builder.setView(view).setTitle("Nueva Evento")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Crear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        titulo=tituloEvento.getText().toString();
                        desc=descEvento.getText().toString();
                        sendColor = String.format("#%06X",0xFFFFFF & defaultColor);

                        dialogListener.sendValues(titulo,fechaFinal,timeIn,timeOut,sendColor,desc);


                    }
                });
        tituloEvento=(EditText) view.findViewById(R.id.tituloEvento);
        descEvento = (EditText)view.findViewById(R.id.descEvento);
        horaInEvento = (Button)view.findViewById(R.id.horaInicioEvento);
        horaFinEvento=(Button)view.findViewById(R.id.horaFinEvento);
        elegirColor=(Button)view.findViewById(R.id.elegirColor);

        horaInEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
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
                                    timeIn=f12Horas.format(date);
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

        horaFinEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
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
                                    timeOut=f12Horas.format(date);
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

        elegirColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenColor();
            }
        });

        return builder.create();
    }

    public void OpenColor()
    {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getContext(), defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor = color;
            }
        });
        colorPicker.show();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            dialogListener=(DialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"OCURRIO UN ERROR EN DIALOG" +
                    "LISTENER");
        }
    }

    public interface DialogListener{
        void sendValues(String titulo, Date fecha, String horaIn,String horaFin, String color, String desc);
    }
}
