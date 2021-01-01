package com.example.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.catalogue.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
