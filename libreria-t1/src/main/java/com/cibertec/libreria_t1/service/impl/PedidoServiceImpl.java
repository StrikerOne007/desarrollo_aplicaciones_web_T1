package com.cibertec.libreria_t1.service.impl;

import com.cibertec.libreria_t1.dto.DetallePedidoRequest;
import com.cibertec.libreria_t1.dto.PedidoRequest;
import com.cibertec.libreria_t1.dto.PedidoResponse;
import com.cibertec.libreria_t1.exception.StockInsuficienteException;
import com.cibertec.libreria_t1.mapper.PedidoMapper;
import com.cibertec.libreria_t1.model.Cliente;
import com.cibertec.libreria_t1.model.DetallePedido;
import com.cibertec.libreria_t1.model.Empleado;
import com.cibertec.libreria_t1.model.Libro;
import com.cibertec.libreria_t1.model.Pedido;
import com.cibertec.libreria_t1.repository.ClienteRepository;
import com.cibertec.libreria_t1.repository.EmpleadoRepository;
import com.cibertec.libreria_t1.repository.LibroRepository;
import com.cibertec.libreria_t1.repository.PedidoRepository;
import com.cibertec.libreria_t1.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final LibroRepository libroRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoServiceImpl(PedidoRepository pedidoRepository,
                             ClienteRepository clienteRepository,
                             EmpleadoRepository empleadoRepository,
                             LibroRepository libroRepository,
                             PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
        this.libroRepository = libroRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponse> listar() {
        return pedidoRepository.findAll().stream()
                .map(pedidoMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoResponse obtenerPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado con id " + id));
        return pedidoMapper.toResponse(pedido);
    }

    @Override
    @Transactional
    public PedidoResponse registrar(PedidoRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        Empleado empleado = empleadoRepository.findById(request.getEmpleadoId())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        Pedido pedido = new Pedido(cliente, empleado);
        BigDecimal total = BigDecimal.ZERO;

        for (DetallePedidoRequest item : request.getItems()) {
            Libro libro = libroRepository.findById(item.getLibroId())
                    .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado"));

            // Descuento atómico: si devuelve 0, no había stock suficiente.
            int afectadas = libroRepository.descontarStock(libro.getId(), item.getCantidad());
            if (afectadas == 0) {
                throw new StockInsuficienteException(
                        "Stock insuficiente para '" + libro.getTitulo()
                                + "' (disponible: " + libro.getStock() + ")");
            }

            DetallePedido detalle = new DetallePedido(pedido, libro, item.getCantidad(), libro.getPrecio());
            pedido.getDetalles().add(detalle);
            total = total.add(detalle.getSubtotal());
        }

        pedido.setTotal(total);
        pedidoRepository.save(pedido); // cascade ALL persiste los detalles
        return pedidoMapper.toResponse(pedido);
    }
}
