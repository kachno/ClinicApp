package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Icd10;

public interface Icd10Repository extends JpaRepository<Icd10, Long>{

	Icd10 findByCode10(String code10);

}
