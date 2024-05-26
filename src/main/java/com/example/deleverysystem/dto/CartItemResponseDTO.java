package com.example.deleverysystem.dto;

import com.example.deleverysystem.entity.CartItem;
import com.example.deleverysystem.entity.MenuItems;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemResponseDTO {
    private MenuItems item;
    private Integer quantity;

    public CartItemResponseDTO(CartItem item) {
        this.item = item.getItem();
        quantity = item.getQuantity();
    }
}