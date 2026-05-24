package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.ProductoDTO;
import com.SistemaWeb.Botica.model.Producto;
import com.SistemaWeb.Botica.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final IProductoService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> findAll() throws Exception {
        List<ProductoDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, ProductoDTO.class)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Producto obj = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(obj, ProductoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProductoDTO dto) throws Exception {
        Producto obj = service.save(modelMapper.map(dto, Producto.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdProducto()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update(@RequestBody ProductoDTO dto,@PathVariable("id") Integer id) throws Exception {
        Producto obj = service.update(modelMapper.map(dto, Producto.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, ProductoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}