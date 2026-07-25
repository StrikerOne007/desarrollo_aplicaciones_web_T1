package com.cibertec.libreria_t1.dto;

public class DetallePedidoRequest {

    private Long libroId;
    private Integer cantidad;

    public DetallePedidoRequest() {}

    public DetallePedidoRequest(Long libroId, Integer cantidad) {
        this.libroId = libroId;
        this.cantidad = cantidad;
    }

    public Long getLibroId() { return libroId; }
    public void setLibroId(Long libroId) { this.libroId = libroId; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
