package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.businessRules.abstracts.EmployerBusinessRulesService;
import kodlamaio.hrms.business.mapping.profiles.abstracts.EmployerMappingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.employer.EmployerAddDto;
import kodlamaio.hrms.entities.dtos.employer.EmployerUpdateDto;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;
	private EmployerBusinessRulesService employerBusinessRulesService;
	private EmployerMappingService employerMappingService;

	@Autowired
	public EmployerManager(EmployerDao employerDao, EmployerBusinessRulesService employerBusinessRulesService,
			EmployerMappingService employerMappingService) {
		super();
		this.employerDao = employerDao;
		this.employerBusinessRulesService = employerBusinessRulesService;
		this.employerMappingService = employerMappingService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {

		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "Listed");
	}

	@Override
	public DataResult<Employer> getByEmail(String email) {

		return new SuccessDataResult<Employer>(this.employerDao.findByEmail(email), "Getted");
	}

	@Override
	public DataResult<Employer> getById(int id) {

		return new SuccessDataResult<Employer>(this.employerDao.getById(id));
	}

	@Override
	public Result confirm(int id) {
		Employer employer = this.employerDao.getById(id);
		employer.setIsActive(true);
		this.employerDao.save(employer);
		return new SuccessResult("Activeted");
	}

	@Override
	public Result add(EmployerAddDto employerAddDto) {
		try {
			// Business Rules
			employerBusinessRulesService.WebsiteMustBeSameAsEmail(employerAddDto.getEmail(),
					employerAddDto.getWebAdress());
			employerBusinessRulesService.EmailCanNotBeDublicatedWhenInserted(employerAddDto.getEmail());
			employerBusinessRulesService.PasswordsMustBeTheSame(employerAddDto.getPassword(),
					employerAddDto.getPasswordRepeat());

			// Mapping
			Employer employer = this.employerMappingService.employerAddDtoToEmployer(employerAddDto);
			this.employerDao.save(employer);
			return new SuccessResult("Succesfully Added");
		} catch (Exception e) {

			e.printStackTrace();
			return new ErrorResult("");
		}

	}

	@Override
	public Result update(EmployerUpdateDto employerUpdateDto) {
		try {
			// Business Rules
			employerBusinessRulesService.WebsiteMustBeSameAsEmail(employerUpdateDto.getEmail(),
					employerUpdateDto.getWebAdress());
			employerBusinessRulesService.emailCanNotBeDublicatedWhenUpdating(employerUpdateDto.getId(),
					employerUpdateDto.getEmail());

			// Mapping
			Employer employer = this.employerMappingService.employerUpdateDtoToEmployer(employerUpdateDto);
			this.employerDao.save(employer);
			return new SuccessResult("Employer Updated");
		} catch (Exception e) {
			e.printStackTrace();
			return new ErrorResult(e.getMessage());
		}

	}

	@Override
	public Result delete(int id) {
		this.employerDao.deleteById(id);
		return new SuccessResult("Employer Deleted");
	}
}
