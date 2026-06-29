package com.SistemaWeb.Botica.service;

import com.SistemaWeb.Botica.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IMenuService extends IGenericService<Menu, Integer> {
    List<Menu> findMenusByRol(String nombreRol);
    Page<Menu> listPage(Pageable pageable);
}