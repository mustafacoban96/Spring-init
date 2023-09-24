package com.mvcexp.web.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvcexp.web.dto.ClubDto;
import com.mvcexp.web.models.Club;
import com.mvcexp.web.repository.ClubRepository;
import com.mvcexp.web.service.ClubService;


@Service
public class ClubServiceImpl implements ClubService{
	
	private ClubRepository clubRepository;
	
	@Autowired
	public ClubServiceImpl(ClubRepository clubRepository) {
		this.clubRepository = clubRepository;
	}

	@Override
	public List<ClubDto> findAllClubs() {
		List<Club> clubs = clubRepository.findAll();
		return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
	}
	 
	@Override
	public Club saveClub(Club club) {
		
		return clubRepository.save(club);
	}

	@Override
	public ClubDto findClubById(long clubId) {
		Club club = clubRepository.findById(clubId).get();
		return mapToClubDto(club);
	}

	@Override
	public void updateClub(ClubDto clubDto) {
		clubRepository.save(mapToEntity(clubDto));
		
	}
	
	private ClubDto mapToClubDto(Club club) {
		ClubDto clubDto = ClubDto.builder()
				.id(club.getId())
				.title(club.getTitle())
				.photoUrl(club.getPhotoUrl())
				.content(club.getContent())
				.createdOn(club.getCreatedOn())
				.updatedOn(club.getUpdatedOn())
				.build();
		return clubDto;
	}
	
	private Club mapToEntity(ClubDto clubDto) {
		Club club = Club.builder()
				.id(clubDto.getId())
				.title(clubDto.getTitle())
				.photoUrl(clubDto.getPhotoUrl())
				.content(clubDto.getContent())
				.createdOn(clubDto.getCreatedOn())
				.updatedOn(clubDto.getUpdatedOn())
				.build();
		return club;
	}

}
