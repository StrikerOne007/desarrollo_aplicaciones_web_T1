package com.cibertec.libreria_t1.mapper;

import com.cibertec.libreria_t1.dto.ClienteRequest;
import com.cibertec.libreria_t1.dto.ClienteResponse;
import com.cibertec.libreria_t1.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequest request) {
        return new Cliente(
                request.getNombres(),
                request.getApellidos(),
                request.getNumeroDocumento(),
                request.getTelefono(),
                request.getEmail(),
                request.getDireccion()
        );
    }

    public ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombres(),
                cliente.getApellidos(),
                cliente.getNumeroDocumento(),
                cliente.getTelefono(),
                cliente.getEmail(),
                cliente.getDireccion()
        );
    }
}
