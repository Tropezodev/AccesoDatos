package com.example.peliculon.controladores;

import com.example.peliculon.modelo.Comentario;
import com.example.peliculon.modelo.Pelicula;
import com.example.peliculon.servicios.ServicioComentarios;
import com.example.peliculon.servicios.ServicioPeliculas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

// El GetMapping suele mandar los datos del controlador a la vista
// El PostMapping suele mandar los datos de la vista al controlador

@Controller
public class Principal {
    @Autowired
    ServicioPeliculas servicioPeliculas;
    @Autowired
    ServicioComentarios servicioComentarios;

    @GetMapping({"/", "/inicio"})
    public String inicio(Model model) {
        ArrayList<Pelicula> cartelera = servicioPeliculas.findAll();
        model.addAttribute("cartelera", cartelera);
        model.addAttribute("ultimosComentarios",servicioComentarios.find3());
        return "index";
    }

    // Esta es la dirección que tengo que escribir en el navegador
    // Esta es la dirección que tengo que escribir en el enlace de index.html
    // Lo que sale en el <a th:href="//pelicula/{id}>
    @GetMapping("/pelicula/{id}")
    public String pelicula(@PathVariable long id, Model model) {
        Pelicula pelicula = servicioPeliculas.findById(id);
        // El nombre de "película" es el que voy a usar en la vista detalle.html
        model.addAttribute("pelicula", pelicula);

        Comentario comentario = servicioComentarios.findById(id);
        model.addAttribute("comentarios", servicioComentarios.findByPelicula(pelicula));
        model.addAttribute("nuevoComentario", new Comentario());
        // El nombre del return es el que tendrá el html que va a devolver
        return "detalle";
    }
    @PostMapping("/comentario/add")
    public String guardarComentario(@ModelAttribute("nuevoComentario") Comentario comentario, @RequestParam long idPelicula) {
        comentario.setFecha(LocalDate.now());
        // "Localizo" la película que tiene el id que hemos pasado en el campo idPelicula
        Pelicula pelicula = servicioPeliculas.findById(idPelicula);
        // Al comentario le asigno la película
        comentario.setPelicula(pelicula);
        servicioComentarios.save(comentario);
        return "redirect:/pelicula/" + comentario.getPelicula().getId();
    }
}
