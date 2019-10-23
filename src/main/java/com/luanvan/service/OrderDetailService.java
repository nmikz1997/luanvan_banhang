package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.response.ChartOrderInf;
import com.luanvan.model.OrderDetail;

public interface OrderDetailService {
	void save(OrderDetail orderDetail);

	List<OrderDetail> orderDetails(Long storeId);

	List<ChartOrderInf> chartOfYear(Long storeId, int year);

	List<ChartOrderInf> chartOfMonth(Long storeId, int year, int month);

	List<ChartOrderInf> tongDoanhThu(Long storeId);
}
