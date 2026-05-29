package com.SistemaWeb.Botica.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "clientes")
public class ClienteDTO extends RepresentationModel<ClienteDTO> {
    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String documento;
    private String telefono;
    private String email;
    private Boolean estado;
}