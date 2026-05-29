package com.SistemaWeb.Botica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO extends RepresentationModel<CategoriaDTO> {
    private Integer idCategoria;
    private String nombre;
    private String descripcion;
    private Boolean estado;
}