package kodlamaio.hrms.business.abstracts;


import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Experience;
import kodlamaio.hrms.entities.dtos.experience.ExperienceAddDto;
import kodlamaio.hrms.entities.dtos.experience.ExperienceUpdateDto;

public interface ExperienceService {

	DataResult<List<Experience>> getAllByResumeId(int resumeId);
	
	DataResult<List<Experience>> getAllByResumeIdSortedByTerminationDate(int resumeId);
	
	Result add(ExperienceAddDto experienceAddDto);
	
	Result update(ExperienceUpdateDto experienceUpdateDto);
	
	Result delete(int experienceId);
}
