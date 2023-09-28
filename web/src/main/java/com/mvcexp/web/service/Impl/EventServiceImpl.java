package com.mvcexp.web.service.Impl;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.sym.Name;
import com.mvcexp.web.dto.EventDto;
import com.mvcexp.web.mapper.EventMapper;
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
		Event event = EventMapper.mapToEvent(eventDto);
		event.setClub(club);
		eventRepository.save(event);
	}

	@Override
	public List<EventDto> findAllEvents() {
		List<Event> events = eventRepository.findAll(); 
		return events.stream().map((event) -> EventMapper.mapToEventDto(event)).collect(Collectors.toList());
	}

	@Override
	public EventDto findByEventId(Long eventId) {
		Event event = eventRepository.findById(eventId).get();
		return EventMapper.mapToEventDto(event);
	}
	
	
	
	
}
