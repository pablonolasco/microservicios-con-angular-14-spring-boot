package com.compra.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compra.springcloud.enums.Rol;
import com.compra.springcloud.modelos.UsuarioEntity;
import com.compra.springcloud.security.UserPrincipal;
import com.compra.springcloud.service.AuthenticationService;
import com.compra.springcloud.service.UsuarioService;

@RestController
@RequestMapping("/api/")
public class UsuarioController {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/autenticacion/crear-cuenta")
	public ResponseEntity<?> crearCuenta(@RequestBody UsuarioEntity usuarioEntity) {
	if ((usuarioService.buscarPorUsername(usuarioEntity.getNombre())).isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(usuarioService.saveUsuario(usuarioEntity), HttpStatus.CREATED);
	}
	
	@PostMapping("/autenticacion/ingresar-sesion")
	public ResponseEntity<?>login(@RequestBody UsuarioEntity usuarioEntity){
		return new ResponseEntity<>(authenticationService.ingresarSesion(usuarioEntity),HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param userPrincipal usuario logueado con token valido
	 * @param rol
	 * @return
	 */
	@PutMapping("/usuario/actualizar-rol/{rol}")
	public ResponseEntity<?>cambiarRol(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Rol rol){
		usuarioService.updateRol(rol, userPrincipal.getUsername());
		return ResponseEntity.ok(true);
	}
	
	
}
