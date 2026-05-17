package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Cliente;
import com.SistemaWeb.Botica.repository.IClienteRepository;
import com.SistemaWeb.Botica.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService {
    private final IClienteRepository repo;

    @Override
    public Cliente save(Cliente cliente) throws Exception {
        return repo.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente, Integer id) throws Exception {
        cliente.setIdCliente(id);
        return repo.save(cliente);
    }

    @Override
    public List<Cliente> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Cliente findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Cliente());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}