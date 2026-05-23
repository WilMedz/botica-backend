package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Categoria;
import com.SistemaWeb.Botica.repository.ICategoriaRepository;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService extends GenericService<Categoria,Integer> implements ICategoriaService{
    private final ICategoriaRepository repo;

    @Override
    protected IGenericRepository <Categoria,Integer> getRepo(){
        return repo;
    }

    /*
    @Override
    public Categoria update(Categoria categoria, Integer id) throws Exception {
        categoria.setIdCategoria(id);
        return repo.save(categoria);
    }

    @Override
    public List<Categoria> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Categoria findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Categoria());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }*/
}