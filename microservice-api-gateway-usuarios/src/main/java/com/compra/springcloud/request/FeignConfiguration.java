package com.compra.springcloud.request;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignConfiguration {

	/**
	 * metodo para comunicar microservicios con open feign
	 * @param usernmae
	 * @param password
	 * @return
	 */
	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
			@Value("${service.security.secure-key-username}") String usernmae,
			@Value("${service.security.secure-key-password}") String password) {
		
		
		return  new BasicAuthRequestInterceptor(usernmae, password);
	}
}
