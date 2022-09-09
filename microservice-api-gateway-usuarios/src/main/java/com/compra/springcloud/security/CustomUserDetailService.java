package com.compra.springcloud.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.compra.springcloud.modelos.UsuarioEntity;
import com.compra.springcloud.service.UsuarioService;
import com.compra.springcloud.utils.SecurityUtils;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioEntity usuarioEntity=usuarioService.buscarPorUsername(username).orElseThrow(()->new UsernameNotFoundException("El username ".concat(username).concat(" no existe")));
		Set<GrantedAuthority>authorities= new HashSet<>(Arrays.asList(SecurityUtils.convertirToAuthority(usuarioEntity.getRol().name())));
		return UserPrincipal
				.builder()
				.usuarioEntity(usuarioEntity)
				.id(usuarioEntity.getId())
				.username(username)
				.password(usuarioEntity.getPassword())
				.grantedAuthorities(authorities)
				.build();
	}

	

}
