package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.UsuarioDTO;
import com.SistemaWeb.Botica.model.Usuario;
import com.SistemaWeb.Botica.model.Rol;
import com.SistemaWeb.Botica.service.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.core.Authentication;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final IUsuarioService service;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<UsuarioDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/pageable")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Page<Usuario>> findAllPageable(Pageable pageable) {
        Page<Usuario> page = service.listPage(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> me(Authentication authentication) {
        String username = authentication.getName();
        Usuario obj = service.findOneByUsername(username);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Integer id) {
        Usuario obj = service.findById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioDTO dto) {
        Usuario obj = convertToEntity(dto);
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        Usuario saved = service.save(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getIdUsuario()).toUri();
        return ResponseEntity.created(location).body(convertToDto(saved));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTO> update(@Valid @RequestBody UsuarioDTO dto, @PathVariable("id") Integer id) {
        Usuario obj = convertToEntity(dto);
        obj.setIdUsuario(id);
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            Usuario existente = service.findById(id);
            obj.setPassword(existente.getPassword());
        } else {
            obj.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        Usuario updated = service.update(obj, id);
        return ResponseEntity.ok(convertToDto(updated));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private UsuarioDTO convertToDto(Usuario entity) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(entity.getIdUsuario());
        dto.setNombre(entity.getNombres());
        dto.setApellido(entity.getApellidos());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setEstado(entity.getEstado());
        if (entity.getRol() != null) {
            dto.setIdRol(entity.getRol().getIdRol());
        }
        return dto;
    }

    private Usuario convertToEntity(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        entity.setIdUsuario(dto.getIdUsuario());
        entity.setNombres(dto.getNombre());
        entity.setApellidos(dto.getApellido());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setEstado(dto.getEstado());
        if (dto.getIdRol() != null) {
            Rol r = new Rol();
            r.setIdRol(dto.getIdRol());
            entity.setRol(r);
        }
        return entity;
    }
}