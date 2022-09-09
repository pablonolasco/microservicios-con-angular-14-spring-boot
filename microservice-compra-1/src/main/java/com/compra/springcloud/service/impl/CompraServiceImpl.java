package com.compra.springcloud.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.compra.springcloud.modelos.CompraEntity;
import com.compra.springcloud.repository.CompraRepository;
import com.compra.springcloud.service.CompraService;

@Service
@Transactional
public class CompraServiceImpl implements CompraService{

	private final CompraRepository compraRepository;
	
	
	public CompraServiceImpl(CompraRepository compraRepository) {
		this.compraRepository = compraRepository;
	}
	

	@Override
	public List<CompraEntity> getCompras() {
		
		return compraRepository.findAll();
	}

	@Override
	public CompraEntity getCompra(Integer id) {
		
		return compraRepository.findById(id).orElseThrow(
				()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El id de la compra no existe", id)));
	}

	@Override
	public CompraEntity save(CompraEntity compraEntity) {
		compraEntity.setFechaCompra(LocalDateTime.now());
		return compraRepository.saveAndFlush(compraEntity);
	}

	@Override
	public void delete(Integer id) {
		compraRepository.deleteById(id);
	}


	@Override
	public List<CompraEntity> getCompraByUsuario(Long id) {
		return compraRepository.findByUsuarioId(id)
				.orElseThrow(()->
				new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No se encontro la compra con el id de usuario", id)));
	}

}
