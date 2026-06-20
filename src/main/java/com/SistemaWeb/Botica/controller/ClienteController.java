package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.ClienteDTO;
import com.SistemaWeb.Botica.model.Cliente;
import com.SistemaWeb.Botica.service.IClienteService;

import jakarta.validation.Valid;
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
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final IClienteService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<CollectionModel<ClienteDTO>> findAll() {
        List<ClienteDTO> list = service.findAll().stream()
                .map(e -> {
                    ClienteDTO dto = modelMapper.map(e, ClienteDTO.class);
                    dto.add(linkTo(methodOn(ClienteController.class).findById(dto.getIdCliente())).withSelfRel());
                    return dto;
                }).toList();

        CollectionModel<ClienteDTO> result = CollectionModel.of(list,
                linkTo(methodOn(ClienteController.class).findAll()).withSelfRel());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable("id") Integer id) {
        Cliente obj = service.findById(id);
        ClienteDTO dto = modelMapper.map(obj, ClienteDTO.class);
        dto.add(linkTo(methodOn(ClienteController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> save(@Valid @RequestBody ClienteDTO dto) {
        Cliente obj = service.save(modelMapper.map(dto, Cliente.class));
        ClienteDTO resultDto = modelMapper.map(obj, ClienteDTO.class);
        resultDto.add(linkTo(methodOn(ClienteController.class).findById(resultDto.getIdCliente())).withSelfRel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCliente()).toUri();
        return ResponseEntity.created(location).body(resultDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@Valid @RequestBody ClienteDTO dto, @PathVariable("id") Integer id) {
        Cliente obj = service.update(modelMapper.map(dto, Cliente.class), id);
        ClienteDTO resultDto = modelMapper.map(obj, ClienteDTO.class);
        resultDto.add(linkTo(methodOn(ClienteController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}