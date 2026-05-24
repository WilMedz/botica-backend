package com.SistemaWeb.Botica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolDTO {

    private Integer idRol;
    private String nombre;
    private Boolean estado;
}