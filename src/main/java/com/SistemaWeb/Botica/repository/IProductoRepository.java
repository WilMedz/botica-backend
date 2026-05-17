package com.SistemaWeb.Botica.repository;

import com.SistemaWeb.Botica.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto, Integer> {

}