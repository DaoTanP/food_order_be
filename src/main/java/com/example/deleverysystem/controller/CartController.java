package com.example.deleverysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.deleverysystem.dto.CartRequestDTO;
import com.example.deleverysystem.dto.CartResponseDTO;
import com.example.deleverysystem.entity.Cart;
import com.example.deleverysystem.repository.UserRepository;
import com.example.deleverysystem.service.CartService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String testing() {
        return "CART ACCESS LEVEL";
    }

    @GetMapping("/viewcart")
    public ResponseEntity<CartResponseDTO> getCart(HttpServletRequest request) {
        try {
            Cart cart = cartService.getUserCart(request);
            return ResponseEntity.ok(new CartResponseDTO(cart));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addtocart")
    public ResponseEntity<String> addToCart(HttpServletRequest request, @RequestBody CartRequestDTO cartDTO) {
        try {
            Cart cart = cartService.addToCart(request, cartDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/removefromcart/{cartItemId}")
    public ResponseEntity<String> removeFromCart(HttpServletRequest request, @PathVariable Integer cartItemId) {
        try {
            cartService.removeFromCart(request, cartItemId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "Checkout successfully!";
    }

    @GetMapping("/clearcart")
    public String clearCart() {
        return "Cart cleared successfully!";
    }

    @GetMapping("/updatecart")
    public String updateCart() {
        return "Cart updated successfully!";
    }

}
