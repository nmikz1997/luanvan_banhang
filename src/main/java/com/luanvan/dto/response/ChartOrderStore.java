package com.luanvan.dto.response;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class ChartOrderStore {
	
	@Setter private Date createdAt;
	@Setter @Getter private Integer total;
	
	public LocalDate getCreatedAt() {
		return Instant.ofEpochMilli(createdAt.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	
}
