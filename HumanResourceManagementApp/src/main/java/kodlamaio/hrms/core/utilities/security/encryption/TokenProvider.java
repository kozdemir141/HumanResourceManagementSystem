package kodlamaio.hrms.core.utilities.security.encryption;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import kodlamaio.hrms.core.entities.Role;

public class TokenProvider {

	public static DecodedJWT decodedJWT(String authorizationHeader) {
		String token = authorizationHeader.substring("Bearer ".length());
		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT;
	}
	
	public static Token refreshToken(String authorizationHeader){
		String token = authorizationHeader.substring("Bearer ".length());
		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT decodedJWT = verifier.verify(token);
		
		Token refreshToken = new Token();
		refreshToken.setToken(token);
		refreshToken.setAlgorithm(algorithm);
		refreshToken.setDecodedJWT(decodedJWT);
		
		return refreshToken;
	}
	
	public static String access_token(User user,HttpServletRequest request,Algorithm algorithm) {
		String access_token = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
		return access_token;
	}
	
	public static String refresh_token(User user,HttpServletRequest request,Algorithm algorithm) {
		String refresh_token = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.sign(algorithm);
		return refresh_token;
	}
	
	public static String refreshed_access_token(kodlamaio.hrms.core.entities.User user,HttpServletRequest request,Algorithm algorithm) {
		String access_token = JWT.create()
				.withSubject(user.getEmail())
				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles",user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
				.sign(algorithm);
		
		return access_token;
	}
	
	public static Map<String, String> tokens(String access_token,String refresh_token){
		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", access_token);
		tokens.put("refresh_token", refresh_token);
		return tokens;
	}
	
	public static Map<String, String> error(Exception exception){
		Map<String, String> error = new HashMap<>();
		error.put("error_message", exception.getMessage());
		return error;
	}
	
	public static HttpServletResponse errorResponse(HttpServletResponse response,Exception exception) {
		response.setHeader("error", exception.getMessage());
		response.setStatus(HttpStatus.FORBIDDEN.value());
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		return response;
	}
}
