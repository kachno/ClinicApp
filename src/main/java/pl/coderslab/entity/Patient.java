package pl.coderslab.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.pl.PESEL;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "patients")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Column(unique = true)
	@PESEL
	private String pesel;
	@NotBlank
	private String address;
	@NotEmpty
	@Size(min = 9, max = 11)
	private String phone;
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Icd10> diseaseCode = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Icd9> procedureCode = new ArrayList<>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getId() {
		return id;
	}

	public List<Icd10> getDiseaseCode() {
		return diseaseCode;
	}

	public void setDiseaseCode(List<Icd10> diseaseCode) {
		this.diseaseCode = diseaseCode;
	}

	public List<Icd9> getProcedureCode() {
		return procedureCode;
	}

	public void setProcedureCode(List<Icd9> procedureCode) {
		this.procedureCode = procedureCode;
	}

}
