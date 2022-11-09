package kodlamaio.hrms.business.mapping.profiles.abstracts;

import kodlamaio.hrms.entities.concretes.Language;
import kodlamaio.hrms.entities.dtos.language.LanguageAddDto;
import kodlamaio.hrms.entities.dtos.language.LanguageUpdateDto;

public interface LanguageMappingService {

	Language languageAddDtoToLanguage(LanguageAddDto languageAddDto);
	
	Language languageUpdateDtoToLanguage(LanguageUpdateDto languageUpdateDto);
}
