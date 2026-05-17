package com.SistemaWeb.Botica.service;

import com.SistemaWeb.Botica.model.Cliente;

import java.util.List;

public interface IClienteService {
    Cliente save(Cliente cliente) throws Exception;
    Cliente update(Cliente cliente, Integer id) throws Exception;
    List<Cliente> findAll() throws Exception;
    Cliente findById(Integer id) throws Exception;
    void delete(Integer id) throws Exception;
}