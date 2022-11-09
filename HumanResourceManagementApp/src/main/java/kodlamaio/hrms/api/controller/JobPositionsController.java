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

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosition;
import kodlamaio.hrms.entities.dtos.jobPosition.JobPositionAddDto;
import kodlamaio.hrms.entities.dtos.jobPosition.JobPositionUpdateDto;

@RestController
@RequestMapping("/api/jobpositions/")
public class JobPositionsController {

	private JobPositionService jobPositionService;

	@Autowired
	public JobPositionsController(JobPositionService jobPositionService) {
		super();
		this.jobPositionService = jobPositionService;
	}

	@GetMapping("getall")
	public DataResult<List<JobPosition>> getAll() {
		return this.jobPositionService.getAll();
	}

	@GetMapping("getbyid")
	public DataResult<JobPosition> getById(@RequestParam int id) {
		return this.jobPositionService.getById(id);
	}
	
	@GetMapping("getAllByNameIsNot")
	public DataResult<List<JobPosition>> getAllByNameIsNot(@RequestParam String jobPositionName) {
		return this.jobPositionService.getAllByNameIsNot(jobPositionName);
	}

	@PostMapping("add")
	public Result add(@Valid @RequestBody JobPositionAddDto jobPositionAddDto) {
		return this.jobPositionService.add(jobPositionAddDto);
	}
	
	@PutMapping("update")
	public Result update(@Valid @RequestBody JobPositionUpdateDto jobPositionUpdateDto) {
		return jobPositionService.update(jobPositionUpdateDto);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestParam int id) {
		return jobPositionService.delete(id);
	}

}
