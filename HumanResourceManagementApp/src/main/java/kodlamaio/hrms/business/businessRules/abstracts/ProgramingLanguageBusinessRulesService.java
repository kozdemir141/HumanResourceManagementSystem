package kodlamaio.hrms.business.businessRules.abstracts;

public interface ProgramingLanguageBusinessRulesService {

	void programingLanguageCanNotBeDublicatedWhenInserted(String programingLanguage, int resumeId) throws Exception;

	void programingLanguageCanNotBeDublicatedWhenUpdating(int id, String programingLanguage) throws Exception;
}
