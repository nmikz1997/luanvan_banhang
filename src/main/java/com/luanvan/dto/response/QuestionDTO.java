package com.luanvan.dto.response;

import java.util.List;

import com.luanvan.model.Reply;

public class QuestionDTO {
	private Long id;
	private String topic;
	private Customer customer;
	private String reply;
	List<Reply> replies;
	
	public static class Customer{
		private Long id;
		private String name;
	}
}
