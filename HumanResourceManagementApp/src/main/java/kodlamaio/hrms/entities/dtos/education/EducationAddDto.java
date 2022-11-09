package kodlamaio.hrms.entities.dtos.education;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationAddDto {

	@NotNull
	private String nameOfEducationalInstitution;
	
	@NotNull
	private String department;
	
	@NotNull
	private LocalDate startingDate;
	
	private LocalDate graduationDate;
	
	@NotNull
	private int resumeId;
}

