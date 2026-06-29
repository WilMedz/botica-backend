package com.SistemaWeb.Botica.service;

import com.SistemaWeb.Botica.model.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRolService extends IGenericService<Rol, Integer> {
    Page<Rol> listPage(Pageable pageable);
}