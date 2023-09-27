package com.mvcexp.web.service.Impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.sym.Name;
import com.mvcexp.web.dto.EventDto;
import com.mvcexp.web.models.Club;
import com.mvcexp.web.models.Event;
import com.mvcexp.web.repository.ClubRepository;
import com.mvcexp.web.repository.EventRepository;
import com.mvcexp.web.service.EventService;


@Service
public class EventServiceImpl implements EventService{
	
	private EventRepository eventRepository;
	private ClubRepository clubRepository;
	
	// inject
	@Autowired
	public EventServiceImpl(EventRepository eventRepository,ClubRepository clubRepository) {
		this.eventRepository = eventRepository;
		this.clubRepository = clubRepository;
	}

	@Override
	public void createEvent(Long clubId, EventDto eventDto) {
		Club club = clubRepository.findById(clubId).get();
		Event event = mapToEvent(eventDto);
		event.setClub(club);
		eventRepository.save(event);
	}
	
	
	private Event mapToEvent(EventDto eventDto) {
		return Event.builder()
				.id(eventDto.getId())
				.name(eventDto.getName())
				.startTime(eventDto.getStartTime())
				.photoUrl(eventDto.getPhotoUrl())
				.endDateTime(eventDto.getEndDateTime())
				.type(eventDto.getType())
				.createOn(eventDto.getCreateOn())
				.updatedOn(eventDto.getUpdatedOn())
				.build();
	}
	
}
