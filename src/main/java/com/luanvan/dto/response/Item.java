package com.luanvan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	private String type;
	private String imageurl;
	private String muatienmat;
	private String muack;
	private String bantienmat;
	private String banck;
}
