package com.example.catalogue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.catalogue.domain.OrderDetails;
import com.example.catalogue.domain.Product;
import com.example.catalogue.service.EmailService;
import com.example.catalogue.service.OrderService;

@RestController
@RequestMapping(value="/checkout/")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private Environment env;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	@PostMapping("confirm/{email}")
	public ResponseEntity<?> confirmOrder(@PathVariable String email,@RequestBody OrderDetails orderDetails){
		ResponseEntity<?>response;
		try {
			orderService.confirmOrder(email,orderDetails);
			emailService.sendMail(email,"Order Placed" , "Order Details: " + orderDetails.toString());
			response = new ResponseEntity<String>(env.getProperty("order.success.message"),HttpStatus.OK);
		}
		catch(Exception ex) {
			logger.info("exception occured" + ex.getMessage());
			response = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
		}
		return response;
	}
	
	@GetMapping("/getAllOrders/{email}")
	public ResponseEntity<?>getAllOrdersForUser(@PathVariable String email){
		ResponseEntity<?>response;
		try {
			
			response = new ResponseEntity<List<Product>>(orderService.getOrdersByEmail(email),HttpStatus.OK);
		}
		catch(Exception ex) {
			logger.info("exception occured" + ex.getMessage());
			response = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
		}
		return response;
	}
}
