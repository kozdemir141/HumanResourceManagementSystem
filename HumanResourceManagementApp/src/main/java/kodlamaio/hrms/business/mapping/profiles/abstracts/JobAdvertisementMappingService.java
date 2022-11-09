package kodlamaio.hrms.business.mapping.profiles.abstracts;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementAddDto;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementDto;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementUpdateDto;

public interface JobAdvertisementMappingService {

	JobAdvertisement jobAdvertisementAddDtoToJobAdvertisement(JobAdvertisementAddDto jobAdvertisementAddDto);
	
	JobAdvertisement jobAdvertisementUpdateDtoToJobAdvertisement(JobAdvertisementUpdateDto jobAdvertisementUpdateDto);
	
	JobAdvertisementDto getJobAdvertisementDto(int id);
}
