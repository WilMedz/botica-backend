package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.ProveedorDTO;
import com.SistemaWeb.Botica.model.Proveedor;
import com.SistemaWeb.Botica.service.IProveedorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/proveedores")
@RequiredArgsConstructor
public class ProveedorController {
    private final IProveedorService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> findAll() throws Exception {
        List<ProveedorDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, ProveedorDTO.class)).toList();
        return ResponseEntity.ok(list);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Proveedor obj = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(obj, ProveedorDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProveedorDTO dto) throws Exception {
        Proveedor obj = service.save(modelMapper.map(dto, Proveedor.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdProveedor()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> update(@RequestBody ProveedorDTO dto,@PathVariable("id") Integer id) throws Exception {
        Proveedor obj = service.update(modelMapper.map(dto, Proveedor.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, ProveedorDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}