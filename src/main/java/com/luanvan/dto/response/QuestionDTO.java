package com.luanvan.dto.response;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class QuestionDTO {
	@Setter @Getter private Long id;
	@Setter @Getter private String topic;
	@Setter @Getter private Customer customer;
	@Setter @Getter private Date createdAt;
	@Setter @Getter private List<Reply> replies;
	@Setter @Getter private Product product;
	
	@Setter @Getter
	public static class Customer{
		private Long id;
		private String name;
	}
	
	@Setter @Getter
	public static class Reply{
		private Long id;
		private String content;
	}
	
	@Setter @Getter
	public static class Product{
		private Long id;
		private String name;
	}

	public String getReplyFirst() {
		if(getReplies().isEmpty()) return null;
		return getReplies().get(getReplies().size() - 1).getContent();
	}

}
