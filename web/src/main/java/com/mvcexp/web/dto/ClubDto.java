package com.mvcexp.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClubDto {
	 
	private Long id;
	@NotEmpty(message = "Club title should not be empty")
	private String title;
	@NotEmpty(message="Photo link should not be empty")
	private String photoUrl;
	@NotEmpty(message = "Content should be not empty")
	private String content;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	private List<EventDto> events;
}
