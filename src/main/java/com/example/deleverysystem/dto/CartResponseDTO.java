package com.example.deleverysystem.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.deleverysystem.entity.Cart;
import com.example.deleverysystem.entity.CartItem;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartResponseDTO {
    private List<CartItemResponseDTO> cartItems;

    public CartResponseDTO(Cart cart) {
        List<CartItem> cartItems = cart.getCartItems();

        if (cartItems.isEmpty())
            return;

        this.cartItems = new ArrayList<CartItemResponseDTO>();

        for (CartItem item : cartItems) {
            this.cartItems.add(new CartItemResponseDTO(item));
        }
    }
}