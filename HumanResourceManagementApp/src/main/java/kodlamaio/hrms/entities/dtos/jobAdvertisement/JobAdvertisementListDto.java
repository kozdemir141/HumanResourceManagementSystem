package kodlamaio.hrms.entities.dtos.jobAdvertisement;

import java.time.LocalDate;

//import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementListDto {

	private int jobAdvertisementId;

	private int numberOfPositions;

	private LocalDate releaseDate;

	private LocalDate deadline;
	
	private String companyName;
	
	private String jobPositionName;
}
