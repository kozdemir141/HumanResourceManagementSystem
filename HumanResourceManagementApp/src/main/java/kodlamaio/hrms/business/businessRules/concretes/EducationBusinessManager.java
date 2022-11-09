package kodlamaio.hrms.business.businessRules.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.businessRules.abstracts.EducationBusinessRulesService;
import kodlamaio.hrms.dataAccess.abstracts.EducationDao;
import kodlamaio.hrms.entities.concretes.Education;
import lombok.Data;

@Service
@Data
public class EducationBusinessManager implements EducationBusinessRulesService {

	private EducationDao educationDao;

	@Autowired
	public EducationBusinessManager(EducationDao educationDao) {
		super();
		this.educationDao = educationDao;
	}

	@Override
	public void graduationDateCanNotBeforeStartingDate(LocalDate startingDate, LocalDate graduationDate)
			throws Exception {

		if (graduationDate != null) {
			Boolean result = startingDate.isBefore(graduationDate);

			if (result == false) {
				throw new Exception("Graduation Date Cannot Be Earlier Than The Start Date.");
			}
		}
	}

	@Override
	public void alreadyHasAContinuingEducation(LocalDate graduationDate, int resumeId) throws Exception {

		List<Education> educations = this.educationDao.getByResume_Id(resumeId);

		if (graduationDate == null) {
			for (Education education : educations) {
				if (education.getGraduationDate() == null) {
					throw new Exception("You have another education you are currently pursuing.");
				}
			}
		}
	}

	@Override
	public void updatedEducationAlreadyHasAContinuingEducation(int id, LocalDate graduationDate) throws Exception {

		Education oldEducation = this.educationDao.getById(id);

		List<Education> educations = this.educationDao.getByResume_Id(oldEducation.getResume().getId());

		if (graduationDate == null) {
			for (Education education : educations) {
				if (education.getGraduationDate() == null) {
					throw new Exception("You have another education you are currently pursuing.");
				}
			}
		}
	}
}
