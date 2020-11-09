package com.project.app.aplicacionmovil;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdaptadorPlanificacion extends RecyclerView.Adapter<AdaptadorPlanificacion.ViewHolderPlanificacion> {

    private ArrayList<ObjetosPlanificacion> listaEventosP;

    public AdaptadorPlanificacion(ArrayList<ObjetosPlanificacion> listaEventosP)
    {
        this.listaEventosP=listaEventosP;
    }


    public AdaptadorPlanificacion.ViewHolderPlanificacion onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.evento_planificacion,null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolderPlanificacion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPlanificacion.ViewHolderPlanificacion holder, int position) {
        holder.titulo.setText(listaEventosP.get(position).getTitulo());
        holder.fecha.setText(listaEventosP.get(position).getFechatxt());

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
        TextView titulo,fecha,horaInicio,horaFin,circulo;
        String color;

        public ViewHolderPlanificacion(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.tituloact);
            fecha = (TextView)itemView.findViewById(R.id.fecha);
            horaInicio=(TextView)itemView.findViewById(R.id.horainicio);
            horaFin = (TextView)itemView.findViewById(R.id.horafin);
            circulo = (TextView)itemView.findViewById(R.id.circuloPersonalizado);
        }
    }
}
