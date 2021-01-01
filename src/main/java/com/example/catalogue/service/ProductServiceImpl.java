package com.example.catalogue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catalogue.domain.Product;
import com.example.catalogue.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products;
	}

	@Override
	public Product getProductById(int id) {
		Product product = productRepository.findById(id).get();
		return product;
	}

	@Override
	public void addProduct(Product product) throws Exception {
		productRepository.save(product);
		
	}
	

}
