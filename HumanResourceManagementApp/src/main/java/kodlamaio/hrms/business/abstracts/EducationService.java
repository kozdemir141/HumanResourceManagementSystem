package kodlamaio.hrms.business.abstracts;


import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Education;
import kodlamaio.hrms.entities.dtos.education.EducationAddDto;
import kodlamaio.hrms.entities.dtos.education.EducationUpdateDto;

public interface EducationService {

	DataResult<List<Education>> getAllByResumeId(int resumeId);
	
	DataResult<List<Education>> getAllByResumeIdSortedByGraduationDate(int resumeId);
	
	Result add(EducationAddDto educationAddDto);
	
	Result delete(int educationId);
	
	Result update(EducationUpdateDto educationUpdateDto);
}
