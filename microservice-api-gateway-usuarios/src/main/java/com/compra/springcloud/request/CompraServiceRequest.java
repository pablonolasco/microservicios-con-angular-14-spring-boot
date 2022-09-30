package com.compra.springcloud.request;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
		value = "compras-service",
		path = "api/compras",
		url = "${compras.service.url}",
		configuration = FeignConfiguration.class
		)
public interface CompraServiceRequest {

	@GetMapping
	List<Object> obtenerCompras();
	
	@GetMapping("{usuarioId}")
	List<Object> obtenerComprasPorIdUsuario(@PathVariable("usuarioId") Long usuarioId);

	
	@PostMapping()
	Object guardarCompra(@RequestBody Object request);
}
