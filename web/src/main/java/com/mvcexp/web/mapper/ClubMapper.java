package com.mvcexp.web.mapper;

import java.util.stream.Collectors;
import com.mvcexp.web.dto.ClubDto;
import com.mvcexp.web.models.Club;

import lombok.Data;

@Data
public class ClubMapper {
	
	public static ClubDto mapToClubDto(Club club) {
		ClubDto clubDto = ClubDto.builder()
				.id(club.getId())
				.title(club.getTitle())
				.photoUrl(club.getPhotoUrl())
				.content(club.getContent()) 
				.createdOn(club.getCreatedOn())
				.updatedOn(club.getUpdatedOn())
				.events(club.getEvents().stream().map((event) -> EventMapper.mapToEventDto(event)).collect(Collectors.toList()))
				.build();
		return clubDto;
	}
	
	public static Club mapToEntity(ClubDto clubDto) {
		Club club = Club.builder()
				.title(clubDto.getTitle())
				.photoUrl(clubDto.getPhotoUrl())
				.content(clubDto.getContent())
				.createdOn(clubDto.getCreatedOn())
				.updatedOn(clubDto.getUpdatedOn())
				.build();
		return club;
	}
}
