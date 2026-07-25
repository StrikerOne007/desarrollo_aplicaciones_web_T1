package com.cibertec.libreria_t1.repository;

import com.cibertec.libreria_t1.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}