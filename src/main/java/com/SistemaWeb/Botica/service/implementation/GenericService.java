package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.exception.ModelNotFoundException;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.service.IGenericService;
import java.util.List;

public abstract class GenericService<T, ID> implements IGenericService<T, ID> {

    protected abstract IGenericRepository<T, ID> getRepo();

    @Override
    public T save(T t) {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) {
        this.findById(id);
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) {
        return getRepo().findById(id)
                .orElseThrow(() -> new ModelNotFoundException("ID no encontrado: " + id));
    }

    @Override
    public void delete(ID id) {
        this.findById(id);
        getRepo().deleteById(id);
    }
}
