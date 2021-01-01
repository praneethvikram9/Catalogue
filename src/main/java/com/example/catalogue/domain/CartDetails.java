package com.example.catalogue.domain;

import java.util.List;

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
	private List<Integer> products;

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Integer> getProducts() {
		return products;
	}
	public void setProducts(List<Integer> products) {
		this.products = products;
	}
	
	
	public CartDetails() {
		super();
	}
	public CartDetails(String email, List<Integer> products) {
		super();
		this.email = email;
		this.products = products;
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
		return "CartDetails [email=" + email + ", products=" + products + "]";
	}

	
}
