package com.SistemaWeb.Botica.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SistemaWeb.Botica.model.Usuario;

import jakarta.transaction.Transactional;

public interface IUsuarioRepository extends IGenericRepository<Usuario,Integer>{
    Usuario findOneByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.password = :password WHERE u.username = :username")
    void changePassword(@Param("username") String username, @Param("password") String newPassword);
}
