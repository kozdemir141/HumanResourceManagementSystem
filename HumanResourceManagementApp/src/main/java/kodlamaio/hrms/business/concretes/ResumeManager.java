package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ResumeService;
import kodlamaio.hrms.business.mapping.profiles.abstracts.ResumeMappingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.dtos.resume.ResumeAddDto;
import kodlamaio.hrms.entities.dtos.resume.ResumeUpdateDto;
import kodlamaio.hrms.entities.dtos.resume.ResumeWithAllRelatedEntitiesDto;

@Service
public class ResumeManager implements ResumeService {

	private ResumeDao resumeDao;
	private ResumeMappingService resumeMappingService;

	@Autowired
	public ResumeManager(ResumeDao resumeDao, ResumeMappingService resumeMappingService) {
		super();
		this.resumeDao = resumeDao;
		this.resumeMappingService = resumeMappingService;
	}

	@Override
	public DataResult<Resume> getById(int resumeId) {

		return new SuccessDataResult<Resume>(this.resumeDao.getById(resumeId));
	}
	
	@Override
	public DataResult<Resume> getByJobSeekerId(int jobSeekerId) {

		return new SuccessDataResult<Resume>(this.resumeDao.getByJobSeeker_Id(jobSeekerId));
	}

	@Override
	public DataResult<ResumeWithAllRelatedEntitiesDto> getResumeDetailsByJobSeekerId(int jobSeekerId) {
		Resume resume = getByJobSeekerId(jobSeekerId).getData();
		// Mapping
		ResumeWithAllRelatedEntitiesDto resumeWithAllRelatedEntitiesDto = this.resumeMappingService
				.resumeToResumeWithAllRelatedEntitiesDto(resume);

		return new SuccessDataResult<ResumeWithAllRelatedEntitiesDto>(resumeWithAllRelatedEntitiesDto);
	}

	@Override
	public Result add(ResumeAddDto resumeAddDto) {
		// Mapping
		Resume resume = resumeMappingService.resumeAddDtoToResume(resumeAddDto);
		this.resumeDao.save(resume);
		return new SuccessResult("Added");
	}

	@Override
	public Result update(ResumeUpdateDto resumeUpdateDto) {
		//Mapping
		Resume resume = this.resumeMappingService.resumeUpdateDtoToResume(resumeUpdateDto);
		this.resumeDao.save(resume);
		return new SuccessResult("Updated");
	}

	@Override
	public Result delete(int id) {
		this.resumeDao.deleteById(id);
		return new SuccessResult("Deleted");
	}

}
