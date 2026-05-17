package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Producto;
import com.SistemaWeb.Botica.repository.IProductoRepository;
import com.SistemaWeb.Botica.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService implements IProductoService {
    private final IProductoRepository repo;

    @Override
    public Producto save(Producto producto) throws Exception {
        return repo.save(producto);
    }

    @Override
    public Producto update(Producto producto, Integer id) throws Exception {
        producto.setIdProducto(id);
        return repo.save(producto);
    }

    @Override
    public List<Producto> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Producto findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Producto());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}