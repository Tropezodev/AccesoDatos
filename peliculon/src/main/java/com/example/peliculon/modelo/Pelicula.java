package com.example.peliculon.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@Entity
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String sinopsis;
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    private LocalDate fecha; // Si es para una api/web internacional se usa ZonedDateTime
    private String nacionalidad;
    private String imagen;
    private String trailer;
    /*@OneToMany
    private ArrayList<Comentario> comentarios;*/

    /*public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }*/
}
