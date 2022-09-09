package com.compra.springcloud.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.compra.springcloud.enums.Rol;
import com.compra.springcloud.modelos.UsuarioEntity;
import com.compra.springcloud.repository.UsuarioRepository;
import com.compra.springcloud.service.UsuarioService;

@Transactional
@Service
public class UsuarioServiceImpl implements UsuarioService{

	private final UsuarioRepository usuarioRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	public UsuarioEntity saveUsuario(UsuarioEntity usuarioEntity) {
		usuarioEntity.setPassword(bCryptPasswordEncoder.encode(usuarioEntity.getPassword()));
		usuarioEntity.setFechaAlta(LocalDateTime.now());
		usuarioEntity.setRol(Rol.USER);
		return usuarioRepository.saveAndFlush(usuarioEntity);
	}
	
	@Override
	public Optional<UsuarioEntity> buscarPorUsername(String username) {
		return Optional.ofNullable(usuarioRepository.findByUsername(username)
				.orElseThrow(()->
				new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No se encontro el registro con el username: ", username))));
	}
	
	@Override
	public void updateRol(Rol rol, String username) {
		 usuarioRepository.findByUsername(username)
				.orElseThrow(()->
				new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No se encontro el registro con el username: ", username)));
		usuarioRepository.update(username, rol);
	}
	
	
	
}
