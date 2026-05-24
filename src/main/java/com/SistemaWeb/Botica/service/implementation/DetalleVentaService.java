package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.DetalleVenta;
import com.SistemaWeb.Botica.repository.IDetalleVentaRepository;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.service.IDetalleVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetalleVentaService extends GenericService<DetalleVenta, Integer> implements IDetalleVentaService {
    private final IDetalleVentaRepository repo;

    @Override
    protected IGenericRepository<DetalleVenta, Integer> getRepo() {
        return repo;
    }

}