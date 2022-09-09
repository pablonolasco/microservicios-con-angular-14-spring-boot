package com.compra.springcloud.modelos;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.compra.springcloud.enums.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

	@Id
	@Column(name = "i_id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	private String password;
	
	private String nombre;
	
	private LocalDateTime fechaAlta;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Rol rol;
}
