package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.business.businessRules.abstracts.LanguageBusinessRulesService;
import kodlamaio.hrms.business.mapping.profiles.abstracts.LanguageMappingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.LanguageDao;
import kodlamaio.hrms.entities.concretes.Language;
import kodlamaio.hrms.entities.dtos.language.LanguageAddDto;
import kodlamaio.hrms.entities.dtos.language.LanguageUpdateDto;

@Service
public class LanguageManager implements LanguageService {

	private LanguageDao languageDao;
	private LanguageMappingService languageMappingService;
	private LanguageBusinessRulesService languageBusinessRulesService;

	@Autowired
	public LanguageManager(LanguageDao languageDao, LanguageMappingService languageMappingService,
			LanguageBusinessRulesService languageBusinessRulesService) {
		super();
		this.languageDao = languageDao;
		this.languageMappingService = languageMappingService;
		this.languageBusinessRulesService = languageBusinessRulesService;
	}

	@Override
	public DataResult<List<Language>> getList() {
		return new SuccessDataResult<List<Language>>(this.languageDao.findAll());
	}

	@Override
	public Result add(LanguageAddDto languageAddDto) {
		try {
			// Business Rules
			this.languageBusinessRulesService.languageCanNotBeDublicatedWhenInserted(languageAddDto.getLanguage(),
					languageAddDto.getResumeId());

			// Mapping
			Language language = this.languageMappingService.languageAddDtoToLanguage(languageAddDto);

			this.languageDao.save(language);
			return new SuccessResult("Added");
		} catch (Exception e) {
			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}

	}

	@Override
	public Result update(LanguageUpdateDto languageUpdateDto) {
		try {
			//Business Rules
			this.languageBusinessRulesService.languageCanNotBeDublicatedWhenUpdating(languageUpdateDto.getId(), languageUpdateDto.getLanguage());
			
			// Mapping
			Language language = this.languageMappingService.languageUpdateDtoToLanguage(languageUpdateDto);
			
			this.languageDao.save(language);
			return new SuccessResult("Language Updated");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}
		

	}

	@Override
	public Result delete(int id) {
		this.languageDao.deleteById(id);
		return new SuccessResult("Language Deleted");
	}

}
