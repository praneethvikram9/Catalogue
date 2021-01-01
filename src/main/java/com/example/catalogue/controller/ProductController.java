package com.example.catalogue.controller;

import java.util.List;

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
import com.example.catalogue.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private Environment env;

	@GetMapping("/allProducts/sortBy={sortBy}&{sortDir}")
	public ResponseEntity<?> getAllProducts(@PathVariable String sortBy,@PathVariable String sortDir) {
		ResponseEntity<?> response;
		try {
			if(sortDir.equals("asc")) {
				response = new ResponseEntity<List<Product>>(productService.getAllProducts(sortBy),HttpStatus.OK);
			}
			else {
				response = new ResponseEntity<List<Product>>(productService.sortProductsByDesc(sortBy),HttpStatus.OK);
			}
			
		}

		catch(Exception ex) {
			response = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
		}
		return response;
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<Product>(productService.getProductById(id),HttpStatus.OK);
		}
		catch(Exception ex) {
			response = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
		}

		return response;
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody Product product){
		ResponseEntity<?> response;
		try {
			productService.addProduct(product);
			response = new ResponseEntity<String>(env.getProperty("product.added.success"),HttpStatus.OK);
		}
		catch(Exception ex) {
			response = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
		}
		return response;
	}
	
	@PostMapping("/search/{name}")
	public ResponseEntity<?> searchProductByName(@PathVariable String productName){
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<List<Product>>(productService.findProductsByName(productName),HttpStatus.OK);
		}
		catch(Exception ex) {
			response = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
		}
		return response;
	}

}
