package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.RolDTO;
import com.SistemaWeb.Botica.model.Rol;
import com.SistemaWeb.Botica.service.IRolService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolController {
    private final IRolService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<RolDTO>> findAll() throws Exception {
        List<RolDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, RolDTO.class)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Rol obj = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(obj, RolDTO.class));
    }

    @PostMapping
    public ResponseEntity<RolDTO> save(@Valid @RequestBody RolDTO dto) throws Exception {
        Rol obj = service.save(modelMapper.map(dto, Rol.class));
        RolDTO resultDto = modelMapper.map(obj, RolDTO.class);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdRol()).toUri();
        return ResponseEntity.created(location).body(resultDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> update(@Valid @RequestBody RolDTO dto, @PathVariable("id") Integer id) throws Exception {
        Rol obj = modelMapper.map(dto, Rol.class);
        obj.setIdRol(id);
        Rol updated = service.update(obj, id);
        return ResponseEntity.ok(modelMapper.map(updated, RolDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
