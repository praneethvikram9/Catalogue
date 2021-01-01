package com.example.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.catalogue.domain.CartDetails;

public interface CartRepository extends JpaRepository<CartDetails, String> {

}
