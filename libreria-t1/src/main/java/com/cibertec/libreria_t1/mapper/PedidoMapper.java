package com.cibertec.libreria_t1.mapper;

import com.cibertec.libreria_t1.dto.DetallePedidoResponse;
import com.cibertec.libreria_t1.dto.PedidoResponse;
import com.cibertec.libreria_t1.model.Pedido;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {

    public PedidoResponse toResponse(Pedido pedido) {
        List<DetallePedidoResponse> detalles = pedido.getDetalles().stream()
                .map(d -> new DetallePedidoResponse(
                        d.getId(),
                        d.getLibro().getTitulo(),
                        d.getCantidad(),
                        d.getPrecioUnitario(),
                        d.getSubtotal()))
                .toList();

        return new PedidoResponse(
                pedido.getId(),
                pedido.getCliente().getNombres() + " " + pedido.getCliente().getApellidos(),
                pedido.getEmpleado().getNombres() + " " + pedido.getEmpleado().getApellidos(),
                pedido.getFecha(),
                pedido.getTotal(),
                pedido.getEstado(),
                detalles
        );
    }
}
