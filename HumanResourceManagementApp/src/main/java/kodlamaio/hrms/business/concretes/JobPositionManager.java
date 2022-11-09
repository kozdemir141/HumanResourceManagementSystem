package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.business.businessRules.abstracts.JobPositionBusinessRulesService;
import kodlamaio.hrms.business.mapping.profiles.abstracts.JobPositionMappingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;
import kodlamaio.hrms.entities.dtos.jobPosition.JobPositionAddDto;
import kodlamaio.hrms.entities.dtos.jobPosition.JobPositionUpdateDto;

@Service
public class JobPositionManager implements JobPositionService {

	private JobPositionDao jobPositionDao;
	private JobPositionMappingService jobPositionMappingService;
	private JobPositionBusinessRulesService jobPositionBusinessRulesService;

	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao, JobPositionMappingService jobPositionMappingService,
			JobPositionBusinessRulesService jobPositionBusinessRulesService) {
		super();
		this.jobPositionDao = jobPositionDao;
		this.jobPositionMappingService = jobPositionMappingService;
		this.jobPositionBusinessRulesService = jobPositionBusinessRulesService;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {

		return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(), "Listed");
	}

	@Override
	public DataResult<JobPosition> getById(int id) {
		return new SuccessDataResult<JobPosition>(this.jobPositionDao.getById(id));
	}

	@Override
	public DataResult<List<JobPosition>> getAllByNameIsNot(String jobPositionName) {
		return new SuccessDataResult<List<JobPosition>>(
				this.jobPositionDao.findByJobPositionNameIsNot(jobPositionName));
	}

	@Override
	public Result add(JobPositionAddDto jobPositionAddDto) {
		try {
			// Business Rules
			this.jobPositionBusinessRulesService
					.jobPositionNameCanNotBeDublicatedWhenInserted(jobPositionAddDto.getJobPositionName());
			// Mapping
			JobPosition jobPosition = this.jobPositionMappingService.jobPositionAddDtoToJobPosition(jobPositionAddDto);
			this.jobPositionDao.save(jobPosition);
			return new SuccessResult("Succesfully Added");
		} catch (Exception e) {

			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}

	}

	@Override
	public Result update(JobPositionUpdateDto jobPositionUpdateDto) {
		try {
			// Business Rules
			this.jobPositionBusinessRulesService.jobPositionNameCanNotBeDublicatedWhenUpdating(
					jobPositionUpdateDto.getJobPositionId(), jobPositionUpdateDto.getJobPositionName());

			// Mapping
			JobPosition jobPosition = this.jobPositionMappingService
					.jobPositionUpdateDtoToJobPosition(jobPositionUpdateDto);

			this.jobPositionDao.save(jobPosition);
			return new SuccessResult("Job Position Updated");
		} catch (Exception e) {

			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}

	}

	@Override
	public Result delete(int id) {
		this.jobPositionDao.deleteById(id);
		return new SuccessResult("Job Position Deleted");
	}
}
