package com.cibertec.libreria_t1.service;

import com.cibertec.libreria_t1.dto.PedidoRequest;
import com.cibertec.libreria_t1.dto.PedidoResponse;

import java.util.List;

public interface PedidoService {

    List<PedidoResponse> listar();

    PedidoResponse obtenerPorId(Long id);

    PedidoResponse registrar(PedidoRequest request);
}
