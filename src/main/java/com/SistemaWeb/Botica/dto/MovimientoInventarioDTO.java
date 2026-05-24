package com.SistemaWeb.Botica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventarioDTO {

    private Integer idMovimiento;
    private Integer idProducto;    // Solo ID del producto
    private String tipo;           // ENTRADA | SALIDA | AJUSTE
    private Integer cantidad;
    private Integer stockAnterior;
    private Integer stockNuevo;
    private String motivo;
    private LocalDateTime fecha;
}