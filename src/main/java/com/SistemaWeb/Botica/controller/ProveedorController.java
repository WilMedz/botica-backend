package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.ProveedorDTO;
import com.SistemaWeb.Botica.model.Proveedor;
import com.SistemaWeb.Botica.service.IProveedorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid; 
import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final IProveedorService service;
    private final ModelMapper modelMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<CollectionModel<ProveedorDTO>> findAll() {
        List<ProveedorDTO> list = service.findAll().stream()
                .map(e -> {
                    ProveedorDTO dto = modelMapper.map(e, ProveedorDTO.class);
                    dto.add(linkTo(methodOn(ProveedorController.class).findById(dto.getIdProveedor())).withSelfRel());
                    return dto;
                }).toList();

        CollectionModel<ProveedorDTO> result = CollectionModel.of(list, 
                linkTo(methodOn(ProveedorController.class).findAll()).withSelfRel());
                
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ProveedorDTO> findById(@PathVariable("id") Integer id) {
        Proveedor obj = service.findById(id);
        ProveedorDTO dto = modelMapper.map(obj, ProveedorDTO.class);
        
        dto.add(linkTo(methodOn(ProveedorController.class).findById(id)).withSelfRel());
        
        return ResponseEntity.ok(dto);
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ProveedorDTO> save(@Valid @RequestBody ProveedorDTO dto) {
        Proveedor obj = service.save(modelMapper.map(dto, Proveedor.class));
        ProveedorDTO resultDto = modelMapper.map(obj, ProveedorDTO.class);
        
        resultDto.add(linkTo(methodOn(ProveedorController.class).findById(resultDto.getIdProveedor())).withSelfRel());
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(resultDto.getIdProveedor()).toUri();
                
        return ResponseEntity.created(location).body(resultDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ProveedorDTO> update(@Valid @RequestBody ProveedorDTO dto, @PathVariable("id") Integer id) {
        dto.setIdProveedor(id);
        Proveedor obj = service.update(modelMapper.map(dto, Proveedor.class), id);
        ProveedorDTO resultDto = modelMapper.map(obj, ProveedorDTO.class);
        
        resultDto.add(linkTo(methodOn(ProveedorController.class).findById(id)).withSelfRel());
        
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pageable")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Page<Proveedor>> findAllPageable(Pageable pageable) {
    Page<Proveedor> page = service.listPage(pageable);
    return ResponseEntity.ok(page);
}
}
