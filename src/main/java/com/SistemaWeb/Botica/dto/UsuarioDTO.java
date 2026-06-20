package com.SistemaWeb.Botica.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Integer idUsuario;

    @NotNull(message = "El nombre no debe ser nulo")
    @NotBlank(message = "El nombre no debe estar en blanco")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotNull(message = "El apellido no debe ser nulo")
    @NotBlank(message = "El apellido no debe estar en blanco")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    private String apellido;

    @NotNull(message = "El username no debe ser nulo")
    @NotBlank(message = "El username no debe estar en blanco")
    @Size(min = 3, max = 80, message = "El username debe tener entre 3 y 80 caracteres")
    private String username;

    @Size(min = 4, max = 200, message = "La contraseña debe tener entre 4 y 200 caracteres")
    private String password;

    @Email(message = "El formato del correo electrónico es inválido")
    @Size(max = 120, message = "El email no puede superar los 120 caracteres")
    private String email;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    @NotNull(message = "El rol es obligatorio")
    private Integer idRol;
}