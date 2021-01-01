package com.example.catalogue.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Product> getCartItems(String email) throws Exception {
		CartDetails cartDetails = cartRepository.findById(email).get();
		Map<Integer, Integer> cartList = cartDetails.getCartList(); // product id and the count ordered.
		List<Product> products = new ArrayList<>();
		List<Integer> productId = new ArrayList<>(cartList.keySet());
		for (Integer id : productId) {
			Product product = productRepository.findById(id).get();
			product.setQuantity(cartList.get(id));
			if (product.getQuantity() != 0) {
				products.add(product);
			}
		}
		return products;
	}


	@Override
	public void addProductToCart(String email, Product product) throws Exception {
		logger.info("inside the add to cart");
		if (cartRepository.existsById(email)) {
			CartDetails cartDetails = cartRepository.findById(email).get();
			Map<Integer, Integer> cartList = cartDetails.getCartList();
			int quantity = 0;
			if (cartList.containsKey(product.getId())) {
				quantity = cartList.get(product.getId());
			}
			cartList.put(product.getId(), quantity + 1);
			cartDetails.setCartList(cartList);
			cartRepository.save(cartDetails);

		} else {
			int quantity = 1;
			Map<Integer, Integer> cartList = new HashMap<>();
			cartList.put(product.getId(), quantity);
			CartDetails cartDetails = new CartDetails(email, cartList);
			cartRepository.save(cartDetails);
		}
	}

	@Override
	public void removeItem(int productId, String email) throws Exception {
		if (cartRepository.existsById(email)) {
			CartDetails cartDetails = cartRepository.findById(email).get();
			Map<Integer, Integer> cartList = cartDetails.getCartList();
			if (cartList.containsKey(productId)) {
				int quantity = cartList.get(productId);
				if (quantity >= 1) {
					cartList.put(productId, quantity - 1);
					cartRepository.save(new CartDetails(email, cartList));
				} else {
					throw new Exception(env.getProperty("cart.empty"));
				}

			}

		} else {
			throw new Exception(env.getProperty("cart.empty"));
		}
	}


	@Override
	public void emptyCart(String email) throws Exception {
		cartRepository.deleteById(email);
		
	}

}
