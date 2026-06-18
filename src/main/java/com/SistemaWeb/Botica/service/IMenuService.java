package com.SistemaWeb.Botica.service;

import com.SistemaWeb.Botica.model.Menu;
import java.util.List;

public interface IMenuService extends IGenericService<Menu, Integer> {
    
    
    List<Menu> findMenusByRol(String nombreRol);
}
