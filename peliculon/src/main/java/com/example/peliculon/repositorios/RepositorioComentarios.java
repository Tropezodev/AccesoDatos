package com.example.peliculon.repositorios;

import com.example.peliculon.modelo.Comentario;
import com.example.peliculon.modelo.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepositorioComentarios extends JpaRepository<Comentario, Long> {
    public ArrayList<Comentario> findAll();
    public Comentario findById(long id);
    public ArrayList<Comentario> findByPelicula(Pelicula pelicula);
    public Comentario save(Comentario comentario);
    public void delete(Comentario comentario);
    public void deleteById(long id);
    @Query("select c from Comentario c order by c.id desc limit 3")
    public ArrayList<Comentario> find3();
}
