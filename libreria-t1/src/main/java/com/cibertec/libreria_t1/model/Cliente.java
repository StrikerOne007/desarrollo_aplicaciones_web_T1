package com.cibertec.libreria_t1.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona {

    @Column(length = 150, unique = true)
    private String email;

    @Column(length = 250)
    private String direccion;

    // Un cliente puede tener muchos pedidos.
    // LAZY: no traigo la lista de pedidos a menos que la use explícitamente.
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {}

    public Cliente(String nombres, String apellidos, String numeroDocumento,
                   String telefono, String email, String direccion) {
        super(nombres, apellidos, numeroDocumento, telefono);
        this.email = email;
        this.direccion = direccion;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }
}
