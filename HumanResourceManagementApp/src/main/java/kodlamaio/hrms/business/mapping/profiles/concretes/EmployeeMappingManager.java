package kodlamaio.hrms.business.mapping.profiles.concretes;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.mapping.profiles.abstracts.EmployeeMappingService;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeDao;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.dtos.employee.EmployeeAddDto;
import kodlamaio.hrms.entities.dtos.employee.EmployeeUpdateDto;
import lombok.Data;

@Service
@Data
public class EmployeeMappingManager implements EmployeeMappingService {

	private EmployeeDao employeeDao;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public EmployeeMappingManager(EmployeeDao employeeDao,PasswordEncoder passwordEncoder) {
		super();
		this.employeeDao = employeeDao;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Employee employeeAddDtoToEmployee(EmployeeAddDto employeeAddDto) throws NoSuchAlgorithmException, InvalidKeySpecException {

		Employee employee = new Employee();
		employee.setEmail(employeeAddDto.getEmail());
		employee.setFirstName(employeeAddDto.getFirstName());
		employee.setLastName(employeeAddDto.getLastName());
		employee.setIsActive(false);
		
		//Password Encode
		//String passwordHash = HashingHelper.generateStrongPasswordHash(employeeAddDto.getPassword());
		employee.setPassword(passwordEncoder.encode(employeeAddDto.getPassword()));
		
		return employee;
	}

	@Override
	public Employee employeeUpdateDtoToEmployee(EmployeeUpdateDto employeeUpdateDto) {
		Employee oldEmployee = this.employeeDao.getById(employeeUpdateDto.getId());
		Employee employee = new Employee();
		
		employee.setId(employeeUpdateDto.getId());
		employee.setEmail(employeeUpdateDto.getEmail());
		employee.setPassword(oldEmployee.getPassword());
		employee.setFirstName(employeeUpdateDto.getFirstName());
		employee.setLastName(employeeUpdateDto.getLastName());
		employee.setIsActive(oldEmployee.getIsActive());
		
		return employee;
	}
}
