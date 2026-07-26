package com.cibertec.libreria_t1.dto;

import java.math.BigDecimal;

public class DetallePedidoResponse {

    private Long id;
    private String libro;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    public DetallePedidoResponse() {}

    public DetallePedidoResponse(Long id, String libro, Integer cantidad,
                                 BigDecimal precioUnitario, BigDecimal subtotal) {
        this.id = id;
        this.libro = libro;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLibro() { return libro; }
    public void setLibro(String libro) { this.libro = libro; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
}
