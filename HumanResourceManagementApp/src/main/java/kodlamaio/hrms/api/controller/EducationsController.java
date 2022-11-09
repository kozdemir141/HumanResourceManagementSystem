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
import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Education;
import kodlamaio.hrms.entities.dtos.education.EducationAddDto;
import kodlamaio.hrms.entities.dtos.education.EducationUpdateDto;

@RestController
@RequestMapping("/api/educations/")
public class EducationsController {

	private EducationService educationService;

	@Autowired
	public EducationsController(EducationService educationService) {
		super();
		this.educationService = educationService;
	}

	@GetMapping("getAllByResumeId")
	public DataResult<List<Education>> getAllByResumeId(@RequestParam int resumeId) {
		return this.educationService.getAllByResumeId(resumeId);
	}

	@GetMapping("getAllByResumeIdSortedByGraduationDate")
	public DataResult<List<Education>> getAllByResumeIdSortedByGraduationDate(@RequestParam int resumeId) {
		return this.educationService.getAllByResumeIdSortedByGraduationDate(resumeId);
	}

	@PostMapping("add")
	public Result add(@Valid @RequestBody EducationAddDto educationAddDto) {
		return this.educationService.add(educationAddDto);
	}

	@PutMapping("update")
	public Result update(@Valid @RequestBody EducationUpdateDto educationUpdateDto) {
		return educationService.update(educationUpdateDto);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestParam int educationId) {
		return educationService.delete(educationId);
	}
}
