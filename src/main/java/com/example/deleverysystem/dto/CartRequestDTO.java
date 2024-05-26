package com.example.deleverysystem.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartRequestDTO {
    private Integer menuItemId;
    private Integer quantity;
}