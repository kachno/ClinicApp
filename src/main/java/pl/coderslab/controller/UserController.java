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

import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("user", new User());

		return "user/userRegister";
	}

	@PostMapping("/form")
	public String formPost(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "user/userRegister";
		}
		if (this.userRepository.findByLogin(user.getLogin()) != null) {

		}
		this.userRepository.save(user);
		return "redirect:/user/all";
	}

	@GetMapping("/all")
	public String listJsp() {
		return "user/userList";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable long id, Model model) {
		User user = this.userRepository.findOne(id);
		model.addAttribute("user", user);
		return "user/userRegister";
	}

	@PostMapping("/{id}/edit")
	public String editPost(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "user/userRegister";
		}
		this.userRepository.save(user);
		;
		return "redirect:/user/all";
	}

	@GetMapping("/{id}/del")
	public String del(@PathVariable long id) {
		User user = this.userRepository.findOne(id);
		this.userRepository.delete(user);
		return "redirect:/user/all";
	}

	@ModelAttribute("users")
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}

}
