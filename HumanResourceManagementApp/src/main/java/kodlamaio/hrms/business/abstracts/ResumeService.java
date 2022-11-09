package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.dtos.resume.ResumeAddDto;
import kodlamaio.hrms.entities.dtos.resume.ResumeUpdateDto;
import kodlamaio.hrms.entities.dtos.resume.ResumeWithAllRelatedEntitiesDto;

public interface ResumeService {

	DataResult<Resume> getByJobSeekerId(int jobSeekerId);
	
	DataResult<Resume> getById(int resumeId);
	
	DataResult<ResumeWithAllRelatedEntitiesDto> getResumeDetailsByJobSeekerId(int jobSeekerId);
	
	Result add(ResumeAddDto resumeAddDto);
	
	Result update(ResumeUpdateDto resumeUpdateDto);
	
	Result delete(int id);
}
