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

import kodlamaio.hrms.business.abstracts.ProgramingLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ProgramingLanguage;
import kodlamaio.hrms.entities.dtos.programingLanguage.ProgramingLanguageAddDto;
import kodlamaio.hrms.entities.dtos.programingLanguage.ProgramingLanguageUpdateDto;

@RestController
@RequestMapping("/api/programmingLanguages/")
public class ProgramingLanguagesController {

	private ProgramingLanguageService programingLanguageService;

	@Autowired
	public ProgramingLanguagesController(ProgramingLanguageService programingLanguageService) {
		super();
		this.programingLanguageService = programingLanguageService;
	}

	@GetMapping("getall")
	public DataResult<List<ProgramingLanguage>> getList() {
		return this.programingLanguageService.getList();
	}

	@PostMapping("add")
	public Result add(@Valid @RequestBody ProgramingLanguageAddDto programingLanguageAddDto) {
		return this.programingLanguageService.add(programingLanguageAddDto);
	}

	@PutMapping("update")
	public Result update(@Valid @RequestBody ProgramingLanguageUpdateDto programingLanguageUpdateDto) {
		return programingLanguageService.update(programingLanguageUpdateDto);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestParam int id) {
		return programingLanguageService.delete(id);
	}
}
