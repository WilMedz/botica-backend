package com.SistemaWeb.Botica.service;

import com.SistemaWeb.Botica.model.Producto;

import java.util.List;

public interface IProductoService {
    Producto save(Producto producto) throws Exception;
    Producto update(Producto producto, Integer id) throws Exception;
    List<Producto> findAll() throws Exception;
    Producto findById(Integer id) throws Exception;
    void delete(Integer id) throws Exception;
}