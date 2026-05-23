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

    private Integer idProveedor;
    private String razonSocial;
    private String ruc;
    private String direccion;
    private String telefono;
    private String email;
    private Boolean estado; 
}