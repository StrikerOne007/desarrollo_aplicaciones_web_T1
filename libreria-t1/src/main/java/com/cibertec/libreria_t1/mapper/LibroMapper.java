package com.cibertec.libreria_t1.mapper;

import com.cibertec.libreria_t1.dto.LibroRequest;
import com.cibertec.libreria_t1.dto.LibroResponse;
import com.cibertec.libreria_t1.model.Libro;
import org.springframework.stereotype.Component;

@Component
public class LibroMapper {

    public Libro toEntity(LibroRequest request) {
        Libro libro = new Libro();
        libro.setIsbn(request.getIsbn());
        libro.setTitulo(request.getTitulo());
        libro.setAutor(request.getAutor());
        libro.setPrecio(request.getPrecio());
        libro.setStock(request.getStock());
        return libro;
    }

    public LibroResponse toResponse(Libro libro) {
        return new LibroResponse(
                libro.getId(),
                libro.getIsbn(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getPrecio(),
                libro.getStock()
        );
    }
}
