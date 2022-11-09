package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.businessRules.abstracts.ProgramingLanguageBusinessRulesService;
import kodlamaio.hrms.dataAccess.abstracts.ProgramingLanguageDao;
import kodlamaio.hrms.entities.concretes.ProgramingLanguage;
import lombok.Data;

@Service
@Data
public class ProgramingLanguageBusinessRules implements ProgramingLanguageBusinessRulesService {

	private ProgramingLanguageDao programingLanguageDao;

	@Autowired
	public ProgramingLanguageBusinessRules(ProgramingLanguageDao programingLanguageDao) {
		super();
		this.programingLanguageDao = programingLanguageDao;
	}

	@Override
	public void programingLanguageCanNotBeDublicatedWhenInserted(String programingLanguage, int resumeId)
			throws Exception {

		if (this.programingLanguageDao.findByProgramingLanguageAndResume_Id(programingLanguage, resumeId) != null) {
			throw new Exception("Programing Language Cannot Be Dublicated When Inserted");
		}

	}

	@Override
	public void programingLanguageCanNotBeDublicatedWhenUpdating(int id, String programingLanguage) throws Exception {

		ProgramingLanguage oldProgramingLanguage = this.programingLanguageDao.getById(id);

		if (oldProgramingLanguage != this.programingLanguageDao.findByProgramingLanguageAndResume_Id(programingLanguage, oldProgramingLanguage.getResume().getId())) {

			if (this.programingLanguageDao.findByProgramingLanguageAndResume_Id(programingLanguage, oldProgramingLanguage.getResume().getId()) != null) {

				throw new Exception("Programing Language Cannot Be Dublicated When Updating");
			}
		}
	}
}
