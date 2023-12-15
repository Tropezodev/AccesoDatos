package com.example.libros.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String autor;
    @Column(columnDefinition = "TEXT")
    private String sinopsis;
    @Column(nullable = false)
    private double precio;
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    private LocalDate fechaPublicacion;
    private String rutaImagen;

    public Libro(String titulo, String autor, String sinopsis, double precio, String rutaImagen) {
        this.titulo = titulo;
        this.autor = autor;
        this.sinopsis = sinopsis;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
    }
    public Libro(){

    }
}
