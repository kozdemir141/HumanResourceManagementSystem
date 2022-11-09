package kodlamaio.hrms.entities.dtos.employee;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAddDto {

	@NotNull
	@NotBlank
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	private String email;
	
	@NotNull
	@NotBlank
	@Size(min = 6)
	private String password;
	
	@NotNull
	@NotBlank
	@Size(min = 6)
	private String passwordRepeat;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
}
