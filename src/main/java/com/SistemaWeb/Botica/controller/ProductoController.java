package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.ProductoDTO;
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
    public List<ProductoDTO> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ProductoDTO findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public ProductoDTO save(@RequestBody ProductoDTO dto) throws Exception {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public ProductoDTO update(@RequestBody ProductoDTO dto, @PathVariable("id") Integer id) throws Exception {
        return service.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
    }
}