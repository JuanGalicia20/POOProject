package com.project.app.aplicacionmovil;

import java.util.Date;

public class ObjetosPlanificacion {

    private String titulo;
    private Date fecha;
    private String horaInicio;
    private String horaFin;
    private String color;
    private String fechatxt;
    private String descripcion;

    public ObjetosPlanificacion(String titulo, Date fecha, String horaInicio, String horaFin, String color, String descripcion) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.color = color;
        this.descripcion=descripcion;
        fechatxt = fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+(fecha.getYear()+1900);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFechatxt() {
        return fechatxt;
    }

    public void setFechatxt(String fechatxt) {
        this.fechatxt = fechatxt;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
}
