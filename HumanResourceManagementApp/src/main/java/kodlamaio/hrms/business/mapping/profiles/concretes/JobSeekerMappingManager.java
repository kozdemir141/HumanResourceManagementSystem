package kodlamaio.hrms.business.mapping.profiles.concretes;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.mapping.profiles.abstracts.JobSeekerMappingService;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.jobSeeker.JobSeekerAddDto;
import kodlamaio.hrms.entities.dtos.jobSeeker.JobSeekerUpdateDto;
import lombok.Data;

@Service
@Data
public class JobSeekerMappingManager implements JobSeekerMappingService {

	private JobSeekerDao jobSeekerDao;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public JobSeekerMappingManager(JobSeekerDao jobSeekerDao,PasswordEncoder passwordEncoder) {
		super();
		this.jobSeekerDao = jobSeekerDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public JobSeeker jobSeekerAddDtoToJobSeeker(JobSeekerAddDto jobSeekerAddDto) throws NoSuchAlgorithmException, InvalidKeySpecException {

		JobSeeker jobSeeker = new JobSeeker();
		jobSeeker.setEmail(jobSeekerAddDto.getEmail());
		jobSeeker.setIdentityNumber(jobSeekerAddDto.getIdentityNumber());
		jobSeeker.setFirstName(jobSeekerAddDto.getFirstName());
		jobSeeker.setLastName(jobSeekerAddDto.getLastName());
		jobSeeker.setBirthDate(jobSeekerAddDto.getBirthDate());
		jobSeeker.setIsActive(false);

		//Password Encode
		//String passwordHash = HashingHelper.generateStrongPasswordHash(jobSeekerAddDto.getPassword());
		jobSeeker.setPassword(passwordEncoder.encode(jobSeekerAddDto.getPassword()));

		return jobSeeker;
	}

	@Override
	public JobSeeker jobSeekerUpdateDtoToJobSeeker(JobSeekerUpdateDto jobSeekerUpdateDto) {

		JobSeeker oldJobSeeker = this.jobSeekerDao.getById(jobSeekerUpdateDto.getId());
		JobSeeker jobSeeker = new JobSeeker();
		jobSeeker.setId(jobSeekerUpdateDto.getId());
		jobSeeker.setEmail(jobSeekerUpdateDto.getEmail());
		jobSeeker.setPassword(oldJobSeeker.getPassword());
		jobSeeker.setIdentityNumber(jobSeekerUpdateDto.getIdentityNumber());
		jobSeeker.setFirstName(jobSeekerUpdateDto.getFirstName());
		jobSeeker.setLastName(jobSeekerUpdateDto.getLastName());
		jobSeeker.setBirthDate(jobSeekerUpdateDto.getBirthDate());
		jobSeeker.setIsActive(oldJobSeeker.getIsActive());

		return jobSeeker;
	}
}
