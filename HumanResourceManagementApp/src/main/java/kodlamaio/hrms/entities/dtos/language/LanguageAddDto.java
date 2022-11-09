package kodlamaio.hrms.entities.dtos.language;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageAddDto {

	@NotNull
	private String language;
	
	@NotNull
	@Min(1)
	@Max(5)
	private int languageLevel;
	
	@NotNull
	private int resumeId;
	
}
