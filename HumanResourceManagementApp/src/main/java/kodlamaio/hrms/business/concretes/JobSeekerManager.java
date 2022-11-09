package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.businessRules.abstracts.JobSeekerBusineessRulesService;
import kodlamaio.hrms.business.mapping.profiles.abstracts.JobSeekerMappingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.jobSeeker.JobSeekerAddDto;
import kodlamaio.hrms.entities.dtos.jobSeeker.JobSeekerUpdateDto;

@Service
public class JobSeekerManager implements JobSeekerService {

	private JobSeekerDao jobSeekerDao;
	private JobSeekerBusineessRulesService jobSeekerBusineessRulesService;
	private JobSeekerMappingService jobSeekerMappingService;

	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao, JobSeekerBusineessRulesService jobSeekerBusineessRulesService,
			JobSeekerMappingService jobSeekerMappingService) {
		this.jobSeekerDao = jobSeekerDao;
		this.jobSeekerBusineessRulesService = jobSeekerBusineessRulesService;
		this.jobSeekerMappingService = jobSeekerMappingService;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {

		return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), "Listed");
	}

	@Override
	public DataResult<JobSeeker> getByEmail(String email) {

		return new SuccessDataResult<JobSeeker>(this.jobSeekerDao.findByEmail(email), "Getted");
	}

	@Override
	public DataResult<JobSeeker> geyByIdentityNumber(String identityNumber) {

		return new SuccessDataResult<JobSeeker>(this.jobSeekerDao.findByIdentityNumber(identityNumber), "Getted");
	}

	@Override
	public Result confirm(int id) {
		JobSeeker jobSeeker = this.jobSeekerDao.getById(id);
		jobSeeker.setIsActive(true);
		this.jobSeekerDao.save(jobSeeker);
		return new SuccessResult("Activeted");
	}

	@Override
	public Result add(JobSeekerAddDto jobSeekerAddDto) {
		try {
			// Business Rules Starts Here.
			jobSeekerBusineessRulesService.IdentityNumberCheck(jobSeekerAddDto.getIdentityNumber());
			jobSeekerBusineessRulesService.EmailCanNotBeDublicatedWhenInserted(jobSeekerAddDto.getEmail());
			jobSeekerBusineessRulesService
					.IdentityNumberCanNotBeDublicatedWhenInserted(jobSeekerAddDto.getIdentityNumber());
			jobSeekerBusineessRulesService.PasswordsMustBeTheSame(jobSeekerAddDto.getPassword(),
					jobSeekerAddDto.getPasswordRepeat());

			// Mapping
			JobSeeker jobSeeker = this.jobSeekerMappingService.jobSeekerAddDtoToJobSeeker(jobSeekerAddDto);

			this.jobSeekerDao.save(jobSeeker);
			return new SuccessResult("Added");
		} catch (Exception e) {

			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}
	}

	@Override
	public Result update(JobSeekerUpdateDto jobSeekerUpdateDto) {
		try {
			// Business Rules Starts Here.
			jobSeekerBusineessRulesService.IdentityNumberCheck(jobSeekerUpdateDto.getIdentityNumber());
			jobSeekerBusineessRulesService.EmailCanNotBeDublicatedWhenUpdating(jobSeekerUpdateDto.getEmail());
			jobSeekerBusineessRulesService
					.IdentityNumberCanNotBeDublicatedWhenUpdating(jobSeekerUpdateDto.getIdentityNumber());
			
			// Mapping
			JobSeeker jobSeeker = this.jobSeekerMappingService.jobSeekerUpdateDtoToJobSeeker(jobSeekerUpdateDto);
			
			this.jobSeekerDao.save(jobSeeker);
			return new SuccessResult("Job Seeker Updated");
			
		} catch (Exception e) {

			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}
		
	}

	@Override
	public Result delete(int id) {
		this.jobSeekerDao.deleteById(id);
		return new SuccessResult("Job Seeker Deleted");
	}
}
