package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Usuario;
import com.SistemaWeb.Botica.repository.IUsuarioRepository;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService extends GenericService<Usuario, Integer> implements IUsuarioService {
    private final IUsuarioRepository repo;

    @Override
    protected IGenericRepository<Usuario, Integer> getRepo() {
        return repo;
    }

}