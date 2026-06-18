package com.SistemaWeb.Botica.repository;

import com.SistemaWeb.Botica.model.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IMenuRepository extends IGenericRepository<Menu, Integer> {

   
    @Query("SELECT m FROM Menu m JOIN m.roles r WHERE r.nombre = :nombreRol")
    List<Menu> findMenusByRol(@Param("nombreRol") String nombreRol);
}
