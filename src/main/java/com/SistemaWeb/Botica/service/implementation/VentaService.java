package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Venta;
import com.SistemaWeb.Botica.repository.IVentaRepository;
import com.SistemaWeb.Botica.service.IVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService implements IVentaService {
    private final IVentaRepository repo;

    @Override
    public Venta save(Venta venta) throws Exception {
        return repo.save(venta);
    }

    @Override
    public Venta update(Venta venta, Integer id) throws Exception {
        venta.setIdVenta(id);
        return repo.save(venta);
    }

    @Override
    public List<Venta> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Venta findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Venta());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}