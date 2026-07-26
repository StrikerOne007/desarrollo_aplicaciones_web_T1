package com.cibertec.libreria_t1.service;

import com.cibertec.libreria_t1.dto.EmpleadoRequest;
import com.cibertec.libreria_t1.dto.EmpleadoResponse;

import java.util.List;

public interface EmpleadoService {

    List<EmpleadoResponse> listar();

    EmpleadoResponse obtenerPorId(Long id);

    EmpleadoResponse registrar(EmpleadoRequest request);
}
