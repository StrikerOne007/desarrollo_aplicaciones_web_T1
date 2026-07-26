package com.cibertec.libreria_t1.service;

import com.cibertec.libreria_t1.dto.ClienteRequest;
import com.cibertec.libreria_t1.dto.ClienteResponse;

import java.util.List;

public interface ClienteService {

    List<ClienteResponse> listar();

    ClienteResponse obtenerPorId(Long id);

    ClienteResponse registrar(ClienteRequest request);
}
