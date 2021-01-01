package com.example.catalogue.domain;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="OrderDetails")
public class OrderDetails {

	@Id
	private int orderId;
	private String userEmail;
	@ElementCollection
	private Map<Integer,Product> orderMap; //stores product id & product details
	
	@ElementCollection
	private Map<Integer,Integer> productMap; //stores the product id & quantity ordered
	private String productName;
	private double price;
	private String orderDate;
	private String deliveryAddress;
	private String expectedDeliveryDate;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}
	public void setExpectedDeliveryDate(String expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}
	public Map<Integer, Product> getOrderMap() {
		return orderMap;
	}
	public void setOrderMap(Map<Integer, Product> orderMap) {
		this.orderMap = orderMap;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Map<Integer, Integer> getProductMap() {
		return productMap;
	}
	public void setProductMap(Map<Integer, Integer> productMap) {
		this.productMap = productMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderId;
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
		OrderDetails other = (OrderDetails) obj;
		if (orderId != other.orderId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", userEmail=" + userEmail + ", orderMap=" + orderMap
				+ ", productMap=" + productMap + ", productName=" + productName + ", price=" + price + ", orderDate="
				+ orderDate + ", deliveryAddress=" + deliveryAddress + ", expectedDeliveryDate=" + expectedDeliveryDate
				+ "]";
	}


}
