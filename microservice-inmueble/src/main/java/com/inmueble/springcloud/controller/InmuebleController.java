package com.inmueble.springcloud.controller;

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

import com.inmueble.springcloud.model.Inmueble;
import com.inmueble.springcloud.service.impl.InmuebleServiceImpl;

@RestController
@RequestMapping("/api/inmuebles")
public class InmuebleController {

	@Autowired
	private InmuebleServiceImpl inmuebleServiceImpl;
	
	@GetMapping("/buscar")
	public ResponseEntity<?>obtenerInmuebles(){
		return ResponseEntity.ok(inmuebleServiceImpl.obtenerInmuebles());
	}
	
	@PostMapping
	public ResponseEntity<?>saveInmueble(@RequestBody Inmueble inmueble){
		return new ResponseEntity<>(inmuebleServiceImpl.saveInmueble(inmueble),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteInmueble(@PathVariable Long id){
		inmuebleServiceImpl.deleteInmueble(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
