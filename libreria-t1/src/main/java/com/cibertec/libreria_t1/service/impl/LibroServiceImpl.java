package com.cibertec.libreria_t1.service.impl;

import com.cibertec.libreria_t1.dto.LibroRequest;
import com.cibertec.libreria_t1.dto.LibroResponse;
import com.cibertec.libreria_t1.mapper.LibroMapper;
import com.cibertec.libreria_t1.model.Libro;
import com.cibertec.libreria_t1.repository.LibroRepository;
import com.cibertec.libreria_t1.service.LibroService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;
    private final LibroMapper libroMapper;

    public LibroServiceImpl(LibroRepository libroRepository, LibroMapper libroMapper) {
        this.libroRepository = libroRepository;
        this.libroMapper = libroMapper;
    }

    @Override
    public List<LibroResponse> listar() {
        return libroRepository.findAll().stream()
                .map(libroMapper::toResponse)
                .toList();
    }

    @Override
    public LibroResponse obtenerPorId(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado con id " + id));
        return libroMapper.toResponse(libro);
    }

    @Override
    @Transactional
    public LibroResponse registrar(LibroRequest request) {
        Libro libro = libroMapper.toEntity(request);
        return libroMapper.toResponse(libroRepository.save(libro));
    }
}
