package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ProgramingLanguageService;
import kodlamaio.hrms.business.businessRules.abstracts.ProgramingLanguageBusinessRulesService;
import kodlamaio.hrms.business.mapping.profiles.abstracts.ProgramingLanguageMappingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ProgramingLanguageDao;
import kodlamaio.hrms.entities.concretes.ProgramingLanguage;
import kodlamaio.hrms.entities.dtos.programingLanguage.ProgramingLanguageAddDto;
import kodlamaio.hrms.entities.dtos.programingLanguage.ProgramingLanguageUpdateDto;

@Service
public class ProgramingLanguageManager implements ProgramingLanguageService {

	private ProgramingLanguageDao programingLanguageDao;
	private ProgramingLanguageMappingService programingLanguageMappingService;
	private ProgramingLanguageBusinessRulesService programingLanguageBusinessRulesService;

	@Autowired
	public ProgramingLanguageManager(ProgramingLanguageDao programingLanguageDao,
			ProgramingLanguageMappingService programingLanguageMappingService,
			ProgramingLanguageBusinessRulesService programingLanguageBusinessRulesService) {
		super();
		this.programingLanguageDao = programingLanguageDao;
		this.programingLanguageMappingService = programingLanguageMappingService;
		this.programingLanguageBusinessRulesService = programingLanguageBusinessRulesService;
	}

	@Override
	public DataResult<List<ProgramingLanguage>> getList() {
		return new SuccessDataResult<List<ProgramingLanguage>>(this.programingLanguageDao.findAll());
	}

	@Override
	public Result add(ProgramingLanguageAddDto programingLanguageAddDto) {
		try {
			// Business Rules
			this.programingLanguageBusinessRulesService.programingLanguageCanNotBeDublicatedWhenInserted(
					programingLanguageAddDto.getProgramingLanguage(), programingLanguageAddDto.getResumeId());

			// Mapping
			ProgramingLanguage programingLanguage = this.programingLanguageMappingService
					.programingLanguageAddDtoToProgramingLanguage(programingLanguageAddDto);
			this.programingLanguageDao.save(programingLanguage);
			return new SuccessResult("Added");

		} catch (Exception e) {

			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}
	}

	@Override
	public Result update(ProgramingLanguageUpdateDto programingLanguageUpdateDto) {
		try {
			// Business Rules
			this.programingLanguageBusinessRulesService.programingLanguageCanNotBeDublicatedWhenUpdating(
					programingLanguageUpdateDto.getId(), programingLanguageUpdateDto.getProgramingLanguage());

			// Mapping
			ProgramingLanguage programingLanguage = this.programingLanguageMappingService
					.programingLanguageUpdateDtoToProgramingLanguage(programingLanguageUpdateDto);

			this.programingLanguageDao.save(programingLanguage);
			return new SuccessResult("Updated");

		} catch (Exception e) {

			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}
	}

	@Override
	public Result delete(int id) {
		this.programingLanguageDao.deleteById(id);
		return new SuccessResult("Deleted");
	}

}
