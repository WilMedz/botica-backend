package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.ProductoDTO;
import com.SistemaWeb.Botica.model.Producto;
import com.SistemaWeb.Botica.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final IProductoService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<CollectionModel<ProductoDTO>> findAll() {
        List<ProductoDTO> list = service.findAll().stream()
                .map(e -> {
                    ProductoDTO dto = modelMapper.map(e, ProductoDTO.class);
                    dto.add(linkTo(methodOn(ProductoController.class).findById(dto.getIdProducto())).withSelfRel());
                    return dto;
                }).toList();

        CollectionModel<ProductoDTO> result = CollectionModel.of(list,
                linkTo(methodOn(ProductoController.class).findAll()).withSelfRel());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable("id") Integer id) {
        Producto obj = service.findById(id);
        ProductoDTO dto = modelMapper.map(obj, ProductoDTO.class);
        dto.add(linkTo(methodOn(ProductoController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> save(@RequestBody ProductoDTO dto) {
        Producto obj = service.save(modelMapper.map(dto, Producto.class));

        ProductoDTO resultDto = modelMapper.map(obj, ProductoDTO.class);
        resultDto.add(linkTo(methodOn(ProductoController.class)
        .findById(resultDto.getIdProducto())).withSelfRel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getIdProducto()).toUri();
        return ResponseEntity.created(location).body(resultDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update(@RequestBody ProductoDTO dto, @PathVariable("id") Integer id) {
        dto.setIdProducto(id);
        Producto obj = service.update(modelMapper.map(dto, Producto.class), id);
        ProductoDTO resultDto = modelMapper.map(obj, ProductoDTO.class);
        resultDto.add(linkTo(methodOn(ProductoController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}