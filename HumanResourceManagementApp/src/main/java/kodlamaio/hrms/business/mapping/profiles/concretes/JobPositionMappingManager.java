package kodlamaio.hrms.business.mapping.profiles.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.mapping.profiles.abstracts.JobPositionMappingService;
import kodlamaio.hrms.entities.concretes.JobPosition;
import kodlamaio.hrms.entities.dtos.jobPosition.JobPositionAddDto;
import kodlamaio.hrms.entities.dtos.jobPosition.JobPositionUpdateDto;
import lombok.Data;

@Service
@Data
public class JobPositionMappingManager implements JobPositionMappingService {

	@Override
	public JobPosition jobPositionAddDtoToJobPosition(JobPositionAddDto jobPositionAddDto) {

		JobPosition jobPosition = new JobPosition();
		jobPosition.setJobPositionName(jobPositionAddDto.getJobPositionName());

		return jobPosition;
	}

	@Override
	public JobPosition jobPositionUpdateDtoToJobPosition(JobPositionUpdateDto jobPositionUpdateDto) {
		
		JobPosition jobPosition = new JobPosition();
		jobPosition.setJobPositionId(jobPositionUpdateDto.getJobPositionId());
		jobPosition.setJobPositionName(jobPositionUpdateDto.getJobPositionName());
		
		return jobPosition;
	}

}
