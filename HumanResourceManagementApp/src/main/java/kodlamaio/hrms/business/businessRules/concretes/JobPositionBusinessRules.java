package kodlamaio.hrms.business.businessRules.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.businessRules.abstracts.JobPositionBusinessRulesService;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;
import lombok.Data;

@Service
@Data
public class JobPositionBusinessRules implements JobPositionBusinessRulesService {

	private JobPositionDao jobPositionDao;

	@Autowired
	public JobPositionBusinessRules(JobPositionDao jobPositionDao) {
		super();
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public void jobPositionNameCanNotBeDublicatedWhenInserted(String jobPositionName) throws Exception {
		
		if (jobPositionDao.getByJobPositionName(jobPositionName) == null) {

		} else {
			throw new Exception("Job Position Name Can Not Be Dublicated When Inserted");
		}
	}

	@Override
	public void jobPositionNameCanNotBeDublicatedWhenUpdating(int jobPositionId, String jobPositionName)
			throws Exception {
		
		JobPosition oldJobPosition = this.jobPositionDao.getById(jobPositionId);
		
		List<JobPosition> jobPositions = this.jobPositionDao.findByJobPositionNameIsNot(oldJobPosition.getJobPositionName());
		
		for (JobPosition jobPosition : jobPositions) {
			if (jobPosition.getJobPositionName().equals(jobPositionName)) {
				throw new Exception("Job Position Name Already Exists");
			}
		}
		
	}
}
