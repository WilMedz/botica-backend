package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Proveedor;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.repository.IProveedorRepository;
import com.SistemaWeb.Botica.service.IProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProveedorService extends GenericService<Proveedor, Integer> implements IProveedorService {
    private final IProveedorRepository repo;

    @Override
    protected IGenericRepository<Proveedor, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Proveedor> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}