package kodlamaio.hrms.business.businessRules.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.businessRules.abstracts.JobAdvertisementBusinessRulesService;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import lombok.Data;

@Service
@Data
public class JobAdvertisementBusinessRules implements JobAdvertisementBusinessRulesService {

	private JobAdvertisementDao jobAdvertisementDao;

	@Autowired
	public JobAdvertisementBusinessRules(JobAdvertisementDao jobAdvertisementDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
	}

	@Override
	public void JobPositionCanNotBeDublicatedWhenInserted(int employerId, int jobPositionId) throws Exception {

		if (this.jobAdvertisementDao.findByEmployer_IdAndJobPosition_JobPositionId(employerId, jobPositionId) != null) {
			throw new Exception("Job Position Cannot Be Dublicated When Inserted");
		}

	}

	@Override
	public void JobPositionCanNotBeDublicatedWhenUpdating(int jobAdvertisementId, int jobPositionId) throws Exception {
		JobAdvertisement oldJobAdvertisement = this.jobAdvertisementDao.getById(jobAdvertisementId);

		if (oldJobAdvertisement != this.jobAdvertisementDao.findByEmployer_IdAndJobPosition_JobPositionId(
				oldJobAdvertisement.getEmployer().getId(), jobPositionId)) {

			if (this.jobAdvertisementDao.findByEmployer_IdAndJobPosition_JobPositionId(
					oldJobAdvertisement.getEmployer().getId(), jobPositionId) != null) {
				
				throw new Exception("Job Position Cannot Be Dublicated When Updating");
			}
		}

	}

	@Override
	public void deadlineCanNotBeforeReleaseDate(LocalDate releaseDate, LocalDate deadline) throws Exception {

		if (deadline != null) {
			Boolean result = releaseDate.isBefore(deadline);

			if (result == false) {
				throw new Exception("Deadline Cannot Be Earlier Than The Start Date.");
			}
		}
	}

	@Override
	public void salaryStandarts(double minSalary, double maxSalary) throws Exception {

		if (minSalary > maxSalary) {
			throw new Exception("Minumum Income Cannot More Than Maximum Income");
		} else if (minSalary == maxSalary) {
			throw new Exception("Minumum Income Cannot Equal to Maximum Income");
		} else if (maxSalary < minSalary) {
			throw new Exception("Maximum Income Cannot Equal to Minumum Income");
		}

	}
}
