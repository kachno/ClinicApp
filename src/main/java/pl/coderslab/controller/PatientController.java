package pl.coderslab.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.coderslab.entity.Icd10;
import pl.coderslab.entity.Icd9;
import pl.coderslab.entity.Patient;
import pl.coderslab.repository.Icd10Repository;
import pl.coderslab.repository.Icd9Repository;
import pl.coderslab.repository.PatientRepository;

@Controller
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	Icd9Repository icd9Repo;
	@Autowired
	Icd10Repository icd10Repo;

	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("patient", new Patient());
		return "patient/patientRegister";
	}

	@PostMapping("/form")
	public String formPost(@Valid Patient patient, @RequestParam("code10") String code10, @RequestParam("code9") String code9, BindingResult result) {
		if (result.hasErrors()) {
			return "patient/patientRegister";
		}
		if (this.patientRepository.findByPesel(patient.getPesel()) != null) {

		}
		Icd10 list10 = this.icd10Repo.findByCode10(code10);
		Icd9 list9 = this.icd9Repo.findByCode9(code9);
		
		List<Icd9> icd9list = new ArrayList<>();
		icd9list.add(list9);
		
		List<Icd10> icd10list = new ArrayList<>();
		icd10list.add(list10);
		
		patient.setProcedureCode(icd9list);
		patient.setDiseaseCode(icd10list);
		
		this.patientRepository.save(patient);
		return "redirect:/patient/all";
	}

	@GetMapping("/all")
	public String listJsp() {
		return "patient/patientList";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable long id, Model model) {
		List<Icd10> disease = this.icd10Repo.findAll();
		List<Icd9> procedure = this.icd9Repo.findAll();
		Patient patient = this.patientRepository.findOne(id);
		patient.setDiseaseCode(disease);
		patient.setProcedureCode(procedure);
		model.addAttribute("patient", patient);
		return "patient/patientRegister";
	}

	@PostMapping("/{id}/edit")
	public String editPost(@ModelAttribute Patient patient, BindingResult result) {
		if (result.hasErrors()) {
			return "patient/patientRegister";
		}
		this.patientRepository.save(patient);
		return "redirect:/patient/all";
	}

	@GetMapping("/{id}/del")
	public String del(@PathVariable long id) {
		Patient patient = this.patientRepository.findOne(id);
		this.patientRepository.delete(patient);
		return "redirect:/patient/all";
	}

	@ModelAttribute("patients")
	public List<Patient> getPatients() {
		return this.patientRepository.findAll();
	}

}
