package com.example.fichaje;

import lombok.Data;

@Data
public class Trabajador {
    private int id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String departamento;
}
