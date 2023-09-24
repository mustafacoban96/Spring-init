package com.mvcexp.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvcexp.web.dto.ClubDto;
import com.mvcexp.web.models.Club;
import com.mvcexp.web.service.ClubService;




@Controller
public class ClubController {
	
	private ClubService clubService;
	
	
	@Autowired
	public ClubController(ClubService clubService) {
		this.clubService = clubService;
	}
	
	@GetMapping("/clubs")
	public String listClubs(Model model) {
		List<ClubDto> clubs = clubService.findAllClubs();
		model.addAttribute("clubs", clubs);
		return "club-list";
		
	}
	
	@GetMapping("/clubs/new")
	public String createClubForm(Model model) {
		Club club = new Club();
		model.addAttribute("club", club);
		return "clubs-create";
	}
	
	@PostMapping("/clubs/new")
	public String saveClub(@ModelAttribute("club") Club club) {
		clubService.saveClub(club);
		return "redirect:/clubs";
	}
	
	@GetMapping("/clubs/{clubId}/edit")
	public String editClubForm(@PathVariable("clubId") long clubId,Model model) {
		ClubDto clubDto = clubService.findClubById(clubId);
		model.addAttribute("club", clubDto);
		return "clubs-edit";
	}
	
	@PostMapping("/clubs/{clubId}/edit")
	public String updateClub(@PathVariable("clubId") long clubId, @ModelAttribute("club") ClubDto clubDto) {
		clubDto.setId(clubId);
		clubService.updateClub(clubDto);
		return "redirect:/clubs";
	}
	

}
