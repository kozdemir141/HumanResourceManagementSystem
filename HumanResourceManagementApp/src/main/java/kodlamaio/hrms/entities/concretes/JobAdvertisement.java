package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "jobadvertisements")
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisement {

	@Id
	@Column(name = "job_advertisement_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobAdvertisementId;

	@NotNull
	@Column(name = "description")
	@Size(min = 6)
	private String description;
	
	@Column(name = "min_salary")
	private double minSalary;
	
	@Column(name = "max_salary")
	private double maxSalary;
	
	@NotNull
	@Column(name = "number_of_positions")
	private int numberOfPositions;
	
	@NotNull
	@Column(name = "release_date")
	private LocalDate releaseDate;
	
	@NotNull
	@Column(name = "deadline")
	private LocalDate deadline;
	
	@NotNull
	@Column(name = "is_active")
	private Boolean isActive;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "job_position_id")
	private JobPosition jobPosition;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "employer_id")
	private Employer employer;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

}
