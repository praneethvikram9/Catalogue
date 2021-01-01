package com.example.catalogue.service;

import java.util.List;

import com.example.catalogue.domain.OrderDetails;
import com.example.catalogue.domain.Product;

public interface OrderService {

	public void confirmOrder(String email, OrderDetails orderDetails);
	
	public List<Product> getOrdersByEmail(String email);

}
