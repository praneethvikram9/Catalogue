package com.example.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.catalogue.domain.OrderDetails;

public interface OrderRepository extends JpaRepository<OrderDetails,Integer> {

}
