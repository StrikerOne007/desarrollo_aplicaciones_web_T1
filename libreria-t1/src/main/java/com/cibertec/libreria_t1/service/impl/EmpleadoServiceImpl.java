package com.cibertec.libreria_t1.service.impl;

import com.cibertec.libreria_t1.dto.EmpleadoRequest;
import com.cibertec.libreria_t1.dto.EmpleadoResponse;
import com.cibertec.libreria_t1.mapper.EmpleadoMapper;
import com.cibertec.libreria_t1.model.Empleado;
import com.cibertec.libreria_t1.repository.EmpleadoRepository;
import com.cibertec.libreria_t1.service.EmpleadoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    @Override
    public List<EmpleadoResponse> listar() {
        return empleadoRepository.findAll().stream()
                .map(empleadoMapper::toResponse)
                .toList();
    }

    @Override
    public EmpleadoResponse obtenerPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con id " + id));
        return empleadoMapper.toResponse(empleado);
    }

    @Override
    @Transactional
    public EmpleadoResponse registrar(EmpleadoRequest request) {
        Empleado empleado = empleadoMapper.toEntity(request);
        return empleadoMapper.toResponse(empleadoRepository.save(empleado));
    }
}
