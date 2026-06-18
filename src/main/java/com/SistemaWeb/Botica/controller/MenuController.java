package com.SistemaWeb.Botica.controller;

import com.SistemaWeb.Botica.dto.MenuDTO;
import com.SistemaWeb.Botica.model.Menu;
import com.SistemaWeb.Botica.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/menus")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class MenuController {

    private final IMenuService service;
    private final ModelMapper modelMapper;

    @GetMapping("/user")
    public ResponseEntity<List<MenuDTO>> getMenusByUser() throws Exception {
       
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        
        String nombreRol = authentication.getAuthorities().stream()
                .map(auth -> auth.getAuthority()) 
                .findFirst()
                .orElse("USER");

       
        List<Menu> menus = service.findMenusByRol(nombreRol);

        
        List<MenuDTO> menusDTO = menus.stream()
                .map(m -> modelMapper.map(m, MenuDTO.class))
                .toList();

        return ResponseEntity.ok(menusDTO);
    }
}
