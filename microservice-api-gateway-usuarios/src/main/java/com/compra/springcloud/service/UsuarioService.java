package com.compra.springcloud.service;

import java.util.Optional;

import com.compra.springcloud.enums.Rol;
import com.compra.springcloud.modelos.UsuarioEntity;

public interface UsuarioService {

	UsuarioEntity saveUsuario(UsuarioEntity usuarioEntity);

	void updateRol(Rol rol, String username);

	Optional<UsuarioEntity> buscarPorUsername(String username);

}
