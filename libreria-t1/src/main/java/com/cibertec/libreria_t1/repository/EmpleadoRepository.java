package com.cibertec.libreria_t1.repository;

import com.cibertec.libreria_t1.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}