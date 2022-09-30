package com.compra.springcloud.request;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
		value="microservice-inmueble",
		path="/api/inmuebles",
		//url="${inmueble.service.url}",
		configuration = FeignConfiguration.class
		)
public interface InmuebleServiceRequest {

	@PostMapping
	Object guardarInmueble(@RequestBody Object request);
	
	@DeleteMapping("{inmuebleId}")
	void desactivarInmueble(@PathVariable("inmuebleId") Long inmuebleId);
	
	@GetMapping("/buscar")
	List<Object>obtenerInmuebles();
}
