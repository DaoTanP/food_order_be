package com.example.deleverysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.deleverysystem.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserInfo_userId(Integer userId);
}