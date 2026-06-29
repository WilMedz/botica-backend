package com.SistemaWeb.Botica.service;

import com.SistemaWeb.Botica.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClienteService extends IGenericService<Cliente, Integer> {
    Page<Cliente> listPage(Pageable pageable);
}