package kodlamaio.hrms.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import kodlamaio.hrms.business.abstracts.ExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Experience;
import kodlamaio.hrms.entities.dtos.experience.ExperienceAddDto;
import kodlamaio.hrms.entities.dtos.experience.ExperienceUpdateDto;

@RestController
@RequestMapping("/api/experiences/")
public class ExperiencesController {

	private ExperienceService experienceService;

	@Autowired
	public ExperiencesController(ExperienceService experienceService) {
		super();
		this.experienceService = experienceService;
	}
	
	@GetMapping("getAllByResumeId")
	public DataResult<List<Experience>> getAllByResumeId(@RequestParam int resumeId){
		return this.experienceService.getAllByResumeId(resumeId);
	}
	
	@GetMapping("getAllByResumeIdSortedByTerminationDate")
	public DataResult<List<Experience>> getAllByResumeIdSortedByTerminationDate(@RequestParam int resumeId){
		return this.experienceService.getAllByResumeIdSortedByTerminationDate(resumeId);
	}
	
	@PostMapping("add")
	public Result add(@Valid @RequestBody ExperienceAddDto experienceAddDto) {
		return this.experienceService.add(experienceAddDto);
	}
	
	@PutMapping("update")
	public Result update(@Valid @RequestBody ExperienceUpdateDto experienceUpdateDto) {
		return experienceService.update(experienceUpdateDto);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestParam int experienceId) {
		return experienceService.delete(experienceId);
	}
}
