package com.inmueble.springcloud.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorityAuthorizationDecision;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Value("${service.security.secure-key-username}")
	private String SECURE_KEY_USERNAME;

	@Value("${service.security.secure-key-password}")
	private String SECURE_KEY_PASSWORD;

	@Value("${service.security.secure-key-username-2}")
	private String SECURE_KEY_USERNAME_2;

	@Value("${service.security.secure-key-username-2}")
	private String SECURE_KEY_PASSWORD_2;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity
				.getSharedObject(AuthenticationManagerBuilder.class);
		// verifica credenciales
		// setear los usuarios y pasword
		authenticationManagerBuilder.inMemoryAuthentication().withUser(SECURE_KEY_USERNAME)
				.password(new BCryptPasswordEncoder().encode(SECURE_KEY_PASSWORD))
				.authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN")).and()
				.withUser(SECURE_KEY_USERNAME_2).password(new BCryptPasswordEncoder().encode(SECURE_KEY_PASSWORD_2))
				.authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_DEV")).and()
				.passwordEncoder(new BCryptPasswordEncoder());

		// recursos protegidos
		// "/**" indica todos los recursos han sido protegidos
		return httpSecurity.antMatcher("/**")
				.authorizeRequests() // indica tener autorizacion
				.anyRequest()
				.hasRole("ADMIN") // indica se rol admin
				.and()
				.csrf()
				.disable()
				.httpBasic()
				/*.and() solo agregar cuando se trabaja con sesiones en servidor
				.exceptionHandling()
				.accessDeniedHandler((request,response, exception)->{
					// en caso de denegado redireccionar
					response.sendRedirect("https://www.google.com");
				})*/
				.and()
				.build();

	}
}
