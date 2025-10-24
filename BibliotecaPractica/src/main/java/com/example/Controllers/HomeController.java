package com.example.bibliotecaCL.controller;

import com.example.bibliotecaCL.domain.Libro;
import com.example.bibliotecaCL.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/")
    public String inicio(Model model) {
        Libro nuevoLibro = libroService.obtenerUltimoLibro();
        model.addAttribute("nuevoLibro", nuevoLibro);
        return "index";
    }
}
