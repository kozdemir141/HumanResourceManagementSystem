package kodlamaio.hrms.business.businessRules.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.businessRules.abstracts.JobSeekerBusineessRulesService;
import kodlamaio.hrms.core.dataAccess.UserDao;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import lombok.Data;

@Service
@Data
public class JobSeekerBusinessRules implements JobSeekerBusineessRulesService {

	private JobSeekerDao jobSeekerDao;
	private UserDao userDao;

	@Autowired
	public JobSeekerBusinessRules(JobSeekerDao jobSeekerDao, UserDao userDao) {
		super();
		this.jobSeekerDao = jobSeekerDao;
		this.userDao = userDao;
	}

	public void IdentityNumberCheck(String identityNumber) throws Exception {

		if (identityNumber.length() == 11 && IsDigit(identityNumber)) {
		} else {
			throw new Exception();
		}
	}

	@Override
	public void EmailCanNotBeDublicatedWhenInserted(String email) throws Exception {

		if (userDao.findByEmail(email) == null) {

		} else {
			throw new Exception("Email Cannot Be Dublicated When Inserted");
		}

	}

	@Override
	public void EmailCanNotBeDublicatedWhenUpdating(String email) throws Exception {

		List<User> users = this.userDao.findByEmailIsNot(email);

		for (User user : users) {
			if (user.getEmail().equals(email)) {
				throw new Exception("Email Cannot Be Dublicated When Updating");
			}
		}

	}

	@Override
	public void IdentityNumberCanNotBeDublicatedWhenInserted(String identityNumber) throws Exception {

		if (jobSeekerDao.findByIdentityNumber(identityNumber) == null) {

		} else {
			throw new Exception();
		}

	}

	@Override
	public void IdentityNumberCanNotBeDublicatedWhenUpdating(String identityNumber) throws Exception {

		List<JobSeeker> jobSeekers = this.jobSeekerDao.findByIdentityNumberIsNot(identityNumber);

		for (JobSeeker jobSeeker : jobSeekers) {
			if (jobSeeker.getIdentityNumber().equals(identityNumber)) {
				throw new Exception("IdentityNumber Cannot Be Dublicated When Updating");
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

	private Boolean IsDigit(String identityNumber) {

		Boolean tcNoCheck = true;

		for (int i = 0; i < identityNumber.length(); i++) {
			char character = identityNumber.charAt(i);

			if (Character.isDigit(character)) {

			} else {
				tcNoCheck = false;
			}
		}
		return tcNoCheck;
	}
}
