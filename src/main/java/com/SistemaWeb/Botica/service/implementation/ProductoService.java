package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Producto;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.repository.IProductoRepository;
import com.SistemaWeb.Botica.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoService extends GenericService<Producto, Integer> implements IProductoService {
    private final IProductoRepository repo;

    @Override
    protected IGenericRepository<Producto, Integer> getRepo() {
        return repo;
    }
}