package com.SistemaWeb.Botica.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.SistemaWeb.Botica.model.Menu;

public interface IMenuRepository extends IGenericRepository<Menu, Integer> {
    @Query("FROM Menu m JOIN m.roles r WHERE r.nombre = :nombreRol")
    List<Menu> findMenusByRol(@Param("nombreRol") String nombreRol);

}
