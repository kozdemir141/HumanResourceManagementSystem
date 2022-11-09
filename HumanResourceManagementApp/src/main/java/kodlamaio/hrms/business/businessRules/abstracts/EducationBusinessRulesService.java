package kodlamaio.hrms.business.businessRules.abstracts;

import java.time.LocalDate;

public interface EducationBusinessRulesService {

	void graduationDateCanNotBeforeStartingDate(LocalDate startingDate, LocalDate graduationDate) throws Exception;

	void alreadyHasAContinuingEducation(LocalDate graduationDate, int resumeId) throws Exception;

	void updatedEducationAlreadyHasAContinuingEducation(int id, LocalDate graduationDate) throws Exception;
}
