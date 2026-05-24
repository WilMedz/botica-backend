package com.SistemaWeb.Botica.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteDTO {
    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String documento;
    private String telefono;
    private String email;
    private Boolean estado;
}
