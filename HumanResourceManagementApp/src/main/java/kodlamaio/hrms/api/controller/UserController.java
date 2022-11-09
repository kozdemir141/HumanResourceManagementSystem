package kodlamaio.hrms.api.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.entities.Role;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.security.encryption.Token;
import kodlamaio.hrms.core.utilities.security.encryption.TokenProvider;
import kodlamaio.hrms.entities.dtos.user.RoleToUserDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok().body(userService.getUsers());
	}
	
	@GetMapping("/findbyemail")
	public ResponseEntity<User> getuserbyemail(@RequestBody String email) {
		return ResponseEntity.ok().body(userService.getUser(email));
	}
	@PostMapping("/user/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
		;
		return ResponseEntity.created(uri).body(userService.saveUser(user));
	}

	@PostMapping("/role/save")
	public ResponseEntity<Role> saveRole(@RequestBody Role role) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
		return ResponseEntity.created(uri).body(userService.saveRole(role));
	}

	@PostMapping("/role/addroletouser")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDto form) {
		userService.addRoleToUser(form.getEmail(), form.getRoleName());
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				Token refreshToken = TokenProvider.refreshToken(authorizationHeader);
				String email = refreshToken.getDecodedJWT().getSubject(); // This is going to give me userName that comes with the token
				User user = userService.getUser(email);
				String access_token = TokenProvider.refreshed_access_token(user, request, refreshToken.getAlgorithm());
				
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), TokenProvider.tokens(access_token, refreshToken.getToken()));
				
			} catch (Exception exception) {
				new ObjectMapper().writeValue(TokenProvider.errorResponse(response, exception).getOutputStream(), TokenProvider.error(exception));
			}
		}
		else {
			throw new RuntimeException("Refresh Token is Missing");
		}
	}
}
