package com.SistemaWeb.Botica.service;

import com.SistemaWeb.Botica.model.DetalleVenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDetalleVentaService extends IGenericService<DetalleVenta, Integer> {
    Page<DetalleVenta> listPage(Pageable pageable);
}