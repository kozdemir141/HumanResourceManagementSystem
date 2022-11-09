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

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.jobSeeker.JobSeekerAddDto;
import kodlamaio.hrms.entities.dtos.jobSeeker.JobSeekerUpdateDto;

@RestController
@RequestMapping("/api/jobseekers/")
public class JobSeekersController {

	private JobSeekerService jobSeekerService;

	@Autowired
	public JobSeekersController(JobSeekerService jobSeekerService) {
		super();
		this.jobSeekerService = jobSeekerService;
	}

	@GetMapping("getall")
	public DataResult<List<JobSeeker>> getAll() {
		return this.jobSeekerService.getAll();
	}

	@GetMapping("/getbyemail")
	public DataResult<JobSeeker> getByEmail(@RequestParam String email) {
		return this.jobSeekerService.getByEmail(email);
	}

	@GetMapping("getbyidentitynumber")
	public DataResult<JobSeeker> getByIdentityNumber(@RequestParam String identityNumber) {
		return this.jobSeekerService.geyByIdentityNumber(identityNumber);
	}
	
	@PostMapping("confirm")
	public Result confirm(@RequestParam int id) {
		return this.jobSeekerService.confirm(id);
	}

	@PostMapping("add")
	public Result add(@Valid @RequestBody JobSeekerAddDto jobSeekerAddDto) {
		return this.jobSeekerService.add(jobSeekerAddDto);
	}

	@PutMapping("update")
	public Result update(@Valid @RequestBody JobSeekerUpdateDto jobSeekerUpdateDto) {
		return jobSeekerService.update(jobSeekerUpdateDto);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestParam int id) {
		return jobSeekerService.delete(id);
	}
}
