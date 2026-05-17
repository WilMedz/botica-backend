package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.model.Cliente;
import com.SistemaWeb.Botica.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClienteController {
    private final IClienteService service;

    @GetMapping
    public List<Cliente> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Cliente findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Cliente save(@RequestBody Cliente cliente) throws Exception {
        return service.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update(@RequestBody Cliente cliente, @PathVariable("id") Integer id) throws Exception {
        return service.update(cliente, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
    }
}