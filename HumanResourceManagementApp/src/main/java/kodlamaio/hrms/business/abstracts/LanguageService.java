package kodlamaio.hrms.business.abstracts;


import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Language;
import kodlamaio.hrms.entities.dtos.language.LanguageAddDto;
import kodlamaio.hrms.entities.dtos.language.LanguageUpdateDto;

public interface LanguageService {

	DataResult<List<Language>> getList();
	
	Result add(LanguageAddDto languageAddDto);
	
	Result update(LanguageUpdateDto languageUpdateDto);
	
	Result delete(int id);
}
