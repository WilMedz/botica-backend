package com.SistemaWeb.Botica.repository;

import com.SistemaWeb.Botica.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaRepository extends JpaRepository<Venta,Integer> {
}
