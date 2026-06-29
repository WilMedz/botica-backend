package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Categoria;
import com.SistemaWeb.Botica.repository.ICategoriaRepository;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService extends GenericService<Categoria, Integer> implements ICategoriaService {
    private final ICategoriaRepository repo;

    @Override
    protected IGenericRepository<Categoria, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Categoria> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}