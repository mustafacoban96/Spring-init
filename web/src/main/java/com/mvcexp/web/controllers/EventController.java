package com.mvcexp.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mvcexp.web.dto.EventDto;
import com.mvcexp.web.models.Event;
import com.mvcexp.web.service.EventService;

@Controller
public class EventController {
	
	
	private EventService eventService;
	
	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@GetMapping("/events/{clubId}/new")
	public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
		Event event = new Event();
		model.addAttribute("clubId", clubId);
		model.addAttribute("event",event);
		return "events-create";
	}
	
	@PostMapping("/events/{clubId}")
	public String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event") EventDto eventDto, Model model) {
		eventService.createEvent(clubId, eventDto);
		return "redirect:/clubs/" + clubId;
	}

}
