package com.example.deleverysystem.service;

import com.example.deleverysystem.dto.CartItemQuantityDTO;
import com.example.deleverysystem.dto.CartRequestDTO;
import com.example.deleverysystem.entity.*;
import com.example.deleverysystem.exception.ErrorMessage;
import com.example.deleverysystem.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    public final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private MenuItemsRepository menuItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart getUserCart(HttpServletRequest request) throws Exception {
        Integer userId = tokenService.getIdFromToken(request);
        ApplicationUser applicationUser = userRepository.findById(userId)
                .orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "User not found: "));
        UserInfo userInfo = applicationUser.getUserInfo();
        return cartRepository.findByUserInfo_userId(userInfo.getUserId());
    }

    public Cart createCart(UserInfo userInfo) throws Exception {
        Cart cart = new Cart();
        cart.setUserInfo(userInfo);
        return cartRepository.save(cart);
    }

    public Cart addToCart(HttpServletRequest request, CartRequestDTO cartDTO) throws Exception {
        Cart cart = getUserCart(request);

        MenuItems menuItem = menuItemRepository.findById(cartDTO.getMenuItemId())
                .orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "Menu item not found: "));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(menuItem);
        cartItem.setQuantity(cartDTO.getQuantity());
        CartItem newCartItem = cartItemRepository.save(cartItem);
        List<CartItem> itemsInCart = cart.getCartItems();
        itemsInCart.add(newCartItem);

        cart.setCartItems(itemsInCart);
        return cartRepository.save(cart);
    }

    public void removeFromCart(HttpServletRequest request, Integer cartItemId) throws Exception {
        Cart cart = getUserCart(request);

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "Cart item not found: "));
        if (cart.getCartItems().indexOf(cartItem) == -1)
            throw new ErrorMessage(HttpStatus.BAD_REQUEST, "access denied");

        cart.getCartItems().remove(cartItem);
        cartRepository.save(cart);

        cartItemRepository.deleteById(cartItemId);
    }

    public void updateQuantity(HttpServletRequest request, CartItemQuantityDTO dto) throws Exception {
        Cart cart = getUserCart(request);

        CartItem cartItem = cartItemRepository.findById(dto.getId())
                .orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "Cart item not found: "));
        if (cart.getCartItems().indexOf(cartItem) == -1)
            throw new ErrorMessage(HttpStatus.BAD_REQUEST, "access denied");

        cartItem.setQuantity(dto.getQuantity());

        cartItemRepository.save(cartItem);
    }
}