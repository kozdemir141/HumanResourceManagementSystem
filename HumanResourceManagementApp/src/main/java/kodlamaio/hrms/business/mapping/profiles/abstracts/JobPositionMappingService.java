package kodlamaio.hrms.business.mapping.profiles.abstracts;

import kodlamaio.hrms.entities.concretes.JobPosition;
import kodlamaio.hrms.entities.dtos.jobPosition.JobPositionAddDto;
import kodlamaio.hrms.entities.dtos.jobPosition.JobPositionUpdateDto;

public interface JobPositionMappingService {

	JobPosition jobPositionAddDtoToJobPosition(JobPositionAddDto jobPositionAddDto);
	
	JobPosition jobPositionUpdateDtoToJobPosition(JobPositionUpdateDto jobPositionUpdateDto);
}
