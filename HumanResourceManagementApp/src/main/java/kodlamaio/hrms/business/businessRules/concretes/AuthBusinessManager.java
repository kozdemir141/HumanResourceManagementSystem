package kodlamaio.hrms.business.businessRules.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.businessRules.abstracts.AuthBusinessRulesService;
import kodlamaio.hrms.core.dataAccess.UserDao;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.security.hashing.HashingHelper;
import kodlamaio.hrms.entities.dtos.auth.LoginDto;
import lombok.Data;

@Data
@Service
public class AuthBusinessManager implements AuthBusinessRulesService {

	private UserDao userDao;

	@Autowired
	public AuthBusinessManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public void isEmailRegistered(String email) throws Exception {

		User user = this.userDao.findByEmail(email);

		if (user == null) {
			throw new Exception("This Mail Not Registered Before System");
		}
	}

	@Override
	public void verifyPassword(LoginDto loginDto) throws Exception {
		// Verify PasswordHash
		String storedPassword = this.userDao.findByEmail(loginDto.getEmail()).getPassword();
		
		Boolean loginResult = HashingHelper.validatePassword(loginDto.getPassword(), storedPassword);

		if (loginResult==false) {
			throw new Exception("Password Wrong");
		}
	}
}
