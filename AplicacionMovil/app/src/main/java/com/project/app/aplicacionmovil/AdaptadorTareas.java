package com.project.app.aplicacionmovil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AdaptadorTareas extends RecyclerView.Adapter<AdaptadorTareas.ViewHolderTareas>{

    private ArrayList<ObjetoTareas> listaTareas;

    public AdaptadorTareas(ArrayList<ObjetoTareas> listaTareas) {
        this.listaTareas = listaTareas;
    }

    @NonNull
    @Override
    public AdaptadorTareas.ViewHolderTareas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemtareas,null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10,10,10,10);
        view.setLayoutParams(lp);
        return new ViewHolderTareas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorTareas.ViewHolderTareas holder, int position) {
        holder.tituloTarea.setText(listaTareas.get(position).getTitulo());


    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public class ViewHolderTareas extends RecyclerView.ViewHolder
    {
        TextView tituloTarea;

        public ViewHolderTareas(@NonNull View itemView) {
            super(itemView);
            tituloTarea = (TextView) itemView.findViewById(R.id.textDTarea);
        }
    }


}
