package com.example.libros.controlers;

import com.example.libros.models.Libro;
import com.example.libros.services.ServicioLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/crud")
public class Crud {
    @Autowired
    ServicioLibros servicioLibros;

    @GetMapping("/libros")
    public String listarLibros(Model model) {
        model.addAttribute("listaLibros", servicioLibros.finAll());
        return "crud";
    }
    @GetMapping("/libros/add")
    public String addLibro(Model model){
        model.addAttribute("formLibro", new Libro());
        return "formulario";
    }
    @PostMapping("/libros/postAdd")
    public String postAddLibro(@ModelAttribute("libro") Libro nuevoLibro){
        servicioLibros.save(nuevoLibro);
        return "redirect:/crud/libros/add";
    }
    @GetMapping("/libros/update/{id}")
    public String updateLibro(@PathVariable long id, Model model){
        Libro libro = servicioLibros.findById(id);
        model.addAttribute("formLibro", libro);
        return "formulario";
    }
    @PostMapping("/libros/postUpdate")
    public String postUpdateLibro(@ModelAttribute("libro") Libro libro){
        servicioLibros.save(libro);
        return "redirect:/crud/libros";
    }
    @GetMapping("/libros/delete/{id}")
    public String deleteLibro(@PathVariable long id, Model model){
        servicioLibros.deleteById(id);
        return "redirect:/crud/libros";
    }
}
