package com.cibertec.libreria_t1.service;

import com.cibertec.libreria_t1.dto.DetallePedidoRequest;
import com.cibertec.libreria_t1.model.*;
import com.cibertec.libreria_t1.repository.LibroRepository;
import com.cibertec.libreria_t1.repository.PedidoRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    private final EntityManager entityManager;
    private final PedidoRepository pedidoRepository;
    private final LibroRepository libroRepository;

    public PedidoService(EntityManager entityManager,
                         PedidoRepository pedidoRepository,
                         LibroRepository libroRepository) {
        this.entityManager = entityManager;
        this.pedidoRepository = pedidoRepository;
        this.libroRepository = libroRepository;
    }

    @Transactional
    public Pedido registrarPedido(Cliente cliente, Empleado empleado, List<DetallePedidoRequest> items) {

        Pedido pedido = new Pedido(cliente, empleado);
        pedidoRepository.save(pedido);
        entityManager.flush(); // fuerza el INSERT y obtiene el ID del pedido ya

        BigDecimal total = BigDecimal.ZERO;
        for (DetallePedidoRequest item : items) {
            Libro libro = libroRepository.findById(item.getLibroId())
                    .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado"));

            DetallePedido detalle = new DetallePedido(pedido, libro, item.getCantidad(), libro.getPrecio());
            pedido.getDetalles().add(detalle);
            total = total.add(detalle.getSubtotal());
        }

        pedido.setTotal(total);
        return pedidoRepository.save(pedido); // dirty checking + cascade guarda los detalles
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }
}