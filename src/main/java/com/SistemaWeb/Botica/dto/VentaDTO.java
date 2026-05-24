package com.SistemaWeb.Botica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {

    private Integer idVenta;
    private LocalDateTime fechaVenta;
    private Double totalVenta;
    private String tipoPago;
    private Boolean estado;
    private Integer idUsuario;  // Solo el ID del usuario que realizó la venta
    private Integer idCliente;  // Solo el ID del cliente
}