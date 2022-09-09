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

import com.compra.springcloud.modelos.CompraEntity;
import com.compra.springcloud.service.impl.CompraServiceImpl;

@RestController
@RequestMapping("/api/compras")
public class ComprasController {
	
	@Autowired
	private CompraServiceImpl  compraServiceImpl;
	
	@GetMapping
	public ResponseEntity<?>listar(){
		return new ResponseEntity<>(compraServiceImpl.getCompras(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?>buscarPorIdUsuario(@PathVariable Long id){
		return new ResponseEntity<>(compraServiceImpl.getCompraByUsuario(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?>agregarCompra(@RequestBody CompraEntity compraEntity){
		return new ResponseEntity<>(compraServiceImpl.save(compraEntity),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>eliminarCompra(@PathVariable Integer id){
		compraServiceImpl.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	

}
