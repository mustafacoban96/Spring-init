package com.mvcexp.web.mapper;

import com.mvcexp.web.dto.EventDto;
import com.mvcexp.web.models.Event;

public class EventMapper {

	
	public static Event mapToEvent(EventDto eventDto) {
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
	
	public static EventDto mapToEventDto(Event event) {
		return EventDto.builder()
				.id(event.getId())
				.name(event.getName())
				.startTime(event.getStartTime())
				.photoUrl(event.getPhotoUrl())
				.endDateTime(event.getEndDateTime())
				.type(event.getType())
				.createOn(event.getCreateOn())
				.updatedOn(event.getUpdatedOn())
				.build();
	}
	
	
}
