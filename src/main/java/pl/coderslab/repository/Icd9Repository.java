package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Icd9;

public interface Icd9Repository extends JpaRepository<Icd9, Long>{

	Icd9 findByCode9(String code9);

}
