package kodlamaio.hrms.business.mapping.profiles.abstracts;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.jobSeeker.JobSeekerAddDto;
import kodlamaio.hrms.entities.dtos.jobSeeker.JobSeekerUpdateDto;

public interface JobSeekerMappingService {

	JobSeeker jobSeekerAddDtoToJobSeeker(JobSeekerAddDto jobSeekerAddDto)
			throws NoSuchAlgorithmException, InvalidKeySpecException;

	JobSeeker jobSeekerUpdateDtoToJobSeeker(JobSeekerUpdateDto jobSeekerUpdateDto);
}
