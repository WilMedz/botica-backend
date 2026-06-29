package com.SistemaWeb.Botica.service;

import com.SistemaWeb.Botica.model.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProveedorService extends IGenericService<Proveedor, Integer> {
    Page<Proveedor> listPage(Pageable pageable);
}