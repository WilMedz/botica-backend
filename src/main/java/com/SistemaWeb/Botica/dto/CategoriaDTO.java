package com.SistemaWeb.Botica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "categorias")
public class CategoriaDTO extends RepresentationModel<CategoriaDTO> {
    private Integer idCategoria;
    private String nombre;
    private String descripcion;
    private Boolean estado;
}