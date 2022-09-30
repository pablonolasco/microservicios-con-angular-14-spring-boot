package com.compra.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compra.springcloud.request.InmuebleServiceRequest;

@RestController
@RequestMapping("gateway/inmueble")
public class InmuebleController {

	@Autowired
	private InmuebleServiceRequest inmuebleServiceRequest;
	
	@GetMapping
	public ResponseEntity<?>obtener(){
		return new ResponseEntity<>(inmuebleServiceRequest.obtenerInmuebles(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?>guardar(@RequestBody Object request){
		return new ResponseEntity<>(inmuebleServiceRequest.guardarInmueble(request),HttpStatus.OK);
	}
	
	@DeleteMapping("{inmueldeId}")
	public ResponseEntity<?>desactivar(@PathVariable Long inmueldeId){
		inmuebleServiceRequest.desactivarInmueble(inmueldeId);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
