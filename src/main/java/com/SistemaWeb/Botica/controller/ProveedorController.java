package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.ProveedorDTO;
import com.SistemaWeb.Botica.model.Proveedor;
import com.SistemaWeb.Botica.service.IProveedorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/proveedores")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ProveedorController {

    private final IProveedorService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<CollectionModel<ProveedorDTO>> findAll() {
        List<ProveedorDTO> list = service.findAll().stream()
                .map(e -> {
                    ProveedorDTO dto = modelMapper.map(e, ProveedorDTO.class);
                    // Agrega /proveedores/{id} a cada elemento individual de la lista
                    dto.add(linkTo(methodOn(ProveedorController.class).findById(dto.getIdProveedor())).withSelfRel());
                    return dto;
                }).toList();

        // Agrega el link global /proveedores a la colección completa
        CollectionModel<ProveedorDTO> result = CollectionModel.of(list, 
                linkTo(methodOn(ProveedorController.class).findAll()).withSelfRel());
                
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> findById(@PathVariable("id") Integer id) {
        Proveedor obj = service.findById(id);
        ProveedorDTO dto = modelMapper.map(obj, ProveedorDTO.class);
        
        // Agrega el enlace propio /proveedores/{id}
        dto.add(linkTo(methodOn(ProveedorController.class).findById(id)).withSelfRel());
        
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO> save(@RequestBody ProveedorDTO dto) {
        Proveedor obj = service.save(modelMapper.map(dto, Proveedor.class));
        ProveedorDTO resultDto = modelMapper.map(obj, ProveedorDTO.class);
        
        // Agrega el enlace del recurso recién creado
        resultDto.add(linkTo(methodOn(ProveedorController.class).findById(resultDto.getIdProveedor())).withSelfRel());
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(resultDto.getIdProveedor()).toUri();
                
        return ResponseEntity.created(location).body(resultDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> update(@RequestBody ProveedorDTO dto, @PathVariable("id") Integer id) {
        // Forzamos que el objeto lleve el ID de la URL para evitar discrepancias
        dto.setIdProveedor(id);
        Proveedor obj = service.update(modelMapper.map(dto, Proveedor.class), id);
        ProveedorDTO resultDto = modelMapper.map(obj, ProveedorDTO.class);
        
        // Agrega el enlace propio actualizado
        resultDto.add(linkTo(methodOn(ProveedorController.class).findById(id)).withSelfRel());
        
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
