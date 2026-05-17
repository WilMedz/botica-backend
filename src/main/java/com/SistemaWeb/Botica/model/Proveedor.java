package com.SistemaWeb.Botica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proveedores")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idProveedor;

	@Column(nullable = false, length = 120)
	private String razonSocial;

	@Column(nullable = true, length = 20)
	private String ruc;

	@Column(nullable = true, length = 150)
	private String direccion;

	@Column(nullable = true, length = 20)
	private String telefono;

	@Column(nullable = true, length = 100)
	private String email;

	@Column(nullable = false)
	private boolean estado;
}
