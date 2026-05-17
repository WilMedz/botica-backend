package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.model.Categoria;
import com.SistemaWeb.Botica.service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoriaController {
    private final ICategoriaService service;

    @GetMapping
    public List<Categoria> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Categoria findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Categoria save(@RequestBody Categoria categoria) throws Exception {
        return service.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria update(@RequestBody Categoria categoria, @PathVariable("id") Integer id) throws Exception {
        return service.update(categoria, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
    }
}