package kodlamaio.hrms.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.dtos.employee.EmployeeAddDto;
import kodlamaio.hrms.entities.dtos.employee.EmployeeUpdateDto;

@RestController
@RequestMapping("/api/systememployees/")
public class EmployeesController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeesController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping("getAll")
	public DataResult<List<Employee>> getAll() {
		return this.employeeService.getAll();
	}

	@GetMapping("getById")
	public DataResult<Employee> getById(@RequestParam int employeeId) {
		return this.employeeService.getById(employeeId);
	}
	
	@PostMapping("confirm")
	public Result confirm(@RequestParam int id) {
		return this.employeeService.confirm(id);
	}

	@PostMapping("add")
	public Result add(@Valid @RequestBody EmployeeAddDto employeeAddDto) {
		return this.employeeService.add(employeeAddDto);
	}
	
	@PutMapping("update")
	public Result update(@Valid @RequestBody EmployeeUpdateDto employeeUpdateDto) {
		return employeeService.update(employeeUpdateDto);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestParam int employeeId) {
		return employeeService.delete(employeeId);
	}
}
