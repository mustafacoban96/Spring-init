package com.mvcexp.web.service.Impl;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mvcexp.web.dto.ClubDto;
import com.mvcexp.web.mapper.ClubMapper;
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
		return clubs.stream().map((club) -> ClubMapper.mapToClubDto(club)).collect(Collectors.toList());
	}
	 
	@Override
	public Club saveClub(ClubDto clubDto) {
		Club club = ClubMapper.mapToEntity(clubDto);
		return clubRepository.save(club);
	}

	@Override
	public ClubDto findClubById(long clubId) {
		Club club = clubRepository.findById(clubId).get();
		return ClubMapper.mapToClubDto(club);
	}

	@Override
	public void updateClub(ClubDto clubDto) {
		clubRepository.save(ClubMapper.mapToEntity(clubDto));
		
	}
	
	

	@Override
	public void delete(Long clubId) {
		clubRepository.deleteById(clubId);
		
	}

	@Override
	public List<ClubDto> searchClubs(String query) {
		List<Club> clubs = clubRepository.searchClubs(query);
		return clubs.stream().map(club -> ClubMapper.mapToClubDto(club)).collect(Collectors.toList());
	}

}
