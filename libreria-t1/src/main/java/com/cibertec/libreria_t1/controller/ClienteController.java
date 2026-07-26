package com.cibertec.libreria_t1.controller;

import com.cibertec.libreria_t1.dto.ClienteRequest;
import com.cibertec.libreria_t1.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listar());
        return "clientes/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("cliente", new ClienteRequest());
        return "clientes/nuevo";
    }

    @PostMapping
    public String registrar(@Valid @ModelAttribute("cliente") ClienteRequest request,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "clientes/nuevo";
        }
        clienteService.registrar(request);
        return "redirect:/clientes";
    }
}
