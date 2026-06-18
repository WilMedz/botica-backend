package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Usuario;
import com.SistemaWeb.Botica.repository.IUsuarioRepository;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService extends GenericService<Usuario, Integer> implements IUsuarioService {
    private final IUsuarioRepository repo;
    private final PasswordEncoder bcrypt;

    @Override
    protected IGenericRepository<Usuario, Integer> getRepo() {
        return repo;
    }

    @Override
    public Usuario findOneByUsername(String username) {
        return repo.findOneByUsername(username);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        repo.changePassword(username, bcrypt.encode(newPassword));
    }

}