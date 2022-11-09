package kodlamaio.hrms.business.mapping.profiles.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.mapping.profiles.abstracts.ExperienceMappingService;
import kodlamaio.hrms.dataAccess.abstracts.ExperienceDao;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.entities.concretes.Experience;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.dtos.experience.ExperienceAddDto;
import kodlamaio.hrms.entities.dtos.experience.ExperienceUpdateDto;
import lombok.Data;

@Service
@Data
public class ExperienceMappingManager implements ExperienceMappingService {

	private ResumeDao resumeDao;
	private ExperienceDao experienceDao;

	@Autowired
	public ExperienceMappingManager(ResumeDao resumeDao, ExperienceDao experienceDao) {
		super();
		this.resumeDao = resumeDao;
		this.experienceDao = experienceDao;
	}

	@Override
	public Experience experienceAddDtoToExperience(ExperienceAddDto experienceAddDto) {

		Experience experience = new Experience();
		Resume resume = this.resumeDao.getById(experienceAddDto.getResumeId());
		experience.setCompanyName(experienceAddDto.getCompanyName());
		experience.setJobPosition(experienceAddDto.getJobPosition());
		experience.setStartingDate(experienceAddDto.getStartingDate());
		experience.setTerminationDate(experienceAddDto.getTerminationDate());
		experience.setContinues(setContinues(experienceAddDto.getTerminationDate()));
		experience.setResume(resume);
		
		return experience;
	}

	@Override
	public Experience experienceUpdateDtoToExperience(ExperienceUpdateDto experienceUpdateDto) {
		Experience oldExperience = this.experienceDao.getById(experienceUpdateDto.getId());
		
		Experience experience = new Experience();
		experience.setId(experienceUpdateDto.getId());
		experience.setCompanyName(experienceUpdateDto.getCompanyName());
		experience.setJobPosition(experienceUpdateDto.getJobPosition());
		experience.setStartingDate(experienceUpdateDto.getStartingDate());
		experience.setTerminationDate(experience.getTerminationDate());
		experience.setContinues(setContinues(experienceUpdateDto.getTerminationDate()));
		experience.setResume(oldExperience.getResume());
		
		return experience;
	}
	
	private boolean setContinues(LocalDate terminationDate) {
		if (terminationDate == null) {
			return true;
		}
		return false;
	}
}
