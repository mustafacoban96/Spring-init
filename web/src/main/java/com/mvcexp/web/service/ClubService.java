package com.mvcexp.web.service;

import java.util.List;

import com.mvcexp.web.dto.ClubDto;
import com.mvcexp.web.models.Club;

public interface ClubService {
	
	List<ClubDto> findAllClubs();
	Club saveClub(Club club);

}
