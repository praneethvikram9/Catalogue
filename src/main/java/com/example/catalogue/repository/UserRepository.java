package com.example.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.catalogue.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

}
