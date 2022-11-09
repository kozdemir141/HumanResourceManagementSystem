package kodlamaio.hrms.api.controller;

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

import kodlamaio.hrms.business.abstracts.ResumeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.dtos.resume.ResumeAddDto;
import kodlamaio.hrms.entities.dtos.resume.ResumeUpdateDto;
import kodlamaio.hrms.entities.dtos.resume.ResumeWithAllRelatedEntitiesDto;

@RestController
@RequestMapping("/api/resumes/")
public class ResumesController {

	private ResumeService resumeService;

	@Autowired
	public ResumesController(ResumeService resumeService) {
		super();
		this.resumeService = resumeService;
	}
	
	@GetMapping("getById")
	public DataResult<Resume> getById(@RequestParam int resumeId){
		return this.resumeService.getById(resumeId);
	}
	
	@GetMapping("getByJobSeekerId")
	public DataResult<Resume> getByJobSeekerId(@RequestParam int jobSeekerId){
		return this.resumeService.getByJobSeekerId(jobSeekerId);
	}
	
	@GetMapping("getResumeDetailsByJobSeekerId")
	
	public DataResult<ResumeWithAllRelatedEntitiesDto> getResumeDetailsByJobSeekerId(@RequestParam int jobSeekerId){
		return this.resumeService.getResumeDetailsByJobSeekerId(jobSeekerId);
	}
	
	@PostMapping("add")
	public Result add(@Valid @RequestBody ResumeAddDto resumeAddDto) {
		
		return this.resumeService.add(resumeAddDto);
	}
	
	@PutMapping("update")
	public Result update(@Valid @RequestBody ResumeUpdateDto resumeUpdateDto) {
		return this.resumeService.update(resumeUpdateDto);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.resumeService.delete(id);
	}
}
