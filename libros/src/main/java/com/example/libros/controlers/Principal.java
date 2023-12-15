package com.example.libros.controlers;

import com.example.libros.models.Libro;
import com.example.libros.services.ServicioLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Principal {
    @Autowired
    ServicioLibros servicioLibros;

    @GetMapping({"/", "/inicio"})
    public String inicio (Model model){
        model.addAttribute("libreria", servicioLibros.finAll());
        return "index";
    }
    @GetMapping("/libro/{id}")
    public String libroDetalle(@PathVariable long id, Model model){
        Libro libro = servicioLibros.findById(id);
        model.addAttribute("libro", libro);
        return "detalle";
    }
}
