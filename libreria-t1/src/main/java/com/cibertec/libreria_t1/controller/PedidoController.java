package com.cibertec.libreria_t1.controller;

import com.cibertec.libreria_t1.dto.DetallePedidoRequest;
import com.cibertec.libreria_t1.model.Cliente;
import com.cibertec.libreria_t1.model.Empleado;
import com.cibertec.libreria_t1.repository.ClienteRepository;
import com.cibertec.libreria_t1.repository.EmpleadoRepository;
import com.cibertec.libreria_t1.repository.LibroRepository;
import com.cibertec.libreria_t1.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final LibroRepository libroRepository;

    public PedidoController(PedidoService pedidoService,
                            ClienteRepository clienteRepository,
                            EmpleadoRepository empleadoRepository,
                            LibroRepository libroRepository) {
        this.pedidoService = pedidoService;
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
        this.libroRepository = libroRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoService.listarTodos());
        return "inicio";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("empleados", empleadoRepository.findAll());
        model.addAttribute("libros", libroRepository.findAll());
        return "nuevo_pedido";
    }

    @PostMapping
    public String registrar(@RequestParam Long clienteId,
                            @RequestParam Long empleadoId,
                            @RequestParam Long libroId,
                            @RequestParam Integer cantidad) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        List<DetallePedidoRequest> items = Collections.singletonList(
                new DetallePedidoRequest(libroId, cantidad)
        );

        pedidoService.registrarPedido(cliente, empleado, items);
        return "redirect:/pedidos";
    }
}