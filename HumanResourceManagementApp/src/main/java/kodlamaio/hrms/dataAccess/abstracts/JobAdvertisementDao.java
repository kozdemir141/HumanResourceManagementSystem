package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementListDto;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {

	List<JobAdvertisement> findByIsActiveTrue();

	List<JobAdvertisement> findByEmployer_Id(int employerId);

	JobAdvertisement findByEmployer_IdAndJobPosition_JobPositionId(int employerId, int jobPositionId);

	@Query("Select new kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementListDto"
			+ "(p.jobAdvertisementId, p.numberOfPositions, p.releaseDate, p.deadline, e.companyName, j.jobPositionName)"
			+ "From Employer e Inner Join e.jobAdvertisements p Inner Join p.jobPosition j")
	List<JobAdvertisementListDto> getJobAdvertisementListDto();

	
	@Query("Select new kodlamaio.hrms.entities.dtos.jobAdvertisement.JobAdvertisementListDto"
			+ "(p.jobAdvertisementId, p.numberOfPositions, p.releaseDate, p.deadline, e.companyName, j.jobPositionName)"
			+ "From JobAdvertisement p Inner Join p.employer e Inner Join e.jobAdvertisements pj Inner Join pj.jobPosition j")
	List<JobAdvertisementListDto> getJobAdvertisementListDtoByReleaseDate(Sort sort);
	
}