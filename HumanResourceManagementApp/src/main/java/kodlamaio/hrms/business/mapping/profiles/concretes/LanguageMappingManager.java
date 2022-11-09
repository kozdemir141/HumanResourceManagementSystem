package kodlamaio.hrms.business.mapping.profiles.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.mapping.profiles.abstracts.LanguageMappingService;
import kodlamaio.hrms.dataAccess.abstracts.LanguageDao;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.entities.concretes.Language;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.dtos.language.LanguageAddDto;
import kodlamaio.hrms.entities.dtos.language.LanguageUpdateDto;
import lombok.Data;

@Service
@Data
public class LanguageMappingManager implements LanguageMappingService {

	private LanguageDao languageDao;
	private ResumeDao resumeDao;

	@Autowired
	public LanguageMappingManager(ResumeDao resumeDao, LanguageDao languageDao) {
		super();
		this.resumeDao = resumeDao;
		this.languageDao = languageDao;
	}

	@Override
	public Language languageAddDtoToLanguage(LanguageAddDto languageAddDto) {

		Language language = new Language();
		Resume resume = this.resumeDao.getById(languageAddDto.getResumeId());
		language.setLanguage(languageAddDto.getLanguage());
		language.setLanguageLevel(languageAddDto.getLanguageLevel());
		language.setResume(resume);

		return language;
	}

	@Override
	public Language languageUpdateDtoToLanguage(LanguageUpdateDto languageUpdateDto) {
		
		Language oldLanguage = this.languageDao.getById(languageUpdateDto.getId());
		Language language = new Language();
		language.setId(languageUpdateDto.getId());
		language.setLanguage(languageUpdateDto.getLanguage());
		language.setLanguageLevel(languageUpdateDto.getLanguageLevel());
		language.setResume(oldLanguage.getResume());
		
		return language;
	}
}
