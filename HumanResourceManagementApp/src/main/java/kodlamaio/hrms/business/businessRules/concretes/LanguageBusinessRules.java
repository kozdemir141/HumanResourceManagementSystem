package kodlamaio.hrms.business.businessRules.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.businessRules.abstracts.LanguageBusinessRulesService;
import kodlamaio.hrms.dataAccess.abstracts.LanguageDao;
import kodlamaio.hrms.entities.concretes.Language;
import lombok.Data;

@Service
@Data
public class LanguageBusinessRules implements LanguageBusinessRulesService {

	private LanguageDao languageDao;

	@Autowired
	public LanguageBusinessRules(LanguageDao languageDao) {
		super();
		this.languageDao = languageDao;
	}

	@Override
	public void languageCanNotBeDublicatedWhenInserted(String language, int resumeId) throws Exception {

		if (this.languageDao.findByLanguageAndResume_Id(language, resumeId) != null) {
			throw new Exception("Language Cannot Be Dublicated When Inserted");
		}

	}

	@Override
	public void languageCanNotBeDublicatedWhenUpdating(int id, String language) throws Exception {
		
		Language oldLanguage = this.languageDao.getById(id);
		
		if (oldLanguage != this.languageDao.findByLanguageAndResume_Id(language, oldLanguage.getResume().getId())) {
			
			if (this.languageDao.findByLanguageAndResume_Id(language, oldLanguage.getResume().getId()) != null) {
				
				throw new Exception("Language Cannot Be Dublicated When Updating");
			}
		}
		
	}
}
