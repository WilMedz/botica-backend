package com.SistemaWeb.Botica.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "proveedores")
public class ProveedorDTO extends RepresentationModel<ProveedorDTO> {

    private Integer idProveedor;
    private String razonSocial;
    private String ruc;
    private String direccion;
    private String telefono;
    private String email;
    private Boolean estado; 
}
