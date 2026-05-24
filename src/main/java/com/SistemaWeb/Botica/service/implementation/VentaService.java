package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Venta;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.repository.IVentaRepository;
import com.SistemaWeb.Botica.service.IVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VentaService extends GenericService<Venta, Integer> implements IVentaService {
    private final IVentaRepository repo;
    
    @Override
    protected IGenericRepository<Venta, Integer> getRepo() {
        return repo;
    }
}