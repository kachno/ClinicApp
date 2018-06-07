package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

	Patient findByPesel(String pesel);

}
