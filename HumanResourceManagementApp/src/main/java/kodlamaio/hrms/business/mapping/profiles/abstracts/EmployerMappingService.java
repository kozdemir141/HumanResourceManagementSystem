package kodlamaio.hrms.business.mapping.profiles.abstracts;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.employer.EmployerAddDto;
import kodlamaio.hrms.entities.dtos.employer.EmployerUpdateDto;

public interface EmployerMappingService {

	Employer employerAddDtoToEmployer(EmployerAddDto employerAddDto)
			throws NoSuchAlgorithmException, InvalidKeySpecException;

	Employer employerUpdateDtoToEmployer(EmployerUpdateDto employerUpdateDto);
}
