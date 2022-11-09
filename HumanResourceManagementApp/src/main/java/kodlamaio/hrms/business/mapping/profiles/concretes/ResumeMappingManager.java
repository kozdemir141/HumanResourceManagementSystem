package kodlamaio.hrms.business.mapping.profiles.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.business.abstracts.ExperienceService;
import kodlamaio.hrms.business.mapping.profiles.abstracts.ResumeMappingService;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.dtos.resume.ResumeAddDto;
import kodlamaio.hrms.entities.dtos.resume.ResumeUpdateDto;
import kodlamaio.hrms.entities.dtos.resume.ResumeWithAllRelatedEntitiesDto;
import lombok.Data;

@Service
@Data
public class ResumeMappingManager implements ResumeMappingService {

	private ResumeDao resumeDao;
	private JobSeekerDao jobSeekerDao;
	private ExperienceService experienceService;
	private EducationService educationService;

	@Autowired
	public ResumeMappingManager(JobSeekerDao jobSeekerDao, ExperienceService experienceService,
			EducationService educationService, ResumeDao resumeDao) {
		super();
		this.jobSeekerDao = jobSeekerDao;
		this.experienceService = experienceService;
		this.educationService = educationService;
		this.resumeDao = resumeDao;
	}

	@Override
	public Resume resumeAddDtoToResume(ResumeAddDto resumeAddDto) {

		Resume resume = new Resume();
		JobSeeker jobSeeker = this.jobSeekerDao.getById(resumeAddDto.getJobSeekerId());
		resume.setCreationDate(LocalDate.now());
		resume.setGithubLink(resumeAddDto.getGithubLink());
		resume.setLinkedinlink(resumeAddDto.getLinkedinlink());
		resume.setCoverLetter(resumeAddDto.getCoverLetter());
		resume.setJobSeeker(jobSeeker);

		return resume;
	}

	@Override
	public ResumeWithAllRelatedEntitiesDto resumeToResumeWithAllRelatedEntitiesDto(Resume resume) {

		ResumeWithAllRelatedEntitiesDto resumeWithAllRelatedEntitiesDto = new ResumeWithAllRelatedEntitiesDto(
				resume.getId(), resume.getCreationDate(), resume.getGithubLink(), resume.getLinkedinlink(),
				resume.getCoverLetter(), resume.getJobSeeker(),
				educationService.getAllByResumeIdSortedByGraduationDate(resume.getId()).getData(),
				experienceService.getAllByResumeIdSortedByTerminationDate(resume.getId()).getData(),
				resume.getLanguages(), resume.getProgramingLanguages());

		return resumeWithAllRelatedEntitiesDto;
	}

	@Override
	public Resume resumeUpdateDtoToResume(ResumeUpdateDto resumeUpdateDto) {
		Resume oldResume = this.resumeDao.getById(resumeUpdateDto.getId());
		Resume resume = new Resume();
		
		resume.setId(resumeUpdateDto.getId());
		resume.setCreationDate(oldResume.getCreationDate());
		resume.setGithubLink(resumeUpdateDto.getGithubLink());
		resume.setLinkedinlink(resumeUpdateDto.getLinkedinlink());
		resume.setCoverLetter(resumeUpdateDto.getCoverLetter());
		resume.setJobSeeker(oldResume.getJobSeeker());
		
		return resume;
	}
}
