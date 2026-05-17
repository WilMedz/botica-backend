package com.SistemaWeb.Botica.repository;

import com.SistemaWeb.Botica.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProveedorRepository extends JpaRepository<Proveedor, Integer> {
}