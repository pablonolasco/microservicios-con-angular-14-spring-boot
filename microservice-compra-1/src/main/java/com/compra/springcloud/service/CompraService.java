package com.compra.springcloud.service;

import java.util.List;

import com.compra.springcloud.modelos.CompraEntity;

public interface CompraService {

	List<CompraEntity>getCompras();
	CompraEntity getCompra(Integer id);
	List<CompraEntity> getCompraByUsuario(Long id);
	CompraEntity save(CompraEntity compraEntity);
	void delete(Integer id);
}
