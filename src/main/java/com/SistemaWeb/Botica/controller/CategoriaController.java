package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.CategoriaDTO;
import com.SistemaWeb.Botica.model.Categoria;
import com.SistemaWeb.Botica.service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
// @CrossOrigin(origins = { "http://localhost:4200","http://localhost:4201"})
public class CategoriaController {
    private final ICategoriaService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() throws Exception {
        //List<Categoria> list = service.findAll();
        //ModelMapper modelMapper = new ModelMapper();
        //List<CategoriaDTO> list = service.findAll().stream().map(e -> new CategoriaDTO(e.getIdCategoria(), e.getNombre(), e.getDescripcion(), e.getEstado())).toList();
        List<CategoriaDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, CategoriaDTO.class)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Categoria obj = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(obj, CategoriaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CategoriaDTO dto) throws Exception {
        Categoria obj = service.save(modelMapper.map(dto, Categoria.class));
        // http://localhost:8181/categories/6
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCategoria()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO>  update(@RequestBody CategoriaDTO dto, @PathVariable("id") Integer id) throws Exception {
         Categoria obj = service.update(modelMapper.map(dto, Categoria.class), id);
         return ResponseEntity.ok(modelMapper.map(obj, CategoriaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}