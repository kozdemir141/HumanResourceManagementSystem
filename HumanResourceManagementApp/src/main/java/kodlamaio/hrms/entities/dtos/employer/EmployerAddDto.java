package kodlamaio.hrms.entities.dtos.employer;

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
public class EmployerAddDto {

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
	@NotBlank
	private String companyName;

	@NotNull
	@NotBlank
	private String webAdress;

	@NotNull
	@NotBlank
	@Size(min = 10, max = 11)
	private String phoneNumber;
}
