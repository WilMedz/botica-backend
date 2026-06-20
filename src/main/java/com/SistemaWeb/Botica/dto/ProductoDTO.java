package com.SistemaWeb.Botica.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotNull
    private String codigoBarra;

    private String descripcion;

    @NotNull
    @Positive(message = "El precio de compra debe ser mayor a 0")
    private BigDecimal precioCompra;

    @NotNull
    @Positive(message = "El precio de venta debe ser mayor a 0")
    private BigDecimal precioVenta;

    @NotNull
    private Integer stock;

    @NotNull
    private Integer stockMinimo;

    @NotNull
    private Integer idCategoria;

    private Integer idProveedor;

    @NotNull
    private Boolean estado;

    @NotNull
    private LocalDate fechaVencimiento;
}