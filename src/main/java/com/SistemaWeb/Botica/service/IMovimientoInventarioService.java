package com.SistemaWeb.Botica.service;

import com.SistemaWeb.Botica.model.MovimientoInventario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMovimientoInventarioService extends IGenericService<MovimientoInventario, Integer> {
    Page<MovimientoInventario> listPage(Pageable pageable);
}