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

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.employer.EmployerAddDto;
import kodlamaio.hrms.entities.dtos.employer.EmployerUpdateDto;

@RestController
@RequestMapping("/api/employers/")
public class EmployersController {

	private EmployerService employerService;

	@Autowired
	public EmployersController(EmployerService employerService) {
		super();
		this.employerService = employerService;
	}

	@GetMapping("getall")
	public DataResult<List<Employer>> getAll() {
		return this.employerService.getAll();
	}

	@GetMapping("getbyemail")
	public DataResult<Employer> getByEmail(@RequestParam String email) {
		return this.employerService.getByEmail(email);
	}
	
	@GetMapping("getbyid")
	public DataResult<Employer> getById(@RequestParam int id){
		return this.employerService.getById(id);
	}

	@PostMapping("confirm")
	public Result confirm(@RequestParam int id) {
		return this.employerService.confirm(id);
	}
	
	@PostMapping("add")
	public Result add(@Valid @RequestBody EmployerAddDto employerAddDto) {
		return this.employerService.add(employerAddDto);
	}
	
	@PutMapping("update")
	public Result update(@Valid @RequestBody EmployerUpdateDto employerUpdateDto) {
		return employerService.update(employerUpdateDto);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestParam int id) {
		return employerService.delete(id);
	}
}
