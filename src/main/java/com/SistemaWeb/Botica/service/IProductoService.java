package com.SistemaWeb.Botica.service;

import com.SistemaWeb.Botica.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductoService extends IGenericService<Producto, Integer> {
    Page<Producto> listPage(Pageable pageable);
}