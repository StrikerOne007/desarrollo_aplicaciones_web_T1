package com.cibertec.libreria_t1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "empleados")
public class Empleado extends Persona {

    @Column(nullable = false, length = 50)
    private String cargo;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    public Empleado() {}

    public Empleado(String nombres, String apellidos, String numeroDocumento,
                    String telefono, String cargo, LocalDate fechaIngreso) {
        super(nombres, apellidos, numeroDocumento, telefono);
        this.cargo = cargo;
        this.fechaIngreso = fechaIngreso;
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
}
