package com.luanvan.dto.request;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.luanvan.model.AttributeValue;
import com.luanvan.model.Category;
import com.luanvan.model.Material;
import com.luanvan.model.Origin;
import com.luanvan.model.Picture;
import com.luanvan.model.Price;
import com.luanvan.model.Producer;
import com.luanvan.model.Store;

public class CreateProductDTO {
	private Long id;
	
	private String name;
	
	private String derciption;
	
	private int status;
	
	private int price;
	
	private int avgStar;
	
	private String avatar;
	
	private Long quantity;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private Category category;
	
	private Material material;
	
	private Origin origin;
	
	private Store store;
	
	private Producer producer;
	
	private List<Price> prices;

    private Set<AttributeValue> attributeValues;
}
