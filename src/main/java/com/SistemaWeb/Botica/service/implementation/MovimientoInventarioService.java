package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.MovimientoInventario;
import com.SistemaWeb.Botica.repository.IMovimientoInventarioRepository;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.service.IMovimientoInventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovimientoInventarioService extends GenericService<MovimientoInventario, Integer> implements IMovimientoInventarioService {
    private final IMovimientoInventarioRepository repo;

    @Override
    protected IGenericRepository<MovimientoInventario, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<MovimientoInventario> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

}