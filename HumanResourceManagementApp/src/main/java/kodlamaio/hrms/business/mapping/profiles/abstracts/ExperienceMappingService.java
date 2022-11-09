package kodlamaio.hrms.business.mapping.profiles.abstracts;

import kodlamaio.hrms.entities.concretes.Experience;
import kodlamaio.hrms.entities.dtos.experience.ExperienceAddDto;
import kodlamaio.hrms.entities.dtos.experience.ExperienceUpdateDto;

public interface ExperienceMappingService {

	Experience experienceAddDtoToExperience(ExperienceAddDto experienceAddDto);
	
	Experience experienceUpdateDtoToExperience(ExperienceUpdateDto experienceUpdateDto);
}
