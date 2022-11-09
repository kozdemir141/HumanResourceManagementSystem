package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.business.businessRules.abstracts.EmployeeBusinessRulesService;
import kodlamaio.hrms.business.mapping.profiles.abstracts.EmployeeMappingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeDao;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.dtos.employee.EmployeeAddDto;
import kodlamaio.hrms.entities.dtos.employee.EmployeeUpdateDto;

@Service
public class EmployeeManager implements EmployeeService {

	private EmployeeDao employeeDao;
	private EmployeeMappingService employeeMappingService;
	private EmployeeBusinessRulesService employeeBusinessRulesService;

	@Autowired
	public EmployeeManager(EmployeeDao employeeDao, EmployeeMappingService employeeMappingService,
			EmployeeBusinessRulesService employeeBusinessRulesService) {
		super();
		this.employeeDao = employeeDao;
		this.employeeMappingService = employeeMappingService;
		this.employeeBusinessRulesService = employeeBusinessRulesService;
	}

	@Override
	public DataResult<List<Employee>> getAll() {

		return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(), "Listed");
	}

	@Override
	public DataResult<Employee> getById(int employeeId) {

		return new SuccessDataResult<Employee>(this.employeeDao.getById(employeeId));
	}

	@Override
	public Result confirm(int id) {
		Employee employee = this.employeeDao.getById(id);
		employee.setIsActive(true);
		this.employeeDao.save(employee);
		return new SuccessResult("Activeted");
	}

	@Override
	public Result add(EmployeeAddDto employeeAddDto) {

		try {
			// Business Rules
			employeeBusinessRulesService.passwordsMustBeTheSame(employeeAddDto.getPassword(),
					employeeAddDto.getPasswordRepeat());
			employeeBusinessRulesService.emailCanNotBeDublicatedWhenInserted(employeeAddDto.getEmail());
			// Mapping
			Employee employee = this.employeeMappingService.employeeAddDtoToEmployee(employeeAddDto);

			this.employeeDao.save(employee);
			return new SuccessResult("Succesfully Added");
		} catch (Exception e) {
			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}

	}

	@Override
	public Result delete(int id) {
		this.employeeDao.deleteById(id);
		return new SuccessResult("Employee Deleted");
	}

	@Override
	public Result update(EmployeeUpdateDto employeeUpdateDto) {
		try {
			//Business Rules
			this.employeeBusinessRulesService.emailCanNotBeDublicatedWhenUpdating(employeeUpdateDto.getId(), employeeUpdateDto.getEmail());
			// Mapping
			Employee employee = this.employeeMappingService.employeeUpdateDtoToEmployee(employeeUpdateDto);
			this.employeeDao.save(employee);

			return new SuccessResult("Employee Updated");
		} catch (Exception e) {
			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}

	}
}
