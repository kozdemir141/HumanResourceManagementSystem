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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "educations")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Education {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name = "name_of_educational_institution")
	private String nameOfEducationalInstitution;
	
	@NotNull
	@Column(name = "department")
	private String department;
	
	@NotNull
	@Column(name = "starting_date")
	private LocalDate startingDate;
	
	@Column(name = "graduation_date")
	private LocalDate graduationDate;
	
	@Column(name = "continues")
	private boolean continues;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "resume_id")
	private Resume resume;
}
