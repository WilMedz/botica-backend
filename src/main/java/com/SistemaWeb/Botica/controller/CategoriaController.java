package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.CategoriaDTO;
import com.SistemaWeb.Botica.model.Categoria;
import com.SistemaWeb.Botica.service.ICategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final ICategoriaService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<CollectionModel<CategoriaDTO>> findAll() {
        List<CategoriaDTO> list = service.findAll().stream()
                .map(e -> {
                    CategoriaDTO dto = modelMapper.map(e, CategoriaDTO.class);
                    dto.add(linkTo(methodOn(CategoriaController.class).findById(dto.getIdCategoria())).withSelfRel());
                    return dto;
                }).toList();

        CollectionModel<CategoriaDTO> result = CollectionModel.of(list,
                linkTo(methodOn(CategoriaController.class).findAll()).withSelfRel());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable("id") Integer id) {
        Categoria obj = service.findById(id);
        CategoriaDTO dto = modelMapper.map(obj, CategoriaDTO.class);
        dto.add(linkTo(methodOn(CategoriaController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<CategoriaDTO> save(@Valid @RequestBody CategoriaDTO dto) {
        Categoria obj = service.save(modelMapper.map(dto, Categoria.class));
        CategoriaDTO resultDto = modelMapper.map(obj, CategoriaDTO.class);

        resultDto.add(linkTo(methodOn(CategoriaController.class).findById(resultDto.getIdCategoria())).withSelfRel());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(obj.getIdCategoria()).toUri();
            
        return ResponseEntity.created(location).body(resultDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<CategoriaDTO> update(@Valid @RequestBody CategoriaDTO dto, @PathVariable("id") Integer id) {
        Categoria obj = service.update(modelMapper.map(dto, Categoria.class), id);
        CategoriaDTO resultDto = modelMapper.map(obj, CategoriaDTO.class);
        resultDto.add(linkTo(methodOn(CategoriaController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Categoria>> findAllPageable(Pageable pageable) {
        Page<Categoria> page = service.listPage(pageable);
        return ResponseEntity.ok(page);
    }
}