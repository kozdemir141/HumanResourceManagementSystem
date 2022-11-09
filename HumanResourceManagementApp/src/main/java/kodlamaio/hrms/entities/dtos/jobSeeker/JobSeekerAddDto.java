package kodlamaio.hrms.entities.dtos.jobSeeker;

import java.time.LocalDate;

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
public class JobSeekerAddDto {

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
	private String identityNumber;

	@NotNull
	@NotBlank
	@Size(min = 2,max = 30)
	private String firstName;

	@NotNull
	@NotBlank
	@Size(min = 2,max = 30)
	private String lastName;

	@NotNull
	private LocalDate birthDate;
}
