package com.example.deleverysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.deleverysystem.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    CartItem findByItem_itemId(Integer itemId);
}