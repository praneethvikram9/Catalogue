package com.example.catalogue.domain;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CartDetails")
public class CartDetails {

	@Id
	private String email;
	
	@ElementCollection
	private Map<Integer,Integer>cartList;

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Map<Integer, Integer> getCartList() {
		return cartList;
	}
	public void setCartList(Map<Integer, Integer> cartList) {
		this.cartList = cartList;
	}
	public CartDetails() {
		super();
	}

	public CartDetails(String email, Map<Integer, Integer> cartList) {
		super();
		this.email = email;
		this.cartList = cartList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartDetails other = (CartDetails) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CartDetails [email=" + email + ", cartList=" + cartList + "]";
	}

}
