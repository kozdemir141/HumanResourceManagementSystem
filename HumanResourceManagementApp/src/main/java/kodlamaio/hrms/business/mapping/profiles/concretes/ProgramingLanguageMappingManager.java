package kodlamaio.hrms.business.mapping.profiles.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.mapping.profiles.abstracts.ProgramingLanguageMappingService;
import kodlamaio.hrms.dataAccess.abstracts.ProgramingLanguageDao;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.entities.concretes.ProgramingLanguage;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.dtos.programingLanguage.ProgramingLanguageAddDto;
import kodlamaio.hrms.entities.dtos.programingLanguage.ProgramingLanguageUpdateDto;
import lombok.Data;

@Service
@Data
public class ProgramingLanguageMappingManager implements ProgramingLanguageMappingService {

	private ProgramingLanguageDao programingLanguageDao;
	private ResumeDao resumeDao;

	@Autowired
	public ProgramingLanguageMappingManager(ResumeDao resumeDao, ProgramingLanguageDao programingLanguageDao) {
		super();
		this.resumeDao = resumeDao;
		this.programingLanguageDao = programingLanguageDao;
	}

	@Override
	public ProgramingLanguage programingLanguageAddDtoToProgramingLanguage(
			ProgramingLanguageAddDto programingLanguageAddDto) {

		ProgramingLanguage programingLanguage = new ProgramingLanguage();
		Resume resume = this.resumeDao.getById(programingLanguageAddDto.getResumeId());
		programingLanguage.setProgramingLanguage(programingLanguageAddDto.getProgramingLanguage());
		programingLanguage.setResume(resume);

		return programingLanguage;
	}

	@Override
	public ProgramingLanguage programingLanguageUpdateDtoToProgramingLanguage(
			ProgramingLanguageUpdateDto programingLanguageUpdateDto) {

		ProgramingLanguage oldProgramingLanguage = this.programingLanguageDao
				.getById(programingLanguageUpdateDto.getId());
		ProgramingLanguage programingLanguage = new ProgramingLanguage();
		programingLanguage.setId(programingLanguageUpdateDto.getId());
		programingLanguage.setProgramingLanguage(programingLanguageUpdateDto.getProgramingLanguage());
		programingLanguage.setResume(oldProgramingLanguage.getResume());
		
		return programingLanguage;
	}

}
