package com.SistemaWeb.Botica.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
    private Integer idMenu;

    @NotNull
    private String icono;

    @NotNull
    private String nombre;

    @NotNull
    private String url;
}