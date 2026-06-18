package com.SistemaWeb.Botica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolDTO {

    private Integer idRol;

    @NotNull(message = "El nombre no debe ser nulo")
    @NotBlank(message = "El nombre no debe estar en blanco")
    @Size(min = 2, max = 30, message = "El nombre debe tener entre 2 y 30 caracteres")
    private String nombre;
}