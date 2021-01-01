package com.example.catalogue.service;

import java.util.List;

import com.example.catalogue.domain.CartDetails;
import com.example.catalogue.domain.Product;

public interface CartService {

	public List<Product> getCartItems(String email) throws Exception;

	public CartDetails updateCart(List<Integer>product,String email) throws Exception;

	public void addProductToCart(String email, Product product) throws Exception;

	public void removeItem(int productId, String email) throws Exception;

}
