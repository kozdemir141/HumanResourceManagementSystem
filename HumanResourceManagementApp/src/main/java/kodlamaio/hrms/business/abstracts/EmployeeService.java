package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.dtos.employee.EmployeeAddDto;
import kodlamaio.hrms.entities.dtos.employee.EmployeeUpdateDto;

public interface EmployeeService {

	DataResult<List<Employee>> getAll();
	
	DataResult<Employee> getById(int id);
	
	Result confirm(int id);
	
	Result add(EmployeeAddDto employeeAddDto);
	
	Result delete(int id);
	
	Result update(EmployeeUpdateDto employeeUpdateDto);
}
