package com.example.libros.repositories;

import com.example.libros.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepositorioLibros extends JpaRepository<Libro, Long> {
    public ArrayList<Libro> findAll();
    public Libro findById(long id);
    public ArrayList<Libro> findByAutor(String autor);
    public Libro save(Libro libro);
    public void delete (Libro libro);
    public void deleteById(long id);
}
