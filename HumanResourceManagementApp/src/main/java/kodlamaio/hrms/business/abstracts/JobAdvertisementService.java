package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementAddDto;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementDto;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementListDto;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementUpdateDto;

public interface JobAdvertisementService {

	DataResult<List<JobAdvertisement>> getAll();
	
	DataResult<JobAdvertisementDto> getJobAdvertisementDetails(int id);

	DataResult<List<JobAdvertisement>> getAllByEmployerId(int employerId);

	DataResult<List<JobAdvertisement>> getAllActive();

	DataResult<List<JobAdvertisementListDto>> getJobAdvertisementWithDetails();

	DataResult<List<JobAdvertisementListDto>> getSortedJobAdvertisementWithDetails(String sortValue);

	DataResult<JobAdvertisement> getByEmployerAndJobPositionId(int employerId, int jobPositionId);
	
	Result confirm(int id);

	Result add(JobAdvertisementAddDto jobAdvertisementAddDto);

	Result softDelete(int jobAdvertisementId);
	
	Result delete(int id);
	
	Result update(JobAdvertisementUpdateDto jobAdvertisementUpdateDto);
}
