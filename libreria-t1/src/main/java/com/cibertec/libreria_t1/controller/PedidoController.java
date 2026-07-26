package com.cibertec.libreria_t1.controller;

import com.cibertec.libreria_t1.dto.DetallePedidoRequest;
import com.cibertec.libreria_t1.dto.PedidoRequest;
import com.cibertec.libreria_t1.service.ClienteService;
import com.cibertec.libreria_t1.service.EmpleadoService;
import com.cibertec.libreria_t1.service.LibroService;
import com.cibertec.libreria_t1.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final EmpleadoService empleadoService;
    private final LibroService libroService;

    public PedidoController(PedidoService pedidoService,
                            ClienteService clienteService,
                            EmpleadoService empleadoService,
                            LibroService libroService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
        this.empleadoService = empleadoService;
        this.libroService = libroService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoService.listar());
        return "pedidos/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        PedidoRequest pedido = new PedidoRequest();
        pedido.getItems().add(new DetallePedidoRequest()); // una fila inicial
        model.addAttribute("pedido", pedido);
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("empleados", empleadoService.listar());
        model.addAttribute("libros", libroService.listar());
        return "pedidos/nuevo";
    }

    @PostMapping
    public String registrar(@Valid @ModelAttribute("pedido") PedidoRequest request,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clientes", clienteService.listar());
            model.addAttribute("empleados", empleadoService.listar());
            model.addAttribute("libros", libroService.listar());
            return "pedidos/nuevo";
        }
        pedidoService.registrar(request);
        return "redirect:/pedidos";
    }
}
