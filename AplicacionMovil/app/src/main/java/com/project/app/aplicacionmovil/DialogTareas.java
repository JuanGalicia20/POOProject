package com.project.app.aplicacionmovil;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class DialogTareas extends AppCompatDialogFragment

    {

        public String descripcion;
        public TextView descTareasU;

        public DialogTareas(String descripcion){
            this.descripcion = descripcion;
        }

        public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_descripciont,null);
            descTareasU = (TextView) view.findViewById(R.id.tvDescTarea);
            descTareasU.setText(descripcion);

            builder.setView(view).setTitle("Descripción tareas")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });


            return builder.create();
        }
    }
