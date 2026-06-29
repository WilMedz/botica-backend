package com.SistemaWeb.Botica.service;

import com.SistemaWeb.Botica.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IVentaService extends IGenericService<Venta, Integer> {
    Page<Venta> listPage(Pageable pageable);
}