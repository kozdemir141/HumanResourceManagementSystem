package kodlamaio.hrms.business.businessRules.abstracts;

import java.time.LocalDate;

public interface JobAdvertisementBusinessRulesService {
	
	void deadlineCanNotBeforeReleaseDate(LocalDate releaseDate, LocalDate deadline) throws Exception;

	void JobPositionCanNotBeDublicatedWhenInserted(int employerId, int jobPositionId) throws Exception;
	
	void JobPositionCanNotBeDublicatedWhenUpdating(int jobAdvertisementId, int jobPositionId) throws Exception;
	
	void salaryStandarts(double minSalary,double maxSalary) throws Exception;
}
