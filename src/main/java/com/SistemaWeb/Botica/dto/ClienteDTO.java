package com.SistemaWeb.Botica.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "El nombre no debe ser nulo")
    @NotBlank(message = "El nombre no debe estar en blanco")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotNull(message = "El apellido no debe ser nulo")
    @NotBlank(message = "El apellido no debe estar en blanco")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    private String apellido;

    @Size(max = 20, message = "El documento no puede superar los 20 caracteres")
    private String documento;

    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    private String telefono;

    @Email(message = "El formato del correo electrónico es inválido")
    @Size(max = 120, message = "El email no puede superar los 120 caracteres")
    private String email;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;
}