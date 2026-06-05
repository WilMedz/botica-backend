package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.VentaDTO;
import com.SistemaWeb.Botica.dto.DetalleVentaDTO;
import com.SistemaWeb.Botica.model.Venta;
import com.SistemaWeb.Botica.model.DetalleVenta;
import com.SistemaWeb.Botica.model.Cliente;
import com.SistemaWeb.Botica.model.Producto;
import com.SistemaWeb.Botica.service.IVentaService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public ResponseEntity<List<VentaDTO>> findAll() throws Exception {
        List<VentaDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Venta obj = service.findById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @PostMapping
    public ResponseEntity<VentaDTO> save(@RequestBody VentaDTO dto) throws Exception {
        Venta obj = convertToEntity(dto);
        Venta saved = service.save(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getIdVenta()).toUri();
        return ResponseEntity.created(location).body(convertToDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> update(@RequestBody VentaDTO dto, @PathVariable("id") Integer id) throws Exception {
        Venta obj = convertToEntity(dto);
        obj.setIdVenta(id);
        Venta updated = service.update(obj, id);
        return ResponseEntity.ok(convertToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private VentaDTO convertToDto(Venta entity) {
        VentaDTO dto = new VentaDTO();
        dto.setIdVenta(entity.getIdVenta());
        dto.setFecha(entity.getFecha());
        dto.setTotal(entity.getTotal());
        dto.setEstado(entity.getEstado());
        dto.setObservacion(entity.getObservacion());
        
        if (entity.getCliente() != null) {
            dto.setIdCliente(entity.getCliente().getIdCliente());
            dto.setNombreCliente(entity.getCliente().getNombre() + " " + entity.getCliente().getApellido());
        }
        
        if (entity.getDetalles() != null) {
            List<DetalleVentaDTO> detallesDto = entity.getDetalles().stream().map(d -> {
                DetalleVentaDTO dDto = new DetalleVentaDTO();
                dDto.setIdDetalleVenta(d.getIdDetalleVenta());
                dDto.setIdVenta(entity.getIdVenta());
                if (d.getProducto() != null) {
                    dDto.setIdProducto(d.getProducto().getIdProducto());
                }
                dDto.setCantidad(d.getCantidad());
                dDto.setPrecioUnitario(d.getPrecioUnitario());
                dDto.setSubtotal(d.getSubtotal());
                return dDto;
            }).toList();
            dto.setDetalles(detallesDto);
        }
        return dto;
    }

    private Venta convertToEntity(VentaDTO dto) {
        Venta entity = new Venta();
        entity.setIdVenta(dto.getIdVenta());
        entity.setFecha(dto.getFecha());
        entity.setTotal(dto.getTotal());
        entity.setEstado(dto.getEstado());
        entity.setObservacion(dto.getObservacion());
        
        if (dto.getIdCliente() != null) {
            Cliente c = new Cliente();
            c.setIdCliente(dto.getIdCliente());
            entity.setCliente(c);
        }
        
        if (dto.getDetalles() != null) {
            List<DetalleVenta> detalles = dto.getDetalles().stream().map(d -> {
                DetalleVenta det = new DetalleVenta();
                det.setIdDetalleVenta(d.getIdDetalleVenta());
                det.setVenta(entity);
                if (d.getIdProducto() != null) {
                    Producto p = new Producto();
                    p.setIdProducto(d.getIdProducto());
                    det.setProducto(p);
                }
                det.setCantidad(d.getCantidad());
                det.setPrecioUnitario(d.getPrecioUnitario());
                det.setSubtotal(d.getSubtotal());
                return det;
            }).toList();
            entity.setDetalles(detalles);
        }
        return entity;
    }
}