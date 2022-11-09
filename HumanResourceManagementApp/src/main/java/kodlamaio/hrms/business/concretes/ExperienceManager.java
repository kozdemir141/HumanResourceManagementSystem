package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ExperienceService;
import kodlamaio.hrms.business.businessRules.abstracts.ExperienceBusinessRulesService;
import kodlamaio.hrms.business.mapping.profiles.abstracts.ExperienceMappingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ExperienceDao;
import kodlamaio.hrms.entities.concretes.Experience;
import kodlamaio.hrms.entities.dtos.experience.ExperienceAddDto;
import kodlamaio.hrms.entities.dtos.experience.ExperienceUpdateDto;

@Service
public class ExperienceManager implements ExperienceService {

	private ExperienceDao experienceDao;
	private ExperienceMappingService experienceMappingService;
	private ExperienceBusinessRulesService experienceBusinessRulesService;

	@Autowired
	public ExperienceManager(ExperienceDao experienceDao, ExperienceMappingService experienceMappingService,
			ExperienceBusinessRulesService experienceBusinessRulesService) {
		super();
		this.experienceDao = experienceDao;
		this.experienceMappingService = experienceMappingService;
		this.experienceBusinessRulesService = experienceBusinessRulesService;
	}

	@Override
	public DataResult<List<Experience>> getAllByResumeId(int resumeId) {

		return new SuccessDataResult<List<Experience>>(this.experienceDao.getByResume_Id(resumeId));
	}

	@Override
	public DataResult<List<Experience>> getAllByResumeIdSortedByTerminationDate(int resumeId) {

		Sort sort = Sort.by(Sort.Direction.DESC, "terminationDate");

		return new SuccessDataResult<List<Experience>>(this.experienceDao.getByResume_Id(resumeId, sort));
	}

	@Override
	public Result add(ExperienceAddDto experienceAddDto) {

		try {
			// Business Rules
			this.experienceBusinessRulesService.terminationDateCanNotBeforeStartingDate(
					experienceAddDto.getStartingDate(), experienceAddDto.getTerminationDate());
			this.experienceBusinessRulesService.alreadyHasAContinuingExperience(experienceAddDto.getTerminationDate(),
					experienceAddDto.getResumeId());
			// Mapping
			Experience experience = experienceMappingService.experienceAddDtoToExperience(experienceAddDto);
			this.experienceDao.save(experience);
			return new SuccessResult("Added");

		} catch (Exception e) {
			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}

	}

	@Override
	public Result update(ExperienceUpdateDto experienceUpdateDto) {
		try {
			// BusinessRules
			this.experienceBusinessRulesService.terminationDateCanNotBeforeStartingDate(
					experienceUpdateDto.getStartingDate(), experienceUpdateDto.getTerminationDate());
			this.experienceBusinessRulesService.updatedExperienceAlreadyHasAContinuingExperience(
					experienceUpdateDto.getId(), experienceUpdateDto.getTerminationDate());

			// Mapping
			Experience experience = this.experienceMappingService.experienceUpdateDtoToExperience(experienceUpdateDto);
			this.experienceDao.save(experience);
			return new SuccessResult("Experience Updated");

		} catch (Exception e) {
			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}

	}

	@Override
	public Result delete(int experienceId) {

		this.experienceDao.deleteById(experienceId);
		return new SuccessResult("Experience Deleted");
	}
}
