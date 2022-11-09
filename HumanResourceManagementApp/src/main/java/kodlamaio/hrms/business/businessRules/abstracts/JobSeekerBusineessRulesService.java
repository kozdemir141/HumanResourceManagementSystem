package kodlamaio.hrms.business.businessRules.abstracts;

public interface JobSeekerBusineessRulesService {

	void IdentityNumberCheck(String identityNumber) throws Exception;
	
	void EmailCanNotBeDublicatedWhenInserted(String email) throws Exception;
	
	void EmailCanNotBeDublicatedWhenUpdating(String email) throws Exception;
	
	void IdentityNumberCanNotBeDublicatedWhenInserted(String identityNumber) throws Exception;
	
	void IdentityNumberCanNotBeDublicatedWhenUpdating(String identityNumber) throws Exception;
	
	void PasswordsMustBeTheSame(String password,String passwordRepeat) throws Exception;
}
