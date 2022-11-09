package kodlamaio.hrms.business.businessRules.abstracts;

public interface EmployeeBusinessRulesService {

	void passwordsMustBeTheSame(String password,String passwordRepeat) throws Exception;
	
	void emailCanNotBeDublicatedWhenInserted(String email) throws Exception;
	
	void emailCanNotBeDublicatedWhenUpdating(int id,String email) throws Exception;
}
