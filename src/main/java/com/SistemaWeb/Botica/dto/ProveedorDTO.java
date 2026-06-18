package com.SistemaWeb.Botica.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "La razón social no debe ser nula")
    @NotBlank(message = "La razón social no debe estar en blanco")
    @Size(min = 3, max = 120, message = "La razón social debe tener entre 3 y 120 caracteres")
    private String razonSocial;

    @Size(max = 20, message = "El RUC no puede superar los 20 caracteres")
    private String ruc;

    @Size(max = 150, message = "La dirección no puede superar los 150 caracteres")
    private String direccion;

    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    private String telefono;

    @Email(message = "El formato del correo electrónico es inválido")
    @Size(max = 100, message = "El email no puede superar los 100 caracteres")
    private String email;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado; 
}
