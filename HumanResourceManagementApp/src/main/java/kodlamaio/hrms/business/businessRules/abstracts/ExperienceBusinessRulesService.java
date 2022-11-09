package kodlamaio.hrms.business.businessRules.abstracts;

import java.time.LocalDate;

public interface ExperienceBusinessRulesService {

	void terminationDateCanNotBeforeStartingDate(LocalDate startingDate, LocalDate terminationDate) throws Exception;

	void alreadyHasAContinuingExperience(LocalDate terminationDate, int resumeId) throws Exception;

	void updatedExperienceAlreadyHasAContinuingExperience(int id, LocalDate terminationDate) throws Exception;
}
