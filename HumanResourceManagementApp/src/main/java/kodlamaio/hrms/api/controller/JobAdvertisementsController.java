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

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementAddDto;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementDto;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementListDto;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementUpdateDto;

@RestController
@RequestMapping("/api/jobadvertisements/")
public class JobAdvertisementsController {

	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}

	@GetMapping("getall")
	public DataResult<List<JobAdvertisement>> getAll() {
		return this.jobAdvertisementService.getAll();
	}
	
	@GetMapping("getById")
	public DataResult<JobAdvertisementDto> getById(int id){
		return this.jobAdvertisementService.getJobAdvertisementDetails(id);
	}

	@GetMapping("getallactive")
	public DataResult<List<JobAdvertisement>> getAllActive() {
		return this.jobAdvertisementService.getAllActive();
	}

	@GetMapping("getJobAdvertisementDetails")
	public DataResult<List<JobAdvertisementListDto>> getJobAdvertisementDetails() {
		return this.jobAdvertisementService.getJobAdvertisementWithDetails();
	}

	@GetMapping("getSortedJobAdvertisementWithDetails")
	public DataResult<List<JobAdvertisementListDto>> getSortedJobAdvertisementWithDetails(@RequestParam String sortValue) {
		return this.jobAdvertisementService.getSortedJobAdvertisementWithDetails(sortValue);
	}

	@GetMapping("getAllByEmployerId")
	public DataResult<List<JobAdvertisement>> getAllByEmployerId(@RequestParam int employerId) {
		return this.jobAdvertisementService.getAllByEmployerId(employerId);
	}

	@GetMapping("getByEmployerAndJobPositionId")
	public DataResult<JobAdvertisement> getByEmployerAndJobPositionId(int employerId, int jobPositionId) {
		return this.jobAdvertisementService.getByEmployerAndJobPositionId(employerId, jobPositionId);
	}
	
	@PostMapping("confirm")
	public Result confirm(@RequestParam int id) {
		return this.jobAdvertisementService.confirm(id);
	}

	@PostMapping("add")
	public Result add(@Valid @RequestBody JobAdvertisementAddDto jobAdvertisementAddDto) {
		return this.jobAdvertisementService.add(jobAdvertisementAddDto);
	}

	@PostMapping("softDelete")
	public Result softDelete(@RequestParam int jobAdvertisementId) {
		return this.jobAdvertisementService.softDelete(jobAdvertisementId);
	}
	
	@PutMapping("update")
	public Result update(@Valid @RequestBody JobAdvertisementUpdateDto jobAdvertisementUpdateDto) {
		return jobAdvertisementService.update(jobAdvertisementUpdateDto);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestParam int id) {
		return jobAdvertisementService.delete(id);
	}

}
