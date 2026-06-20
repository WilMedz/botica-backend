package com.SistemaWeb.Botica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {

    private Integer idVenta;
    private LocalDateTime fecha;
    private BigDecimal total;
    private String estado;
    private String observacion;
    private Integer idCliente;
    private String nombreCliente;
    private List<DetalleVentaDTO> detalles;
}