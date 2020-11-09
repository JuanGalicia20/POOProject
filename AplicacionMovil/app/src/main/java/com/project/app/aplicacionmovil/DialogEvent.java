package com.project.app.aplicacionmovil;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;

public class DialogEvent extends AppCompatDialogFragment {


    private RecyclerView recyclerSeleccionados;
    AdaptadorEventoSeleccionado adapterSeleccionado;
    ArrayList<ObjetosPlanificacion> listaEventos = new ArrayList<>();


    public DialogEvent(ArrayList<ObjetosPlanificacion> listaEventos)
    {
        this.listaEventos = listaEventos;
    }

    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.eventos_seleccionados,null);

        recyclerSeleccionados = view.findViewById(R.id.diaSeleccion);
        recyclerSeleccionados.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterSeleccionado = new AdaptadorEventoSeleccionado(listaEventos);
        recyclerSeleccionados.setAdapter(adapterSeleccionado);
        adapterSeleccionado.notifyItemInserted(listaEventos.size()-1);


        builder.setView(view).setTitle("Eventos Dia seleccionado")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        return builder.create();
    }
}
