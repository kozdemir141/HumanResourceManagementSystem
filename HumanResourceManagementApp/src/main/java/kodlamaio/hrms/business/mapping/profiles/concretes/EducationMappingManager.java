package kodlamaio.hrms.business.mapping.profiles.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.mapping.profiles.abstracts.EducationMappingService;
import kodlamaio.hrms.dataAccess.abstracts.EducationDao;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.entities.concretes.Education;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.dtos.education.EducationAddDto;
import kodlamaio.hrms.entities.dtos.education.EducationUpdateDto;
import lombok.Data;

@Service
@Data
public class EducationMappingManager implements EducationMappingService {

	private ResumeDao resumeDao;
	private EducationDao educationDao;

	@Autowired
	public EducationMappingManager(ResumeDao resumeDao, EducationDao educationDao) {
		super();
		this.resumeDao = resumeDao;
		this.educationDao = educationDao;
	}

	@Override
	public Education educationAddDtoToEducation(EducationAddDto educationAddDto) {

		Education education = new Education();
		Resume resume = this.resumeDao.getById(educationAddDto.getResumeId());
		education.setNameOfEducationalInstitution(educationAddDto.getNameOfEducationalInstitution());
		education.setDepartment(educationAddDto.getDepartment());
		education.setStartingDate(educationAddDto.getStartingDate());
		education.setGraduationDate(educationAddDto.getGraduationDate());
		education.setContinues(setContinues(educationAddDto.getGraduationDate()));
		education.setResume(resume);

		return education;
	}

	public boolean setContinues(LocalDate graduationDate) {
		if (graduationDate == null) {
			return true;
		}
		return false;
	}

	@Override
	public Education educationUpdateDtoToEducation(EducationUpdateDto educationUpdateDto) {

		Education oldEducation = this.educationDao.getById(educationUpdateDto.getId());

		Education education = new Education();
		education.setId(educationUpdateDto.getId());
		education.setNameOfEducationalInstitution(educationUpdateDto.getNameOfEducationalInstitution());
		education.setDepartment(educationUpdateDto.getDepartment());
		education.setStartingDate(educationUpdateDto.getStartingDate());
		education.setGraduationDate(educationUpdateDto.getGraduationDate());
		education.setContinues(setContinues(educationUpdateDto.getGraduationDate()));
		education.setResume(oldEducation.getResume());

		return education;
	}
}
