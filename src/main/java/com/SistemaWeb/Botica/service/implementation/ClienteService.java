package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Cliente;
import com.SistemaWeb.Botica.repository.IClienteRepository;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService extends GenericService<Cliente, Integer> implements IClienteService {
    private final IClienteRepository repo;

    @Override
    protected IGenericRepository<Cliente, Integer> getRepo() {
        return repo;
    }
}