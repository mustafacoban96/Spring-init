package com.mvcexp.web.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClubDto {
	
	private Long id;
	private String title;
	private String photoUrl;
	private String content;
	private LocalDateTime createOn;
	private LocalDateTime upDateTimeOn;
}
