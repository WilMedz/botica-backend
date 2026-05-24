package com.SistemaWeb.Botica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaDTO {

    private Integer idDetalleVenta;
    private Integer idVenta;       // Solo ID, reemplaza @JsonBackReference
    private Integer idProducto;    // Solo ID del producto
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}
