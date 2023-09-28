package com.mvcexp.web.service;



import java.util.List;

import com.mvcexp.web.dto.EventDto;


public interface EventService {
	void createEvent(Long clubId,EventDto eventDto);
	List<EventDto> findAllEvents();
	EventDto findByEventId(Long eventId);
	
}
