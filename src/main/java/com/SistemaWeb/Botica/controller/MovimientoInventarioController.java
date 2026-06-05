package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.MovimientoInventarioDTO;
import com.SistemaWeb.Botica.model.MovimientoInventario;
import com.SistemaWeb.Botica.model.Producto;
import com.SistemaWeb.Botica.service.IMovimientoInventarioService;
import com.SistemaWeb.Botica.repository.IProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movimientos-inventario")
@RequiredArgsConstructor
public class MovimientoInventarioController {
    private final IMovimientoInventarioService service;
    private final IProductoRepository productoRepo;

    @GetMapping
    public ResponseEntity<List<MovimientoInventarioDTO>> findAll() throws Exception {
        List<MovimientoInventarioDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventarioDTO> findById(@PathVariable("id") Integer id) throws Exception {
        MovimientoInventario obj = service.findById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @PostMapping
    public ResponseEntity<MovimientoInventarioDTO> save(@RequestBody MovimientoInventarioDTO dto) throws Exception {
        MovimientoInventario obj = convertToEntity(dto);
        
        // Si es una creación manual, ajustamos el stock del producto
        if (dto.getIdProducto() != null) {
            Producto prod = productoRepo.findById(dto.getIdProducto()).orElse(null);
            if (prod != null) {
                int stockAnterior = prod.getStock();
                int stockNuevo = stockAnterior;
                if ("ENTRADA".equalsIgnoreCase(dto.getTipo())) {
                    stockNuevo = stockAnterior + dto.getCantidad();
                } else if ("SALIDA".equalsIgnoreCase(dto.getTipo())) {
                    stockNuevo = stockAnterior - dto.getCantidad();
                } else if ("AJUSTE".equalsIgnoreCase(dto.getTipo())) {
                    stockNuevo = dto.getCantidad(); // En caso de ajuste directo
                }
                
                prod.setStock(stockNuevo);
                productoRepo.save(prod);
                
                obj.setStockAnterior(stockAnterior);
                obj.setStockNuevo(stockNuevo);
            }
        }
        
        MovimientoInventario saved = service.save(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getIdMovimiento()).toUri();
        return ResponseEntity.created(location).body(convertToDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoInventarioDTO> update(@RequestBody MovimientoInventarioDTO dto, @PathVariable("id") Integer id) throws Exception {
        MovimientoInventario obj = convertToEntity(dto);
        obj.setIdMovimiento(id);
        MovimientoInventario updated = service.update(obj, id);
        return ResponseEntity.ok(convertToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private MovimientoInventarioDTO convertToDto(MovimientoInventario entity) {
        MovimientoInventarioDTO dto = new MovimientoInventarioDTO();
        dto.setIdMovimiento(entity.getIdMovimiento());
        dto.setTipo(entity.getTipo());
        dto.setCantidad(entity.getCantidad());
        dto.setStockAnterior(entity.getStockAnterior());
        dto.setStockNuevo(entity.getStockNuevo());
        dto.setMotivo(entity.getMotivo());
        dto.setFecha(entity.getFecha());
        if (entity.getProducto() != null) {
            dto.setIdProducto(entity.getProducto().getIdProducto());
        }
        return dto;
    }

    private MovimientoInventario convertToEntity(MovimientoInventarioDTO dto) {
        MovimientoInventario entity = new MovimientoInventario();
        entity.setIdMovimiento(dto.getIdMovimiento());
        entity.setTipo(dto.getTipo());
        entity.setCantidad(dto.getCantidad());
        entity.setStockAnterior(dto.getStockAnterior());
        entity.setStockNuevo(dto.getStockNuevo());
        entity.setMotivo(dto.getMotivo());
        entity.setFecha(dto.getFecha());
        if (dto.getIdProducto() != null) {
            Producto p = new Producto();
            p.setIdProducto(dto.getIdProducto());
            entity.setProducto(p);
        }
        return entity;
    }
}
