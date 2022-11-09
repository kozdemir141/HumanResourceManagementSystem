package kodlamaio.hrms.business.businessRules.abstracts;

public interface JobPositionBusinessRulesService {
	
	void jobPositionNameCanNotBeDublicatedWhenInserted(String jobPositionName) throws Exception;
	
	void jobPositionNameCanNotBeDublicatedWhenUpdating(int jobPositionId ,String jobPositionName) throws Exception;

}
