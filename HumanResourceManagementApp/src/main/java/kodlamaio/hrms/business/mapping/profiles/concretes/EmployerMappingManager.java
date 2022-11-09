package kodlamaio.hrms.business.mapping.profiles.concretes;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.mapping.profiles.abstracts.EmployerMappingService;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.employer.EmployerAddDto;
import kodlamaio.hrms.entities.dtos.employer.EmployerUpdateDto;
import lombok.Data;

@Service
@Data
public class EmployerMappingManager implements EmployerMappingService {

	private EmployerDao employerDao;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public EmployerMappingManager(EmployerDao employerDao, PasswordEncoder passwordEncoder) {
		super();
		this.employerDao = employerDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Employer employerAddDtoToEmployer(EmployerAddDto employerAddDto)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		Employer employer = new Employer();
		employer.setEmail(employerAddDto.getEmail());
		employer.setCompanyName(employerAddDto.getCompanyName());
		employer.setWebAdress("www." + employerAddDto.getWebAdress() + ".com");
		employer.setPhoneNumber(employerAddDto.getPhoneNumber());
		employer.setIsActive(false);

		// Password Encode
		// String passwordHash = HashingHelper.generateStrongPasswordHash(employerAddDto.getPassword());
		employer.setPassword(passwordEncoder.encode(employerAddDto.getPassword()));

		return employer;
	}

	@Override
	public Employer employerUpdateDtoToEmployer(EmployerUpdateDto employerUpdateDto) {

		Employer oldEmployer = this.employerDao.getById(employerUpdateDto.getId());

		Employer employer = new Employer();
		employer.setId(employerUpdateDto.getId());
		employer.setEmail(employerUpdateDto.getEmail());
		employer.setPassword(oldEmployer.getPassword());
		employer.setCompanyName(employerUpdateDto.getCompanyName());
		employer.setWebAdress("www." + employerUpdateDto.getWebAdress() + ".com");
		employer.setPhoneNumber(employerUpdateDto.getPhoneNumber());
		employer.setIsActive(oldEmployer.getIsActive());

		return employer;
	}
}
