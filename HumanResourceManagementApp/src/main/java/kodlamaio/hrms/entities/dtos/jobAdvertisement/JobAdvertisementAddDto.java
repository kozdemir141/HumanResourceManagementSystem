package kodlamaio.hrms.entities.dtos.jobAdvertisement;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementAddDto {

	@NotNull
	@Size(min = 6)
	private String description;
	
	private double minSalary;
	
	private double maxSalary;
	
	@NotNull
	private int numberOfPositions;
	
	@NotNull
	private LocalDate deadline;
	
	@NotNull
	private int jobPositionId;
	
	@NotNull
	private int employerId;
	
	@NotNull
	private int cityId;
}
