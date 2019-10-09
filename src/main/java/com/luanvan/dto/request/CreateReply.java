package com.luanvan.dto.request;

import com.luanvan.model.Question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReply {
	private String content;
	private Question question;
}
