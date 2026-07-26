package com.cibertec.libreria_t1.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PedidoRequest {

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El empleado es obligatorio")
    private Long empleadoId;

    @NotEmpty(message = "El pedido debe tener al menos un libro")
    @Valid
    private List<DetallePedidoRequest> items = new ArrayList<>();

    public PedidoRequest() {}

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Long getEmpleadoId() { return empleadoId; }
    public void setEmpleadoId(Long empleadoId) { this.empleadoId = empleadoId; }
    public List<DetallePedidoRequest> getItems() { return items; }
    public void setItems(List<DetallePedidoRequest> items) { this.items = items; }
}
