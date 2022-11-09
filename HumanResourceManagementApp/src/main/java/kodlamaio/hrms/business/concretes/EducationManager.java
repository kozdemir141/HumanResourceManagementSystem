package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.business.businessRules.abstracts.EducationBusinessRulesService;
import kodlamaio.hrms.business.mapping.profiles.abstracts.EducationMappingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EducationDao;
import kodlamaio.hrms.entities.concretes.Education;
import kodlamaio.hrms.entities.dtos.education.EducationAddDto;
import kodlamaio.hrms.entities.dtos.education.EducationUpdateDto;

@Service
public class EducationManager implements EducationService {

	private EducationDao educationDao;
	private EducationMappingService educationMappingService;
	private EducationBusinessRulesService educationBusinessRulesService;

	@Autowired
	public EducationManager(EducationDao educationDao, EducationMappingService educationMappingService,
			EducationBusinessRulesService educationBusinessRulesService) {
		super();
		this.educationDao = educationDao;
		this.educationMappingService = educationMappingService;
		this.educationBusinessRulesService = educationBusinessRulesService;
	}

	@Override
	public DataResult<List<Education>> getAllByResumeId(int resumeId) {
		return new SuccessDataResult<List<Education>>(this.educationDao.getByResume_Id(resumeId));
	}

	@Override
	public DataResult<List<Education>> getAllByResumeIdSortedByGraduationDate(int resumeId) {
		Sort sort = Sort.by(Sort.Direction.DESC, "graduationDate");

		return new SuccessDataResult<List<Education>>(this.educationDao.getByResume_Id(resumeId, sort));
	}

	@Override
	public Result add(EducationAddDto educationAddDto) {
		try {
			// Business Rules
			this.educationBusinessRulesService.graduationDateCanNotBeforeStartingDate(educationAddDto.getStartingDate(),
					educationAddDto.getGraduationDate());
			this.educationBusinessRulesService.alreadyHasAContinuingEducation(educationAddDto.getGraduationDate(),
					educationAddDto.getResumeId());
			// Mapping
			Education education = this.educationMappingService.educationAddDtoToEducation(educationAddDto);
			this.educationDao.save(education);
			return new SuccessResult("Added");
		} catch (Exception e) {
			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}
	}

	@Override
	public Result delete(int educationId) {
		this.educationDao.deleteById(educationId);
		return new SuccessResult("Education Deleted");
	}

	@Override
	public Result update(EducationUpdateDto educationUpdateDto) {
		try {
			// BusinessRules
			this.educationBusinessRulesService.graduationDateCanNotBeforeStartingDate(
					educationUpdateDto.getStartingDate(), educationUpdateDto.getGraduationDate());
			this.educationBusinessRulesService.updatedEducationAlreadyHasAContinuingEducation(educationUpdateDto.getId(), educationUpdateDto.getGraduationDate());

			// Mapping
			Education education = this.educationMappingService.educationUpdateDtoToEducation(educationUpdateDto);
			this.educationDao.save(education);
			return new SuccessResult("Education Info is Updated");
		} catch (Exception e) {
			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}
	}
}
