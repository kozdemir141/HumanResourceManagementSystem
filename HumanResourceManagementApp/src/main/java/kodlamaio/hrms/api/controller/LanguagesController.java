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
import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Language;
import kodlamaio.hrms.entities.dtos.language.LanguageAddDto;
import kodlamaio.hrms.entities.dtos.language.LanguageUpdateDto;

@RestController
@RequestMapping("/api/languages/")
public class LanguagesController {

	private LanguageService languageService;

	@Autowired
	public LanguagesController(LanguageService languageService) {
		super();
		this.languageService = languageService;
	}
	
	@GetMapping("getall")
	public DataResult<List<Language>> getList(){
		return this.languageService.getList();
	}
	
	@PostMapping("add")
	public Result add(@Valid @RequestBody LanguageAddDto languageAddDto) {
		return this.languageService.add(languageAddDto);
	}
	
	@PutMapping("update")
	public Result update(@Valid @RequestBody LanguageUpdateDto languageUpdateDto) {
		return languageService.update(languageUpdateDto);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestParam int id) {
		return languageService.delete(id);
	}
	
}
