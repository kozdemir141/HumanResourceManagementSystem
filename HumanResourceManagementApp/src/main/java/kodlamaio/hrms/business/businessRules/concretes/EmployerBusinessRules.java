package kodlamaio.hrms.business.businessRules.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.businessRules.abstracts.EmployerBusinessRulesService;
import kodlamaio.hrms.core.dataAccess.UserDao;
import kodlamaio.hrms.core.entities.User;
import lombok.Data;

@Service
@Data
public class EmployerBusinessRules implements EmployerBusinessRulesService {

	private UserDao userDao;

	@Autowired
	public EmployerBusinessRules(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public void WebsiteMustBeSameAsEmail(String email, String webAdress) throws Exception {
		if (email.contains(webAdress)) {

		} else {
			throw new Exception();
		}
	}

	@Override
	public void EmailCanNotBeDublicatedWhenInserted(String email) throws Exception {

		if (userDao.findByEmail(email) == null) {

		} else {
			throw new Exception();
		}
	}

	@Override
	public void emailCanNotBeDublicatedWhenUpdating(int id, String email) throws Exception {

		User oldUser = this.userDao.getById(id);

		List<User> users = this.userDao.findByEmailIsNot(oldUser.getEmail());

		for (User user : users) {
			if (user.getEmail().equals(email)) {
				throw new Exception("E Mail Already Exists");
			}
		}
	}

	@Override
	public void PasswordsMustBeTheSame(String password, String passwordRepeat) throws Exception {

		if (password.equals(passwordRepeat)) {

		} else {
			throw new Exception();
		}

	}
}
