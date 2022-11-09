package kodlamaio.hrms.entities.dtos.resume;

import java.time.LocalDate;
import java.util.List;

import kodlamaio.hrms.entities.concretes.Education;
import kodlamaio.hrms.entities.concretes.Experience;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.Language;
import kodlamaio.hrms.entities.concretes.ProgramingLanguage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeWithAllRelatedEntitiesDto {

	private int id;
	
	private LocalDate creationDate;
	
	private String githubLink;
	
	private String linkedinlink;
	
	private String coverLetter;
	
	private JobSeeker jobSeeker;
	
	private List<Education> educations;
	
	private List<Experience> experiences;
	
	private List<Language> languages;
	
	private List<ProgramingLanguage> programingLanguages;
}
