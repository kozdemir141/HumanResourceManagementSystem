package kodlamaio.hrms.entities.dtos.jobPosition;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPositionUpdateDto {

	@NotNull
	private int jobPositionId;

	@NotNull
	private String jobPositionName;
}
