package com.cibertec.libreria_t1.repository;

import com.cibertec.libreria_t1.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}