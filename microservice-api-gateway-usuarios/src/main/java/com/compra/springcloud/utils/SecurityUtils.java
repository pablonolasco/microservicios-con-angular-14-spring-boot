package com.compra.springcloud.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

public class SecurityUtils {

	public static final String ROLE_PREFIX="ROLE_";
	public static final String AUTH_HEADER="authorization";
	public static final String AUTH_TOKEN_TYPE="Bearer";
	public static final String AUTH_TOKEN_PREFIX=AUTH_TOKEN_TYPE.concat(" ");
	
	public static SimpleGrantedAuthority convertirToAuthority(String role) {
		String roleFormateado=role.startsWith("ROLE_")?role:ROLE_PREFIX.concat(role);
		return new SimpleGrantedAuthority(roleFormateado);
	}
	
	public static String extractAuthTokenFromRequest(HttpServletRequest servletRequest) {
		String bearerToken=servletRequest.getHeader(AUTH_HEADER);
		if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX)) {
			// regresa token
			return bearerToken.substring(7);
		}
		return null;
	}
}
