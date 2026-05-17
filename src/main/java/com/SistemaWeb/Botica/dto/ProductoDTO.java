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
    private Boolean estado;
    private LocalDate fechaVencimiento;
    private Integer idCategoria;
    private String nombreCategoria;
    private Integer idProveedor;
    private String razonSocialProveedor; 
}