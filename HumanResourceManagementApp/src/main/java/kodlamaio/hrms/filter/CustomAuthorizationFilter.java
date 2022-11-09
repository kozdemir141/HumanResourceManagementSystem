package kodlamaio.hrms.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import kodlamaio.hrms.core.utilities.security.encryption.TokenProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/token/refresh")) {
			filterChain.doFilter(request, response);
		} else {

			String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				try {
					DecodedJWT decodedJWT = TokenProvider.decodedJWT(authorizationHeader);

					String email = decodedJWT.getSubject(); // This is going to give me userName that comes with the token
					String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
					//We don't need the password because  at this point, the user has been authenticated.
					
					Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
					
					Stream.of(roles).forEach(role ->{
						authorities.add(new SimpleGrantedAuthority(role));
					});
					
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, authorities);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					filterChain.doFilter(request, response);
					
				} catch (Exception exception) {
					log.error("Error logging in: {}", exception.getMessage());
					new ObjectMapper().writeValue(TokenProvider.errorResponse(response, exception).getOutputStream(), TokenProvider.error(exception));
				}
			}
			else {
				filterChain.doFilter(request, response);
			}
		}

	}

}
