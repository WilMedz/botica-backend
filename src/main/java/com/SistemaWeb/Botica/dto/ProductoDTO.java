package com.SistemaWeb.Botica.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "productos")
public class ProductoDTO extends RepresentationModel<ProductoDTO> {

    private Integer idProducto;
    private String nombre;
    private String codigoBarra;
    private String descripcion;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private Integer stock;
    private Integer stockMinimo;
    private Integer idCategoria;
    private Integer idProveedor;
    private Boolean estado;
    private LocalDate fechaVencimiento;
}