package kodlamaio.hrms.entities.dtos.jobAdvertisement;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementDto {

	private int jobAdvertisementId;
	
	private String description;
	
	private double minSalary;
	
	private double maxSalary;
	
	private int numberOfPositions;
	
	private LocalDate releaseDate;
	
	private LocalDate deadline;
	
	private Boolean isActive;
	
	private String jobPositionName;
	
	private String companyName;
}
