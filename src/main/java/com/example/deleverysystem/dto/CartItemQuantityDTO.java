package com.example.deleverysystem.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemQuantityDTO {
    private Integer quantity;
    private Integer id;

    public CartItemQuantityDTO(Integer id, Integer quantity) {

        this.id = id;
        this.quantity = quantity;
    }
}