package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ProgramingLanguage;
import kodlamaio.hrms.entities.dtos.programingLanguage.ProgramingLanguageAddDto;
import kodlamaio.hrms.entities.dtos.programingLanguage.ProgramingLanguageUpdateDto;

public interface ProgramingLanguageService {

	DataResult<List<ProgramingLanguage>> getList();

	Result add(ProgramingLanguageAddDto programingLanguageAddDto);

	Result update(ProgramingLanguageUpdateDto programingLanguageUpdateDto);

	Result delete(int id);
}
