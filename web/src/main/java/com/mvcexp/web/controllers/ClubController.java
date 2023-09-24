package com.mvcexp.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvcexp.web.dto.ClubDto;
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

}
