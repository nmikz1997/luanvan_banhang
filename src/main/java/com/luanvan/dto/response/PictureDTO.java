package com.luanvan.dto.response;

public class PictureDTO {
	private Long id;
	private String path;
	
	public PictureDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PictureDTO(Long id, String path) {
		super();
		this.id = id;
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
