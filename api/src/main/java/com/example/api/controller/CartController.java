package com.example.api.controller;

import com.example.api.dto.CartDto;
import com.example.api.service.CartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin(origins = "http://localhost:9000")
public class CartController {
    @Autowired
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    
    @GetMapping("all")
    public List<CartDto> getCarts() {
        return cartService.getCarts();
    }
    
    @GetMapping("/{id}")
    public CartDto getCart(@PathVariable long id) {
        return cartService.getCart(id);
    }
    
    @PutMapping("/{id}")
    public CartDto updateCart(@PathVariable long id, @RequestBody CartDto cartDto) {
        return cartService.updateCart(cartDto);
    }
    
    @DeleteMapping("/{id}")
    public boolean deleteCart(@PathVariable long id) {
        return cartService.deleteCart(id);
    }
    
    @PostMapping("")
    public CartDto createCart(@RequestBody CartDto cartDto) throws Exception {
        System.out.println("Received cart: " + cartDto);
        return cartService.createCart(cartDto);
    }
}
