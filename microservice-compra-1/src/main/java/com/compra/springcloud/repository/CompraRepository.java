package com.compra.springcloud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compra.springcloud.modelos.CompraEntity;

public interface CompraRepository extends JpaRepository<CompraEntity, Integer> {

	Optional<List<CompraEntity>>findByUsuarioId(Long id);
	
}
