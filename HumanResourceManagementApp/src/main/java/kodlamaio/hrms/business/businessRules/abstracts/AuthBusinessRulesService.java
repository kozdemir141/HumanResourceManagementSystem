package kodlamaio.hrms.business.businessRules.abstracts;

import kodlamaio.hrms.entities.dtos.auth.LoginDto;

public interface AuthBusinessRulesService {

	void isEmailRegistered(String email) throws Exception;
	
	void verifyPassword(LoginDto loginDto) throws Exception;
}
