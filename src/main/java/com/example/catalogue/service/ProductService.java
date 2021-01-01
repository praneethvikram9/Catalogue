package com.example.catalogue.service;

import java.util.List;

import com.example.catalogue.domain.Product;

public interface ProductService {

	public List<Product> getAllProducts();

	public Product getProductById(int id);

	public void addProduct(Product product) throws Exception;

}
