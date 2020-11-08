package com.project.app.aplicacionmovil;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdaptadorEventoSeleccionado extends RecyclerView.Adapter<AdaptadorEventoSeleccionado.ViewHolderPlanificacion> {

    ArrayList<ObjetosPlanificacion> listaEventosP;

    public AdaptadorEventoSeleccionado(ArrayList<ObjetosPlanificacion> listaEventosP)
    {
        this.listaEventosP=listaEventosP;
    }


    public AdaptadorEventoSeleccionado.ViewHolderPlanificacion onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dia_seleccionado,null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new AdaptadorEventoSeleccionado.ViewHolderPlanificacion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlanificacion holder, int position) {
        holder.titulo.setText(listaEventosP.get(position).getTitulo());
        holder.fecha.setText(listaEventosP.get(position).getFechatxt());
        holder.descripcion.setText(listaEventosP.get(position).getDescripcion());

        SimpleDateFormat f24Horas = new SimpleDateFormat(
                "HH:mm"
        );
        try
        {
            Date date = f24Horas.parse(listaEventosP.get(position).getHoraInicio());
            SimpleDateFormat f12Horas = new SimpleDateFormat(
                    "hh:mm aa"
            );
            holder.horaInicio.setText(f12Horas.format(date));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        try
        {
            Date date = f24Horas.parse(listaEventosP.get(position).getHoraFin());
            SimpleDateFormat f12Horas = new SimpleDateFormat(
                    "hh:mm aa"
            );
            holder.horaFin.setText(f12Horas.format(date));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        //holder.horaFin.setText(listaEventosP.get(position).getHoraFin());
        holder.color=listaEventosP.get(position).getColor();

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setSize(100,100);
        shape.setColor(Color.parseColor(holder.color));
        shape.setStroke(2, Color.parseColor("#000000"));
        holder.circulo.setBackground(shape);
    }


    @Override
    public int getItemCount() {
        return listaEventosP.size();
    }

    public class ViewHolderPlanificacion extends RecyclerView.ViewHolder
    {
        TextView titulo,fecha,horaInicio,horaFin,circulo, descripcion;
        String color;

        public ViewHolderPlanificacion(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.tituloactEvent);
            fecha = (TextView)itemView.findViewById(R.id.fechaSeleccionada);
            horaInicio=(TextView)itemView.findViewById(R.id.EventohoraIn);
            horaFin = (TextView)itemView.findViewById(R.id.EventohoraOut);
            circulo = (TextView)itemView.findViewById(R.id.circuloPersonalizadoEvento);
            descripcion = (TextView)itemView.findViewById(R.id.descripcionEvento);
        }
    }
}

