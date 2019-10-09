package com.luanvan.dto.request;

import java.util.List;

import com.luanvan.model.OrderGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGroupOrder {
	
	private OrderGroup orderGroup;
	private List<CreateOrderDTO> orders;
	
}
