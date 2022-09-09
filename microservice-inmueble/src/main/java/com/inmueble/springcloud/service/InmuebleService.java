package com.inmueble.springcloud.service;

import java.util.List;

import com.inmueble.springcloud.model.Inmueble;

public interface InmuebleService {

	List<Inmueble>obtenerInmuebles();
	
	Inmueble saveInmueble(Inmueble inmueble);
	
	void deleteInmueble(Long id);
	
	

}
