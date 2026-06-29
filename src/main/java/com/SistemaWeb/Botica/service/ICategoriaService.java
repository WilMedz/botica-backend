package com.SistemaWeb.Botica.service;

import com.SistemaWeb.Botica.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ICategoriaService extends IGenericService<Categoria, Integer> {

    Page<Categoria> listPage(Pageable pageable);
}