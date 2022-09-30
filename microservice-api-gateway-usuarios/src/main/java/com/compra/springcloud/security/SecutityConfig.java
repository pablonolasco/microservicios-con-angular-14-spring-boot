package com.compra.springcloud.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.compra.springcloud.enums.Rol;
import com.compra.springcloud.security.jwt.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecutityConfig {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// metodo que se encarga de la validacion de los usuarios
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilter() {
		return new JwtAuthorizationFilter();
	}
	/**
	 * 
	 * @param httpSecurity
	 * @return las urls permitidas
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity
				.getSharedObject(AuthenticationManagerBuilder.class);

		// referencia entre el usuario del proyecto
		authenticationManagerBuilder.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder);

		AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
		// paths publicos

		httpSecurity.cors();
		httpSecurity.csrf().disable();
		httpSecurity.authenticationManager(authenticationManager);
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity.authorizeRequests()
				.antMatchers("/api/autenticacion/ingresar-sesion", "/api/autenticacion/crear-cuenta").permitAll() // paths acceso con logeo
				.antMatchers(HttpMethod.GET,"gateway/inmueble").permitAll()																				// previo
				.antMatchers("gateway/inmueble").hasRole(Rol.ADMIN.name())																				// previo
				.anyRequest().authenticated();
		
		httpSecurity.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}

	
}
