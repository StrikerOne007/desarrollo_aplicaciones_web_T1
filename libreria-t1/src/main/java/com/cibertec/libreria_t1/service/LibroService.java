package com.cibertec.libreria_t1.service;

import com.cibertec.libreria_t1.dto.LibroRequest;
import com.cibertec.libreria_t1.dto.LibroResponse;

import java.util.List;

public interface LibroService {

    List<LibroResponse> listar();

    LibroResponse obtenerPorId(Long id);

    LibroResponse registrar(LibroRequest request);
}
