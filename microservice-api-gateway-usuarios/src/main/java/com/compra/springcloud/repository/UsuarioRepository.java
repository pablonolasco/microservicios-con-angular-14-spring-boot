package com.compra.springcloud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.compra.springcloud.enums.Rol;
import com.compra.springcloud.modelos.UsuarioEntity;
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{

	Optional<UsuarioEntity> findByUsernameAndPassword(String username, String password);
	Optional<UsuarioEntity> findByUsername(String username);
	
	@Modifying
	@Query(value = "update UsuarioEntity set role=:role where username=:username")
	void update(@Param("username")String username, @Param("role")Rol role);
	//void update(String username, Rol role);
}
