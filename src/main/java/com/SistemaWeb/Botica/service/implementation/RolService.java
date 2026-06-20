package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Rol;
import com.SistemaWeb.Botica.repository.IRolRepository;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolService extends GenericService<Rol, Integer> implements IRolService {
    private final IRolRepository repo;

    @Override
    protected IGenericRepository<Rol, Integer> getRepo() {
        return repo;
    }
}