package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.business.businessRules.abstracts.JobAdvertisementBusinessRulesService;
import kodlamaio.hrms.business.mapping.profiles.abstracts.JobAdvertisementMappingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementAddDto;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementDto;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementListDto;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementUpdateDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private JobAdvertisementDao jobAdvertisementDao;
	private JobAdvertisementBusinessRulesService jobAdvertisementBusinessRulesService;
	private JobAdvertisementMappingService jobAdvertisementMappingService;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao,
			JobAdvertisementBusinessRulesService jobAdvertisementBusinessRulesService,
			JobAdvertisementMappingService jobAdvertisementMappingService) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.jobAdvertisementBusinessRulesService = jobAdvertisementBusinessRulesService;
		this.jobAdvertisementMappingService = jobAdvertisementMappingService;
	}

	@Override
	public DataResult<JobAdvertisementDto> getJobAdvertisementDetails(int id) {
		JobAdvertisementDto jobAdvertisementDto = this.jobAdvertisementMappingService.getJobAdvertisementDto(id);
		return new SuccessDataResult<JobAdvertisementDto>(jobAdvertisementDto);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {

		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActive() {

		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActiveTrue());
	}

	@Override
	public DataResult<List<JobAdvertisementListDto>> getJobAdvertisementWithDetails() {

		return new SuccessDataResult<List<JobAdvertisementListDto>>(
				this.jobAdvertisementDao.getJobAdvertisementListDto());
	}

	@Override
	public DataResult<List<JobAdvertisementListDto>> getSortedJobAdvertisementWithDetails(String sortValue) {

		Sort sort;

		if (sortValue.equals("asc")) {
			sort = Sort.by(Sort.Direction.ASC, "releaseDate");
		} else {
			sort = Sort.by(Sort.Direction.DESC, "releaseDate");
		}

		return new SuccessDataResult<List<JobAdvertisementListDto>>(
				this.jobAdvertisementDao.getJobAdvertisementListDtoByReleaseDate(sort));
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByEmployerId(int employerId) {

		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByEmployer_Id(employerId));
	}

	@Override
	public DataResult<JobAdvertisement> getByEmployerAndJobPositionId(int employerId, int jobPositionId) {

		return new SuccessDataResult<JobAdvertisement>(
				this.jobAdvertisementDao.findByEmployer_IdAndJobPosition_JobPositionId(employerId, jobPositionId));
	}

	@Override
	public Result confirm(int id) {
		JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.getById(id);
		jobAdvertisement.setIsActive(true);
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult("Activeted");
	}

	@Override
	public Result add(JobAdvertisementAddDto jobAdvertisementAddDto) {

		try {
			// Business Rules Start Here.
			jobAdvertisementBusinessRulesService.JobPositionCanNotBeDublicatedWhenInserted(
					jobAdvertisementAddDto.getEmployerId(), jobAdvertisementAddDto.getJobPositionId());
			jobAdvertisementBusinessRulesService.deadlineCanNotBeforeReleaseDate(LocalDate.now(),
					jobAdvertisementAddDto.getDeadline());
			jobAdvertisementBusinessRulesService.salaryStandarts(jobAdvertisementAddDto.getMinSalary(),
					jobAdvertisementAddDto.getMaxSalary());

			// Mapping
			JobAdvertisement jobAdvertisement = jobAdvertisementMappingService
					.jobAdvertisementAddDtoToJobAdvertisement(jobAdvertisementAddDto);

			this.jobAdvertisementDao.save(jobAdvertisement);
			return new SuccessResult("Successfully Added");
		} catch (Exception e) {
			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}

	}

	@Override
	public Result softDelete(int jobAdvertisementId) {

		JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.getById(jobAdvertisementId);

		jobAdvertisement.setIsActive(false);

		this.jobAdvertisementDao.save(jobAdvertisement);

		return new SuccessResult("Pasif İlan haline getirildi");
	}

	@Override
	public Result delete(int id) {

		this.jobAdvertisementDao.deleteById(id);
		return new SuccessResult("Job Advertisement Deleted");
	}

	@Override
	public Result update(JobAdvertisementUpdateDto jobAdvertisementUpdateDto) {
		try {
			// Business Rules Start Here.
			jobAdvertisementBusinessRulesService.JobPositionCanNotBeDublicatedWhenUpdating(
					jobAdvertisementUpdateDto.getJobAdvertisementId(), jobAdvertisementUpdateDto.getJobPositionId());
			jobAdvertisementBusinessRulesService.deadlineCanNotBeforeReleaseDate(LocalDate.now(),
					jobAdvertisementUpdateDto.getDeadline());
			jobAdvertisementBusinessRulesService.salaryStandarts(jobAdvertisementUpdateDto.getMinSalary(),
					jobAdvertisementUpdateDto.getMaxSalary());
			// Mapping
			JobAdvertisement jobAdvertisement = this.jobAdvertisementMappingService
					.jobAdvertisementUpdateDtoToJobAdvertisement(jobAdvertisementUpdateDto);
			this.jobAdvertisementDao.save(jobAdvertisement);
			return new SuccessResult("Job Advertisement Updated");
		} catch (Exception e) {
			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}

	}
}
