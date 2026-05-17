package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.model.Venta;
import com.SistemaWeb.Botica.service.IVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VentaController {
    private final IVentaService service;

    @GetMapping
    public List<Venta> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Venta findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Venta save(@RequestBody Venta venta) throws Exception {
        return service.save(venta);
    }

    @PutMapping("/{id}")
    public Venta update(@RequestBody Venta venta, @PathVariable("id") Integer id) throws Exception {
        return service.update(venta, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
    }
}