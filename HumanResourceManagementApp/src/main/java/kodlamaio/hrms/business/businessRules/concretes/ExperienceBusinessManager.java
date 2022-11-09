package kodlamaio.hrms.business.businessRules.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.businessRules.abstracts.ExperienceBusinessRulesService;
import kodlamaio.hrms.dataAccess.abstracts.ExperienceDao;
import kodlamaio.hrms.entities.concretes.Experience;
import lombok.Data;

@Service
@Data
public class ExperienceBusinessManager implements ExperienceBusinessRulesService {
	
	private ExperienceDao experienceDao;
	
	@Autowired
	public ExperienceBusinessManager(ExperienceDao experienceDao) {
		super();
		this.experienceDao = experienceDao;
	}
	
	@Override
	public void terminationDateCanNotBeforeStartingDate(LocalDate startingDate, LocalDate terminationDate)
			throws Exception {
		
		if (terminationDate != null) {
			Boolean result = startingDate.isBefore(terminationDate);

			if (result == false) {
				throw new Exception("Termination Date Cannot Be Earlier Than The Start Date.");
			}
		}
		
	}

	@Override
	public void alreadyHasAContinuingExperience(LocalDate terminationDate, int resumeId) throws Exception {
		
		List<Experience> experiences = this.experienceDao.getByResume_Id(resumeId);

		if (terminationDate == null) {
			for (Experience experience : experiences) {
				if (experience.getTerminationDate() == null) {
					throw new Exception("You have another experience you are currently pursuing.");
				}
			}
		}
		
	}

	@Override
	public void updatedExperienceAlreadyHasAContinuingExperience(int id, LocalDate terminationDate) throws Exception {
		
		Experience oldExperience = this.experienceDao.getById(id);
		
		List<Experience> experiences = this.experienceDao.getByResume_Id(oldExperience.getResume().getId());
		
		if (terminationDate == null) {
			for (Experience experience : experiences) {
				if (experience.getTerminationDate() == null) {
					throw new Exception("You have another experience you are currently pursuing.");
				}
			}
		}
	}
}
