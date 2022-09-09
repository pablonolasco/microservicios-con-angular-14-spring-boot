package com.inmueble.springcloud.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inmueble.springcloud.model.Inmueble;
import com.inmueble.springcloud.repository.InmuebleRepository;
import com.inmueble.springcloud.service.InmuebleService;

@Transactional
@Service
public class InmuebleServiceImpl implements InmuebleService{

	private final InmuebleRepository inmuebleRepository;

	public InmuebleServiceImpl(InmuebleRepository inmuebleRepository) {
		this.inmuebleRepository = inmuebleRepository;
	}
	
	@Override
	public List<Inmueble> obtenerInmuebles() {
		return inmuebleRepository.findAll();
	}

	@Override
	public Inmueble saveInmueble(Inmueble inmueble) {
		inmueble.setFechaCreacion(LocalDateTime.now());
		return inmuebleRepository.save(inmueble);
	}

	@Override
	public void deleteInmueble(Long id) {
		inmuebleRepository.deleteById(id);
		
	}
	
	
	
	
}
