package kodlamaio.hrms.business.businessRules.abstracts;

public interface LanguageBusinessRulesService {

	void languageCanNotBeDublicatedWhenInserted(String language, int resumeId) throws Exception;

	void languageCanNotBeDublicatedWhenUpdating(int id, String language) throws Exception;
}
