package com.example.catalogue.domain;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



@Data
@Entity
@Table(name="USER")
public class User {
	
	@Id
	private String email;
	private String password;
	private int age;
	private String name;
	private String gender;
	private String billingAddress;
	private String deliveryAddress;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	public User() {
		super();
	}
	public User(String email, String password, int age, String name, String gender, String billingAddress,
			String deliveryAddress) {
		super();
		this.email = email;
		this.password = password;
		this.age = age;
		this.name = name;
		this.gender = gender;
		this.billingAddress = billingAddress;
		this.deliveryAddress = deliveryAddress;
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", age=" + age + ", name=" + name + ", gender="
				+ gender + ", billingAddress=" + billingAddress + ", deliveryAddress=" + deliveryAddress + "]";
	}
	
}
