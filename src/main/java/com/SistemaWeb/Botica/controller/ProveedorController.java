package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.model.Proveedor;
import com.SistemaWeb.Botica.service.IProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProveedorController {
    private final IProveedorService service;

    @GetMapping
    public List<Proveedor> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Proveedor findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Proveedor save(@RequestBody Proveedor proveedor) throws Exception {
        return service.save(proveedor);
    }

    @PutMapping("/{id}")
    public Proveedor update(@RequestBody Proveedor proveedor, @PathVariable("id") Integer id) throws Exception {
        return service.update(proveedor, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
    }
}