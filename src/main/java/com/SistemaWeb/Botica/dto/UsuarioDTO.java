package com.SistemaWeb.Botica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private String email;
    private Boolean estado;
    private Integer idRol; // Solo el ID, no el objeto completo
}