package com.example.catalogue.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catalogue.domain.CartDetails;
import com.example.catalogue.domain.OrderDetails;
import com.example.catalogue.domain.Product;
import com.example.catalogue.repository.CartRepository;
import com.example.catalogue.repository.OrderRepository;
import com.example.catalogue.repository.ProductRepository;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public void confirmOrder(String email, OrderDetails orderDetails) {
		orderDetails.setUserEmail(email);
		Map<Integer,Product> orderMap = new HashMap<>();
		Product product = new Product();
		List<Integer>productId = new ArrayList<>(orderDetails.getProductMap().keySet());
		for(Integer prodId: productId) {
			product = productRepository.findById(prodId).get();
			product.setQuantity(orderDetails.getProductMap().get(prodId));
			Map<Integer,Integer>cartMap = cartRepository.findById(email).get().getCartList();
			int quantity = cartMap.get(prodId);
			cartMap.put(prodId, quantity - product.getQuantity());
			cartRepository.save(new CartDetails(email,cartMap));
			orderMap.put(prodId, product);
		}
		orderDetails.setOrderMap(orderMap);
		orderRepository.save(orderDetails);
	}

	@Override
	public List<Product> getOrdersByEmail(String email) {
		List<OrderDetails> orders = orderRepository.findAll();
		List<Product> products = new ArrayList<>();	
		for(OrderDetails order: orders) {
			if(order.getUserEmail().equals(email)) {
				products.addAll(order.getOrderMap().values());
			}
		}
		return products;
	}
	
	

}
