package pl.coderslab.controller;

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
	public String formPost(@Valid Patient patient, BindingResult result) {
		if (result.hasErrors()) {
			return "patient/patientRegister";
		}
		if (this.patientRepository.findByPesel(patient.getPesel()) != null) {

		}

		this.patientRepository.save(patient);
		return "redirect:/patient/all";
	}

	@GetMapping("/all")
	public String listJsp() {
		return "patient/patientList";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable long id, Model model) {
		Patient patient = this.patientRepository.findOne(id);
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

	@GetMapping("/{id}/procedure/{procedureId}/del")
	public String removeAssignedProcedure(@PathVariable long id, @PathVariable long procedureId, Model model) {
		Patient patient = this.patientRepository.findOne(id);
		List<Icd9> procedures = patient.getProcedureCode();

		patient.getProcedureCode().removeIf(procedure1 -> {
			if (procedure1.getId() == procedureId) {
				return true;
			} else {
				return false;
			}
		});

		this.patientRepository.save(patient);
		return "redirect:/patient/all";
	}

	@GetMapping("/procedure/{id}/assign")
	public String assignProcedureForm(@PathVariable long id, Model model) {
		model.addAttribute("patient", patientRepository.findOne(id));
		return "patient/assignProcedureForm";
	}

	@PostMapping("/procedure/{id}/assign")
	public String assignProcedure(@PathVariable long id, Model model, @RequestParam("procedureId") long procedureId) {

		Patient patient = patientRepository.findOne(id);
		Icd9 procedure = icd9Repo.findOne(procedureId);
		patient.getProcedureCode().add(procedure);

		patientRepository.save(patient);

		return "redirect:/patient/all";
	}

//	@GetMapping("/{id}/disease/{diseaseId}/del")
//	public String removeAssignedDisease(@PathVariable long id, @PathVariable long diseaseId, Model model) {
//		Patient patient = this.patientRepository.findOne(id);
//		List<Icd10> procedures = patient.getDiseaseCode();
//
//		patient.getDiseaseCode().removeIf(disease1 -> {
//			if (disease1.getId() == diseaseId) {
//				return true;
//			} else {
//				return false;
//			}
//		});
//
//		this.patientRepository.save(patient);
//		return "redirect:/patient/all";
//	}
//
//	@GetMapping("/disease/{id}/assign")
//	public String assignDiseaseForm(@PathVariable long id, Model model) {
//		model.addAttribute("patient", patientRepository.findOne(id));
//		return "patient/assignDiseaseForm";
//	}
//
//	@PostMapping("/disease/{id}/assign")
//	public String assignDisease(@PathVariable long id, Model model, @RequestParam("diseaseId") long diseaseId) {
//
//		Patient patient = patientRepository.findOne(id);
//		Icd10 disease = icd10Repo.findOne(diseaseId);
//		patient.getDiseaseCode().add(disease);
//
//		patientRepository.save(patient);
//
//		return "redirect:/patient/all";
//	}

	@ModelAttribute("procedures")
	public List<Icd9> getProcedures() {
		return this.icd9Repo.findAll();
	}

	@ModelAttribute("diseases")
	public List<Icd10> getDiseases() {
		return this.icd10Repo.findAll();
	}
}
