package com.compra.springcloud.security.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.compra.springcloud.modelos.UsuarioEntity;
import com.compra.springcloud.security.UserPrincipal;

public interface JwtProvider {

	String generarToken(UserPrincipal principal);

	Authentication getAuthentication(HttpServletRequest servletRequest);

	boolean isValidToken(HttpServletRequest servletRequest);

	String generateToken(UsuarioEntity user);

}
