package com.example.libros.services;

import com.example.libros.models.Libro;
import com.example.libros.repositories.RepositorioLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioLibros {
    @Autowired
    RepositorioLibros repositorioLibros;
    public ArrayList<Libro> finAll(){
        return repositorioLibros.findAll();
    }
    public Libro findById(long id){
        return repositorioLibros.findById(id);
    }
    public Libro save(Libro libro){
        // Las comprobaciones se hacen en los servicios. Ej: if(pelicula.getNacionalidad().equals(null))
        return repositorioLibros.save(libro);
    }
    public ArrayList<Libro> findByNacionalidad(String autor){
        return repositorioLibros.findByAutor(autor);
    }
    public void delete(Libro libro){
        repositorioLibros.delete(libro);
    }
    public void deleteById(long id){
        repositorioLibros.deleteById(id);
    }
}
