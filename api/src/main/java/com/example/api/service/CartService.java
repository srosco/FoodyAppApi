package com.example.api.service;
import com.example.api.dto.CartDto;
import com.example.api.model.Cart;
import com.example.api.repository.CartRepository;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    private CartDto convertToDto(Cart cart) {
        return new CartDto(cart.getId(), cart.getName(), cart.getCreationDate(), cart.getUser());
    }

    private Cart convertToEntity(CartDto cartDto) {
        return new Cart(cartDto.getId(), cartDto.getName(), cartDto.getCreationDate(), cartDto.getUser());
    }

    public CartDto getCart(final Long id){
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        return convertToDto(cart);
    }

    public List<CartDto> getCarts() {
        return cartRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public boolean deleteCart(Long id){
        cartRepository.deleteById(id);
        return true;
    }

    public CartDto createCart(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setName(cartDto.getName());
        cart.setCreationDate(cartDto.getCreationDate());
        cart.setUser(cartDto.getUser());
        cart = cartRepository.save(cart);
        System.out.println("Received cart: " + cart);
        return convertToDto(cart);
    }

    public CartDto updateCart(CartDto cartDto) {
        Cart cart = cartRepository.findById(cartDto.getId()).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.setName(cartDto.getName());
        cart.setCreationDate(cartDto.getCreationDate());
        cart.setUser(cartDto.getUser());
        Cart savedCart = cartRepository.save(cart);
        return convertToDto(savedCart);
    }
}
