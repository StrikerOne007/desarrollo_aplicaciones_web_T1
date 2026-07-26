package com.cibertec.libreria_t1.controller;

import com.cibertec.libreria_t1.dto.EmpleadoRequest;
import com.cibertec.libreria_t1.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("empleados", empleadoService.listar());
        return "empleados/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("empleado", new EmpleadoRequest());
        return "empleados/nuevo";
    }

    @PostMapping
    public String registrar(@Valid @ModelAttribute("empleado") EmpleadoRequest request,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "empleados/nuevo";
        }
        empleadoService.registrar(request);
        return "redirect:/empleados";
    }
}
