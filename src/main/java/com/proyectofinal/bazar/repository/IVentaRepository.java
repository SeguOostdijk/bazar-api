package com.proyectofinal.bazar.repository;

import com.proyectofinal.bazar.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaRepository extends JpaRepository<Venta,Long> {
    Venta findTopByOrderByTotalDesc();
}
