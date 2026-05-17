package com.SistemaWeb.Botica.service;

import com.SistemaWeb.Botica.model.Categoria;
import java.util.List;

public interface ICategoriaService {
    Categoria save(Categoria categoria) throws Exception;
    Categoria update(Categoria categoria, Integer id) throws Exception;
    List<Categoria> findAll() throws Exception;
    Categoria findById(Integer id) throws Exception;
    void delete(Integer id) throws Exception;
}
