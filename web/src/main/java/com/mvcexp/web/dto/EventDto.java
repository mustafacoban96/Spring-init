package com.mvcexp.web.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
	
	private Long id;
	private String name;
	private LocalDateTime starTime;
	private LocalDateTime endDateTime;
	private String type;
	private LocalDateTime createOn;
	private LocalDateTime updatedOn;

}
