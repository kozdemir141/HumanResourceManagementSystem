package kodlamaio.hrms.entities.dtos.resume;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeUpdateDto {

	@NotNull
	private int id;
	
	private String githubLink;
	
	private String linkedinlink;
	
	private String coverLetter;
}
