
package ca.sheridancollege.sin11368.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.sin11368.beans.Breed;
import ca.sheridancollege.sin11368.beans.Dogs;
import ca.sheridancollege.sin11368.repositories.DogRepository;

@Controller
public class HomeController {
	
	
	@Autowired
	private DogRepository dogRepo;
	
	@GetMapping("/")
	public String goRoot() {
		return "root.html";
	}
	
	@GetMapping("/addDog")
	public String AddDog(Model model,@ModelAttribute Dogs dogs) {
		model.addAttribute("dogs", new Dogs());
		model.addAttribute("breeds", dogRepo.getBreed());
		return "addDogs.html";
	}
	
	@PostMapping("/addDog")
	public String AddDogs(@ModelAttribute Dogs dogs) {
		dogRepo.addDog(dogs);
		return "redirect:/addDog";
	}
	
	@GetMapping("/viewDogs")
	public String ViewDogs(Model model) {
		model.addAttribute("dogs", dogRepo.getDog());		
		return "viewDogs.html";
	}
	
	@GetMapping("/edit/{E_num}")
	public String ModifyDogs(@PathVariable int E_num, Model model) {
		Dogs d = dogRepo.getDogByE_num(E_num);
		model.addAttribute("dogs", d);
		model.addAttribute("breeds", dogRepo.getBreed());
		return "ModifyDog.html";
	}
	
	@PostMapping("/editDog")
	public String ModifyDogs(@ModelAttribute Dogs dogs) {
		dogRepo.editDog(dogs);
		return "redirect:/viewDogs";
	}
	
	@GetMapping("/delete/{E_num}")
	public String DeleteDog(@ModelAttribute Dogs dog) {
		dogRepo.deleteDog(dog);
		return "redirect:/viewDogs";
	}
	
	@GetMapping("/addBreed")
	public String AddBreed(Model model,@ModelAttribute Breed breed) {
		model.addAttribute("breed", new Breed());
		return "addBreed.html";
	}
	
	@PostMapping("/addBreed")
	public String AddBreed(@ModelAttribute Breed breed) {
		dogRepo.addBreed(breed);
		return "redirect:/addBreed";
	}
	
	@GetMapping("/viewBreeds")
	public String ViewBreeds(Model model) {
		model.addAttribute("breeds", dogRepo.getBreed());		
		return "viewBreeds.html";
	}
	
	@GetMapping("/editbreed/{s_num}")
	public String ModifyBreed(@PathVariable int s_num, Model model) {
		Breed b = dogRepo.getBreedBys_num(s_num);
		model.addAttribute("breed", b);
		return "ModifyBreed.html";
	}
	
	@PostMapping("/editBreed")
	public String ModifyBreed(@ModelAttribute Breed breed) {
		dogRepo.editBreed(breed);
		return "redirect:/viewBreeds";
	}
	
	@GetMapping("/deletebreed/{s_num}")
	public String DeleteBreed(@ModelAttribute Breed breed) {
		dogRepo.deleteBreed(breed);
		return "redirect:/viewBreeds";
	}
	
	@GetMapping("/showList")
	public String ShowList(Model model) {
		model.addAttribute("List", dogRepo.getShowList());		
		return "showList.html";
	}
}
