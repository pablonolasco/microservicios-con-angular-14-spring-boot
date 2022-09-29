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
import com.compra.springcloud.security.jwt.JwtProvider;
import com.compra.springcloud.service.UsuarioService;

@Transactional
@Service
public class UsuarioServiceImpl implements UsuarioService{

	private final UsuarioRepository usuarioRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final JwtProvider jwtProvider;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder,JwtProvider jwtProvider) {
		this.usuarioRepository = usuarioRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.jwtProvider=jwtProvider;
	}
	
	@Override
	public UsuarioEntity saveUsuario(UsuarioEntity usuarioEntity) {
		usuarioEntity.setPassword(bCryptPasswordEncoder.encode(usuarioEntity.getPassword()));
		usuarioEntity.setFechaAlta(LocalDateTime.now());
		usuarioEntity.setRol(Rol.USER);
		UsuarioEntity usuarioEntityCreado =usuarioRepository.saveAndFlush(usuarioEntity);
		String token=jwtProvider.generateToken(usuarioEntityCreado);
		usuarioEntityCreado.setToken(token);
		return usuarioEntityCreado;
	}
	
	@Override
	public Optional<UsuarioEntity> buscarPorUsername(String username) {
		return Optional.ofNullable(usuarioRepository.findByUsername(username).orElse(null));
	}
	
	@Override
	public void updateRol(Rol rol, String username) {
		 usuarioRepository.findByUsername(username)
				.orElseThrow(()->
				new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No se encontro el registro con el username: ", username)));
		usuarioRepository.update(username, rol);
	}
	
	
	
}
