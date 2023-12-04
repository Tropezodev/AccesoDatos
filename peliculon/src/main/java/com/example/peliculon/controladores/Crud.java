package com.example.peliculon.controladores;

import com.example.peliculon.modelo.Pelicula;
import com.example.peliculon.servicios.ServicioPeliculas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Crud {
    // Para modificar películas necesito el Servicio de Películas
    @Autowired
    ServicioPeliculas servicioPeliculas;
    // Este url/endpoint nos muestra la lista con todas las películas
    @GetMapping("/crud")
    public String listadoPeliculas (Model model){
        // El nombre "peliculas" es el que va a usarse en la plantilla "crud.html" con thymeleaf
        model.addAttribute("peliculas", servicioPeliculas.findAll());
        return "crud";
    }

    // Para añadir registros siempre hace falta un GetMapping y un PostMapping
    // El GetMapping muestra el formulario para añadir las películas, pero AÚN NO LAS AÑADE
    @GetMapping("/crud/add")
    public String addPeliculas (Model model){
        // formPelicula es el objeto que voy a usar en la plantilla
        model.addAttribute("formPelicula", new Pelicula());
        return "form_add";
    }
    // Esta es la URL que aparece en el th:action del formulario para añadir películas
    // Lo que parece en el @ModelAttribute es lo mismo del th:object del formulario
    @PostMapping("/crud/save")
    public String guardarPelicula(@ModelAttribute("formPelicula")Pelicula nuevaPelicula){
        servicioPeliculas.save(nuevaPelicula);
        return "redirect:/crud/add";
    }

    @GetMapping("/crud/update/{id}")
    public String muestraPelicula (@PathVariable long id, Model model){
        Pelicula p = servicioPeliculas.findById(id);
        // El nombre del objeto debe de ser el mismo que el del GetMapping de añadir y el mismo que en el th:object del formulario
        model.addAttribute("formPelicula", p);
        return "form_add";
    }

    @PostMapping("/crud/modificar")
    public String modificarPelicula(@ModelAttribute("formPelicula") Pelicula p){
        servicioPeliculas.save(p);
        return "redirect:/crud";
    }
    @GetMapping("/crud/delete/{id}")
    public String borrarPelicula (@PathVariable long id, Model model){
        Pelicula p = servicioPeliculas.findById(id);
        servicioPeliculas.delete(p);
        return "redirect:/crud";
    }
}
