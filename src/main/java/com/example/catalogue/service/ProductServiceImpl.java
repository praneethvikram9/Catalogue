package com.example.catalogue.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.catalogue.domain.Product;
import com.example.catalogue.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private Environment env;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Product> getAllProducts(String sortBy) {
		Sort sort = Sort.by(Sort.Order.asc(sortBy));
		List<Product> products = productRepository.findAll(sort);
		return products;
	}
	
	@Override
	public List<Product> sortProductsByDesc(String sortBy){
		Sort sort = Sort.by(Sort.Order.desc(sortBy));
		List<Product> products = productRepository.findAll(sort);
		return products;
	}

	@Override
	public Product getProductById(int id) throws Exception{
		if(!productRepository.existsById(id)) {
			throw new Exception(env.getProperty("no.product.found"));
		}
		Product product = productRepository.findById(id).get();
		return product;
	}

	@Override
	public void addProduct(Product product) throws Exception {
		if(productRepository.existsById(product.getId())) {
			throw new Exception(env.getProperty("product.already.exists.error"));
		}
		productRepository.save(product);
	}

	@Override
	public List<Product> findProductsByName(String productName) throws Exception {
		logger.info("inside the product by name");
		List<Product> products = productRepository.findAll();
		List<Product> result = new ArrayList<>();
		for(Product prod:products) {
			if(prod.getName().contentEquals(productName)) {
				result.add(prod);
			}
		}
		if(result.isEmpty()) {
			throw new Exception(env.getProperty("no.product.found"));
		}
		return result;
	}
	
	
	

}
