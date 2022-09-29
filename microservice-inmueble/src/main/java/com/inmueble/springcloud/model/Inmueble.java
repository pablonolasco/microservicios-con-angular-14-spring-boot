package com.inmueble.springcloud.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Inmueble {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "i_id_inmueble", nullable = false)
	private Long id;
	
	@Column(name = "c_nombre", length = 150, nullable = false)
	private String nombre;
	
	@Column(name = "c_direccion", length = 500, nullable = false)
	private String direccion;
	
	@Column(name = "c_foto", length = 120, nullable = true)
	private String picture;
	
	@Column(name = "c_precio", nullable = true)
	private Double precio;
	
	@Column(name = "d_fecha_alta", nullable = false)
	private LocalDateTime fechaCreacion;
}
