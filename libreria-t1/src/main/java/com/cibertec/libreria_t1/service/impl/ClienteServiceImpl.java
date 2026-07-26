package com.cibertec.libreria_t1.service.impl;

import com.cibertec.libreria_t1.dto.ClienteRequest;
import com.cibertec.libreria_t1.dto.ClienteResponse;
import com.cibertec.libreria_t1.mapper.ClienteMapper;
import com.cibertec.libreria_t1.model.Cliente;
import com.cibertec.libreria_t1.repository.ClienteRepository;
import com.cibertec.libreria_t1.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public List<ClienteResponse> listar() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::toResponse)
                .toList();
    }

    @Override
    public ClienteResponse obtenerPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id " + id));
        return clienteMapper.toResponse(cliente);
    }

    @Override
    @Transactional
    public ClienteResponse registrar(ClienteRequest request) {
        Cliente cliente = clienteMapper.toEntity(request);
        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }
}
