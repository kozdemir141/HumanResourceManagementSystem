package kodlamaio.hrms.business.mapping.profiles.abstracts;

import kodlamaio.hrms.entities.concretes.Education;
import kodlamaio.hrms.entities.dtos.education.EducationAddDto;
import kodlamaio.hrms.entities.dtos.education.EducationUpdateDto;

public interface EducationMappingService {

	Education educationAddDtoToEducation(EducationAddDto educationAddDto);
	
	Education educationUpdateDtoToEducation(EducationUpdateDto educationUpdateDto);
}
