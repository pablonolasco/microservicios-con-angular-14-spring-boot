package com.compra.springcloud.service;

import com.compra.springcloud.modelos.UsuarioEntity;

public interface AuthenticationService {

	UsuarioEntity ingresarSesion(UsuarioEntity usuarioEntity);

}
