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

import com.example.catalogue.domain.Product;
import com.example.catalogue.service.CartService;

@RestController
@RequestMapping(value="/cart/")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private Environment env;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@GetMapping("getCartItems/{email}")
	public ResponseEntity<?> getCartItems(@PathVariable String email){
		ResponseEntity<?> response;
		
		try {
			response = new ResponseEntity<List<Product>>(cartService.getCartItems(email),HttpStatus.OK);
		}
		catch(Exception ex) {
			logger.error(ex.getMessage());
            response = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
		}
		return response;
	}
	
	
	@PostMapping("addItem/{email}")
	public ResponseEntity<?> addToCart(@PathVariable String email ,@RequestBody Product product){
		ResponseEntity<?> response;
		try {
			cartService.addProductToCart(email,product);
			response = new ResponseEntity<String>(env.getProperty("product.added.to.cart"),HttpStatus.OK);
		}
		catch(Exception ex) {
			logger.error(ex.getMessage());
            response = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
		}
		return response;
	}
	
	@PostMapping("removeItem/{email}")
	public ResponseEntity<?> removeFromCart(@PathVariable String email,@RequestBody int productId){
		ResponseEntity<?> response;
		try {
			cartService.removeItem(productId,email);
			response = new ResponseEntity<String>(env.getProperty("cart.update.success"),HttpStatus.OK);
		}
		catch(Exception ex) {
			logger.error(ex.getMessage());
            response = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
		}
		return response;
	}
	
	@PostMapping("emptyCart/{email}")
	public ResponseEntity<?> emptyCartForUser(@PathVariable String email){
		ResponseEntity<?> response;
		try {
			cartService.emptyCart(email);
			response = new ResponseEntity<String>(env.getProperty("cart.items.removed"),HttpStatus.OK);
		}
		catch(Exception ex) {
			logger.error(ex.getMessage());
            response = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
		}
		return response;
	}

	
}
