package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.employer.EmployerAddDto;
import kodlamaio.hrms.entities.dtos.employer.EmployerUpdateDto;

public interface EmployerService {

	DataResult<List<Employer>> getAll(); 
	
	DataResult<Employer> getById(int id);
	
	DataResult<Employer> getByEmail(String email);
	
	Result confirm(int id);
	
	Result add(EmployerAddDto employerAddDto);
	
	Result update(EmployerUpdateDto employerUpdateDto);
	
	Result delete(int id);
}
