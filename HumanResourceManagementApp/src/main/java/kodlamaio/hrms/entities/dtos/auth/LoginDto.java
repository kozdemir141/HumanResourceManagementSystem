package kodlamaio.hrms.entities.dtos.auth;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
}
