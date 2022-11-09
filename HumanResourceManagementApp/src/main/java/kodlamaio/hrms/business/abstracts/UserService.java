package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.entities.Role;
import kodlamaio.hrms.core.entities.User;


public interface UserService {

	User saveUser(User user);

	Role saveRole(Role role);

	void addRoleToUser(String email, String roleName);

	User getUser(String email);

	//in the real world Application we probably would't hit that because this is try to load everything in database
	//in the real world we would usually return a page to a user(Pagination)
	List<User> getUsers();
}
