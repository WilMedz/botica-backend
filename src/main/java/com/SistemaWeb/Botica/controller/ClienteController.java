package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.ClienteDTO;
import com.SistemaWeb.Botica.model.Cliente;
import com.SistemaWeb.Botica.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final IClienteService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() throws Exception {
        List<ClienteDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, ClienteDTO.class)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Cliente obj = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(obj, ClienteDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ClienteDTO dto) throws Exception {
        Cliente obj = service.save(modelMapper.map(dto, Cliente.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCliente()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@RequestBody ClienteDTO dto,@PathVariable("id") Integer id) throws Exception {
        Cliente obj = service.update(modelMapper.map(dto, Cliente.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, ClienteDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}