package com.compra.springcloud.service.impl;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.compra.springcloud.modelos.UsuarioEntity;
import com.compra.springcloud.security.UserPrincipal;
import com.compra.springcloud.security.jwt.JwtProvider;
import com.compra.springcloud.service.AuthenticationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	private AuthenticationManager authenticationManager;

	private JwtProvider jwtProvider;
	
	
	@Override
	public UsuarioEntity ingresarSesion(UsuarioEntity usuarioEntity) {
		 Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(usuarioEntity.getUsername(), usuarioEntity.getPassword())
	        );
		 UserPrincipal principal= (UserPrincipal)authentication.getPrincipal();
		 
		 String jwt= jwtProvider.generarToken(principal);
		 
		 UsuarioEntity  usuario= principal.getUsuarioEntity();
		 
		 usuario.setToken(jwt);
		 
		 return usuario;
		 
				
	}
	
}
