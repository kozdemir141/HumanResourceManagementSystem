package kodlamaio.hrms.business.mapping.profiles.abstracts;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.dtos.employee.EmployeeAddDto;
import kodlamaio.hrms.entities.dtos.employee.EmployeeUpdateDto;

public interface EmployeeMappingService {

	Employee employeeAddDtoToEmployee(EmployeeAddDto employeeAddDto)
			throws NoSuchAlgorithmException, InvalidKeySpecException;

	Employee employeeUpdateDtoToEmployee(EmployeeUpdateDto employeeUpdateDto);
}
