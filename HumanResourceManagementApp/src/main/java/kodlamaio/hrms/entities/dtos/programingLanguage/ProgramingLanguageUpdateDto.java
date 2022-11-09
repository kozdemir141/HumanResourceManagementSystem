package kodlamaio.hrms.entities.dtos.programingLanguage;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramingLanguageUpdateDto {

	@NotNull
	private int id;

	@NotNull
	private String programingLanguage;
}
