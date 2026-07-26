package com.cibertec.libreria_t1.repository;

import com.cibertec.libreria_t1.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Descuenta el stock de forma atómica: solo actualiza si hay suficiente.
    // Devuelve 1 si descontó, 0 si el stock no alcanzaba.
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Libro l SET l.stock = l.stock - :cantidad WHERE l.id = :id AND l.stock >= :cantidad")
    int descontarStock(@Param("id") Long id, @Param("cantidad") Integer cantidad);
}
