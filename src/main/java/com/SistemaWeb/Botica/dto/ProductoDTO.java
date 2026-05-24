package com.SistemaWeb.Botica.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Integer idProducto;
    private String nombre;
    private String codigoBarra;
    private String descripcion;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private Integer stock;
    private Integer stockMinimo;
    private Integer idCategoria;   // Solo ID, evita referencia circular
    private Integer idProveedor;   // Solo ID, evita referencia circular
    private Boolean estado;
    private LocalDate fechaVencimiento;
}