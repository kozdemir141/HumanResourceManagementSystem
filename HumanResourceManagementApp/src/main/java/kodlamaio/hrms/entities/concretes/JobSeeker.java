package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import kodlamaio.hrms.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "jobseekers")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@EqualsAndHashCode(callSuper = true)
public class JobSeeker extends User {
	
	@NotNull
	@NotBlank
	@Column(name = "identity_number")
	private String identityNumber;

	@NotNull
	@NotBlank
	@Column(name = "first_name")
	@Size(min = 2,max = 30)
	private String firstName;

	@NotNull
	@NotBlank
	@Column(name = "last_name")
	@Size(min = 2,max = 30)
	private String lastName;

	@NotNull
	@Column(name = "birth_date")
	private LocalDate birthDate;
	
	@Column(name = "is_active")
	private Boolean isActive;
}
