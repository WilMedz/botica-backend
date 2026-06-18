package com.SistemaWeb.Botica.service;

import com.SistemaWeb.Botica.model.Usuario;

public interface IUsuarioService extends IGenericService<Usuario,Integer> {
    Usuario findOneByUsername(String username);
    void changePassword(String username, String password);
}