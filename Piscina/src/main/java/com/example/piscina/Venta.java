package com.example.piscina;

import java.time.LocalDate;
import java.time.LocalTime;

public class Venta {

    private int id;
    private String nombre;
    private LocalDate fecha;
    private LocalTime hora;
    private int adultos;
    private int menores;
    private float precioAdultos = 5.00F;
    private float precioMenores = 2.80F;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getAdultos() {
        return adultos;
    }

    public void setAdultos(int adultos) {
        this.adultos = adultos;
    }

    public int getMenores() {
        return menores;
    }

    public void setMenores(int menores) {
        this.menores = menores;
    }

    public float getPrecioAdultos() {
        return precioAdultos;
    }

    public void setPrecioAdultos(float precioAdultos) {
        this.precioAdultos = precioAdultos;
    }

    public float getPrecioMenores() {
        return precioMenores;
    }

    public void setPrecioMenores(float precioMenores) {
        this.precioMenores = precioMenores;
    }
}
