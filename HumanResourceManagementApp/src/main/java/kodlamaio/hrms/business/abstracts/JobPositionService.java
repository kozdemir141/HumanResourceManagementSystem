package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosition;
import kodlamaio.hrms.entities.dtos.jobPosition.JobPositionAddDto;
import kodlamaio.hrms.entities.dtos.jobPosition.JobPositionUpdateDto;

public interface JobPositionService {

	DataResult<List<JobPosition>> getAll();
	
	DataResult<JobPosition> getById(int id);
	
	DataResult<List<JobPosition>> getAllByNameIsNot(String jobPositionName);

	Result add(JobPositionAddDto jobPositionAddDto);
	
	Result update(JobPositionUpdateDto jobPositionUpdateDto);
	
	Result delete(int id);
}
