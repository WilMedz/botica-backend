package com.SistemaWeb.Botica.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movimientos_inventario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MovimientoInventario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idMovimiento;

	@ManyToOne
	@JoinColumn(name = "id_producto", nullable = false)
	private Producto producto;

	@Column(nullable = false, length = 20)
	private String tipo; // ENTRADA | SALIDA | AJUSTE

	@Column(nullable = false)
	private Integer cantidad;

	@Column(nullable = false)
	private Integer stockAnterior;

	@Column(nullable = false)
	private Integer stockNuevo;

	@Column(nullable = true, length = 250)
	private String motivo;

	@Column(nullable = false)
	private LocalDateTime fecha;
}
