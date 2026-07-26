package com.cibertec.libreria_t1.dto;

import java.time.LocalDate;

public class EmpleadoResponse {

    private Long id;
    private String nombres;
    private String apellidos;
    private String numeroDocumento;
    private String telefono;
    private String cargo;
    private LocalDate fechaIngreso;

    public EmpleadoResponse() {}

    public EmpleadoResponse(Long id, String nombres, String apellidos, String numeroDocumento,
                            String telefono, String cargo, LocalDate fechaIngreso) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.cargo = cargo;
        this.fechaIngreso = fechaIngreso;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
}
