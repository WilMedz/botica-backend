package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Categoria;
import com.SistemaWeb.Botica.repository.ICategoriaRepository;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService extends GenericService<Cliente,Integer> implements IClienteService {
    private final IClienteRepository repo;

    @Override
    protected IGenericRepository <Cliente,Integer> getRepo() {
        return repo;
    }

}