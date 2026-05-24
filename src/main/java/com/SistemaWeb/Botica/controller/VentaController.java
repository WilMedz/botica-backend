package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.VentaDTO;
import com.SistemaWeb.Botica.model.Venta;
import com.SistemaWeb.Botica.service.IVentaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentaController {
    private final IVentaService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> findAll() throws Exception {
        List<VentaDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, VentaDTO.class)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Venta obj = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(obj, VentaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody VentaDTO dto) throws Exception {
        Venta obj = service.save(modelMapper.map(dto, Venta.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdVenta()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> update(@RequestBody VentaDTO dto, @PathVariable("id") Integer id) throws Exception {
        Venta obj = service.update(modelMapper.map(dto, Venta.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, VentaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}