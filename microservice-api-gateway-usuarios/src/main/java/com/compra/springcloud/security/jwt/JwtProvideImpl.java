package com.compra.springcloud.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.compra.springcloud.security.UserPrincipal;
import com.compra.springcloud.utils.SecurityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvideImpl implements JwtProvider {

	@Value("${app.jwt.secret}")
	private String JWT_SECRET;
	@Value("${app.jwt.expiration-in-ms}")
	private Long JWT_EXPIRATION_IN_MS;
	
	// metodo para generar el token
	@Override
	public String generarToken(UserPrincipal principal) {
		// obtener los roles
		String authorities= principal.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		// obtener key
		Key keys= Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
		
		return Jwts.builder()
				.setSubject(principal.getUsername())
				.claim("roles",authorities)
				.claim("userId",principal.getId())
				.setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION_IN_MS))
				.signWith(keys,SignatureAlgorithm.HS512)
				.compact();
		
	}
	
	
	// obtiene la utenticacion
	
	@Override
	public Authentication getAuthentication(HttpServletRequest servletRequest) {
		Claims claims= extracClaims(servletRequest);
		// obtener informacion del token
		if (claims== null) {
			return null;
		}
		String username = claims.getSubject();
		Integer userId=claims.get("usuarioId", Integer.class);
		// roles
		Set<GrantedAuthority> authorities=Arrays.stream(claims.get("roles").toString().split(","))
				.map(SecurityUtils::convertirToAuthority)
				.collect(Collectors.toSet());
		
		// objeto usuario
		UserDetails userDetails = UserPrincipal.builder()
				.username(username)
				.grantedAuthorities(authorities)
				.id(userId)
				.build();
		
		if (username == null) {
			return null;
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
		
	}
	
	@Override
	public boolean isValidToken(HttpServletRequest servletRequest) {
		Claims claims= extracClaims(servletRequest);
		if (claims == null) {
			return false;
		}
		
		if (claims.getExpiration().before(new Date())) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * metodo que extrae el contenido del token
	 * @param servletRequest
	 * @return
	 */
	private Claims extracClaims(HttpServletRequest servletRequest) {
		String token = SecurityUtils.extractAuthTokenFromRequest(servletRequest);
		if (token==null) {
			return null;
		}
		
		Key key= Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
		
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}
