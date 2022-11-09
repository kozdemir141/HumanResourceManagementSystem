package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer> {

	JobSeeker findByEmail(String email);
	
	JobSeeker findByIdentityNumber(String identityNumber);
	
	List<JobSeeker> findByIdentityNumberIsNot(String identityNumber);
}
