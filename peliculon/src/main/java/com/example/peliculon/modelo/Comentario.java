package com.example.peliculon.modelo;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String contenido;
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    private LocalDate fecha;

    @ManyToOne
    private Pelicula pelicula;
}
