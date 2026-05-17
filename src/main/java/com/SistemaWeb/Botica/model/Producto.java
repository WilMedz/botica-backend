package com.SistemaWeb.Botica.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name = "productos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idProducto;

	@Column(nullable = false, length = 150)
	private String nombre;

	@Column(nullable = true, length = 20)
	private String codigoBarra;

	@Column(nullable = true, length = 300)
	private String descripcion;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal precioCompra;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal precioVenta;

	@Column(nullable = false)
	private Integer stock;

	@Column(nullable = false)
	private Integer stockMinimo;

	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "id_proveedor", nullable = true)
	private Proveedor proveedor;

	@Column(nullable = false)
	private boolean estado;

	@Column (nullable=true )
	private LocalDate fechaVencimiento;
}
