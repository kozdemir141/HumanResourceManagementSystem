package kodlamaio.hrms.business.mapping.profiles.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.mapping.profiles.abstracts.JobAdvertisementMappingService;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementAddDto;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementDto;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementUpdateDto;
import lombok.Data;

@Service
@Data
public class JobAdvertisementMappingManager implements JobAdvertisementMappingService {

	private JobAdvertisementDao jobAdvertisementDao;
	private JobPositionDao jobPositionDao;
	private EmployerDao employerDao;
	private CityDao cityDao;

	@Autowired
	public JobAdvertisementMappingManager(JobPositionDao jobPositionDao, EmployerDao employerDao, CityDao cityDao,
			JobAdvertisementDao jobAdvertisementDao) {
		super();
		this.jobPositionDao = jobPositionDao;
		this.employerDao = employerDao;
		this.cityDao = cityDao;
		this.jobAdvertisementDao = jobAdvertisementDao;
	}

	@Override
	public JobAdvertisement jobAdvertisementAddDtoToJobAdvertisement(JobAdvertisementAddDto jobAdvertisementAddDto) {

		JobAdvertisement jobAdvertisement = new JobAdvertisement();
		jobAdvertisement.setDescription(jobAdvertisementAddDto.getDescription());
		jobAdvertisement.setMinSalary(jobAdvertisementAddDto.getMinSalary());
		jobAdvertisement.setMaxSalary(jobAdvertisementAddDto.getMaxSalary());
		jobAdvertisement.setNumberOfPositions(jobAdvertisementAddDto.getNumberOfPositions());
		jobAdvertisement.setReleaseDate(LocalDate.now());
		jobAdvertisement.setDeadline(jobAdvertisementAddDto.getDeadline());
		jobAdvertisement.setIsActive(false);

		jobAdvertisement.setJobPosition(this.jobPositionDao.getById(jobAdvertisementAddDto.getJobPositionId()));
		jobAdvertisement.setEmployer(this.employerDao.getById(jobAdvertisementAddDto.getEmployerId()));
		jobAdvertisement.setCity(this.cityDao.getById(jobAdvertisementAddDto.getCityId()));

		return jobAdvertisement;
	}

	@Override
	public JobAdvertisement jobAdvertisementUpdateDtoToJobAdvertisement(
			JobAdvertisementUpdateDto jobAdvertisementUpdateDto) {

		JobAdvertisement oldJobAdvertisement = this.jobAdvertisementDao
				.getById(jobAdvertisementUpdateDto.getJobAdvertisementId());
		JobAdvertisement jobAdvertisement = new JobAdvertisement();

		jobAdvertisement.setJobAdvertisementId(jobAdvertisementUpdateDto.getJobAdvertisementId());
		jobAdvertisement.setDescription(jobAdvertisementUpdateDto.getDescription());
		jobAdvertisement.setMinSalary(jobAdvertisementUpdateDto.getMinSalary());
		jobAdvertisement.setMaxSalary(jobAdvertisementUpdateDto.getMaxSalary());
		jobAdvertisement.setNumberOfPositions(jobAdvertisementUpdateDto.getNumberOfPositions());
		jobAdvertisement.setReleaseDate(oldJobAdvertisement.getReleaseDate());
		jobAdvertisement.setDeadline(jobAdvertisementUpdateDto.getDeadline());
		jobAdvertisement.setIsActive(oldJobAdvertisement.getIsActive());

		jobAdvertisement.setJobPosition(this.jobPositionDao.getById(jobAdvertisementUpdateDto.getJobPositionId()));
		jobAdvertisement.setEmployer(oldJobAdvertisement.getEmployer());
		jobAdvertisement.setCity(this.cityDao.getById(jobAdvertisementUpdateDto.getCityId()));

		return jobAdvertisement;
	}

	@Override
	public JobAdvertisementDto getJobAdvertisementDto(int id) {
		JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.getById(id);
		JobAdvertisementDto jobAdvertisementDto = new JobAdvertisementDto();
		
		jobAdvertisementDto.setJobAdvertisementId(jobAdvertisement.getJobAdvertisementId());
		jobAdvertisementDto.setDescription(jobAdvertisement.getDescription());
		jobAdvertisementDto.setMinSalary(jobAdvertisement.getMinSalary());
		jobAdvertisementDto.setMaxSalary(jobAdvertisement.getMaxSalary());
		jobAdvertisementDto.setNumberOfPositions(jobAdvertisement.getNumberOfPositions());
		jobAdvertisementDto.setReleaseDate(jobAdvertisement.getReleaseDate());
		jobAdvertisementDto.setDeadline(jobAdvertisement.getDeadline());
		jobAdvertisementDto.setIsActive(jobAdvertisement.getIsActive());
		jobAdvertisementDto.setJobPositionName(jobAdvertisement.getJobPosition().getJobPositionName());
		jobAdvertisementDto.setCompanyName(jobAdvertisement.getEmployer().getCompanyName());
		
		return jobAdvertisementDto;
	}
}
