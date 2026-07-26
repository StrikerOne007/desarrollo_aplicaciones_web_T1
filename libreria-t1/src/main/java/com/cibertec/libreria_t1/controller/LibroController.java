package com.cibertec.libreria_t1.controller;

import com.cibertec.libreria_t1.dto.LibroRequest;
import com.cibertec.libreria_t1.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("libros", libroService.listar());
        return "libros/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("libro", new LibroRequest());
        return "libros/nuevo";
    }

    @PostMapping
    public String registrar(@Valid @ModelAttribute("libro") LibroRequest request,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "libros/nuevo";
        }
        libroService.registrar(request);
        return "redirect:/libros";
    }
}
