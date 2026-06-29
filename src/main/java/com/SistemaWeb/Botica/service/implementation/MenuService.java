// MenuService.java
package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Menu;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.repository.IMenuRepository;
import com.SistemaWeb.Botica.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService extends GenericService<Menu, Integer> implements IMenuService {

    private final IMenuRepository repo;

    @Override
    protected IGenericRepository<Menu, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Menu> findMenusByRol(String nombreRol) {
        return repo.findMenusByRol(nombreRol);
    }

    @Override
    public Page<Menu> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}