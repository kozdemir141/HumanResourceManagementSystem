package kodlamaio.hrms.business.businessRules.abstracts;

public interface EmployerBusinessRulesService {

	void EmailCanNotBeDublicatedWhenInserted(String email) throws Exception;
	
	void emailCanNotBeDublicatedWhenUpdating(int id,String email) throws Exception;

	void WebsiteMustBeSameAsEmail(String email, String webAdress) throws Exception;
	
	void PasswordsMustBeTheSame(String password,String passwordRepeat) throws Exception;
}
