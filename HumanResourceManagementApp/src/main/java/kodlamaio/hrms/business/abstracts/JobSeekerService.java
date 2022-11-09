package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.jobSeeker.JobSeekerAddDto;
import kodlamaio.hrms.entities.dtos.jobSeeker.JobSeekerUpdateDto;

public interface JobSeekerService {

	DataResult<List<JobSeeker>> getAll();
	
	DataResult<JobSeeker> getByEmail(String email);
	
	DataResult<JobSeeker> geyByIdentityNumber(String identityNumber);
	
	Result confirm(int id);
	
	Result add(JobSeekerAddDto jobSeekerAddDto);
	
	Result update(JobSeekerUpdateDto jobSeekerUpdateDto);
	
	Result delete(int id);
}
