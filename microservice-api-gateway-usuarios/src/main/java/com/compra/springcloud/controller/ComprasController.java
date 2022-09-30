package com.compra.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compra.springcloud.request.CompraServiceRequest;
import com.compra.springcloud.security.UserPrincipal;

@RestController
@RequestMapping("gateway/compra")
public class ComprasController {

	@Autowired
	private CompraServiceRequest compraServiceRequest;
	
	@GetMapping
	public ResponseEntity<?>obtener(){
		return new ResponseEntity<>(compraServiceRequest.obtenerCompras(),HttpStatus.OK);
	}

	@GetMapping("/{usuarioId}")
	public ResponseEntity<?>obtenerComprasPorIdUsuario(@AuthenticationPrincipal UserPrincipal userPrincipal ){
		Long id=Long.valueOf(userPrincipal.getId());
		return new ResponseEntity<>(compraServiceRequest.obtenerComprasPorIdUsuario(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?>guardar(@RequestBody Object request){
		return new ResponseEntity<>(compraServiceRequest.guardarCompra(request),HttpStatus.CREATED);
	}
}
