package kodlamaio.hrms.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.core.entities.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {

	Role findByName(String name);
}
