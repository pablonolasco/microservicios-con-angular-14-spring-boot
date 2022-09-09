package com.inmueble.springcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inmueble.springcloud.model.Inmueble;
@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble, Long> {

}
