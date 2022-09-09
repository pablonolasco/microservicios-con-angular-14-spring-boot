package com.compra.springcloud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.compra.springcloud.enums.Rol;
import com.compra.springcloud.modelos.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{

	Optional<UsuarioEntity> findByUsernameAndPassword(String username, String password);
	Optional<UsuarioEntity> findByUsername(String username);
	
	@Modifying
	@Query(value = "update usuarios set role=role where username=username")
	void update(@Param("username")String username, @Param("role")Rol role);
}
