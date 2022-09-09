package com.compra.springcloud.modelos;

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
@Table(name = "compras")
public class CompraEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "usuario_id_i", nullable = false)
	private Long usuarioId;
	
	@Column(name = "inmueble_id_i", nullable = false)
	private Long inmuebleId;
	
	private String titulo;
	
	private Double precio;
	
	@Column(name = "d_fecha_alta", nullable = true)
	private LocalDateTime fechaCompra;
	
}
