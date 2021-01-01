package com.example.catalogue.service;

import java.util.List;

import com.example.catalogue.domain.Product;

public interface ProductService {

	public List<Product> getAllProducts(String sortBy);
	
	public List<Product> sortProductsByDesc(String sortBy);

	public Product getProductById(int id) throws Exception;

	public void addProduct(Product product) throws Exception;

	public List<Product> findProductsByName(String productName) throws Exception;

}
