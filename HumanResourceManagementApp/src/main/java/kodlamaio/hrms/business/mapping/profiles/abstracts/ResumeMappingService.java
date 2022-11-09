package kodlamaio.hrms.business.mapping.profiles.abstracts;

import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.dtos.resume.ResumeAddDto;
import kodlamaio.hrms.entities.dtos.resume.ResumeUpdateDto;
import kodlamaio.hrms.entities.dtos.resume.ResumeWithAllRelatedEntitiesDto;

public interface ResumeMappingService {

	Resume resumeAddDtoToResume(ResumeAddDto resumeAddDto);
	
	ResumeWithAllRelatedEntitiesDto resumeToResumeWithAllRelatedEntitiesDto(Resume resume);
	
	Resume resumeUpdateDtoToResume(ResumeUpdateDto resumeUpdateDto);
}
