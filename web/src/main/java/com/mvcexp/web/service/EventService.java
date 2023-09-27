package com.mvcexp.web.service;



import com.mvcexp.web.dto.EventDto;


public interface EventService {
	void createEvent(Long clubId,EventDto eventDto);
}
