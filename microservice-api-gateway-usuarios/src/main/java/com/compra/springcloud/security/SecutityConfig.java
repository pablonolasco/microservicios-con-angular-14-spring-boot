package com.compra.springcloud.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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

		httpSecurity.csrf().disable().cors().disable().authorizeRequests()
				.antMatchers("/api/auth/iniciar-sesion", "/api/auth/crear-cuenta").permitAll() // paths acceso con logeo
																								// previo
				.and().authenticationManager(authenticationManager).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}

	
}
