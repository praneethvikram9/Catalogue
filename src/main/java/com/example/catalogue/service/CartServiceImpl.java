package com.example.catalogue.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.catalogue.domain.CartDetails;
import com.example.catalogue.domain.Product;
import com.example.catalogue.repository.CartRepository;
import com.example.catalogue.repository.ProductRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private Environment env;

	@Override
	public List<Product> getCartItems(String email) throws Exception {
		CartDetails cartDetails = cartRepository.findById(email).get();
		List<Product> products = new ArrayList<>();
		for(int productId : cartDetails.getProducts()) {
			Product product = productRepository.findById(productId).get();
			products.add(product);
		}
		return products;
	}

	@Override
	public CartDetails updateCart(List<Integer>products,String email) throws Exception {
		CartDetails cartDetails = cartRepository.findById(email).get();
		cartDetails.setProducts(products);
		cartRepository.save(cartDetails);
		return cartDetails;
	}

	@Override
	public void addProductToCart(String email, Product product) throws Exception {
		if(!cartRepository.existsById(email)) {
			List<Integer>productId = new ArrayList<>();
			productId.add(product.getId());
			CartDetails cartItem = new CartDetails(email,productId);
			cartRepository.save(cartItem);
		}
	}

	@Override
	public void removeItem(int productId, String email) throws Exception {
		if(cartRepository.existsById(email)) {
			CartDetails cartDetails = cartRepository.findById(email).get();
			List<Integer> products = cartDetails.getProducts();
			if(products.contains(productId)) {
				products.remove(productId);
			}
			cartDetails.setProducts(products);
			cartRepository.save(cartDetails);	
		}
		else {
			throw new Exception(env.getProperty("cart.empty"));
		}
	}
	
	

}
