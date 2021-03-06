package com.example.catalogue.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Product")
public class Product {
	
	@Id
	private int productId;
	private String name;
	private String category;
	private double price;
	private double ratings;
	private int quantity;
	
	public Product(int productId, String name, String category, double price, double ratings,int quantity) {
		super();
		this.productId = productId;
		this.name = name;
		this.category = category;
		this.price = price;
		this.ratings = ratings;
		this.quantity = quantity;
	}
		
	public Product() {
		super();
	}


	public int getId() {
		return productId;
	}
	public void setId(int productId) {
		this.productId = productId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getRatings() {
		return ratings;
	}
	public void setRatings(double ratings) {
		this.ratings = ratings;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
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
		Product other = (Product) obj;
		if (productId != other.productId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", category=" + category + ", price=" + price
				+ ", ratings=" + ratings + ", quantity=" + quantity + "]";
	}


}
