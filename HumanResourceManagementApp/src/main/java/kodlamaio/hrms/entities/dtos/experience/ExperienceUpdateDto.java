package kodlamaio.hrms.entities.dtos.experience;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceUpdateDto {

	@NotNull
	private int id;
	
	@NotNull
	private String companyName;
	
	@NotNull
	private String jobPosition;
	
	@NotNull
	private LocalDate startingDate;
	
	private LocalDate terminationDate;
}
