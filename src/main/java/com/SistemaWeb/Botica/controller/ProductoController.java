package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.model.Producto;
import com.SistemaWeb.Botica.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductoController {
    private final IProductoService service;

    @GetMapping
    public List<Producto> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Producto findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Producto save(@RequestBody Producto producto) throws Exception {
        return service.save(producto);
    }

    @PutMapping("/{id}")
    public Producto update(@RequestBody Producto producto, @PathVariable("id") Integer id) throws Exception {
        return service.update(producto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
    }
}