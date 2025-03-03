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
    
    @GetMapping()
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
    public CartDto createCart(@RequestBody CartDto cartDto) throws Exception{
        System.out.println(cartDto);
        
        return cartService.createCart(cartDto);
    }
  
    // CartService cartRepository;
    // @GetMapping()
    // public CartDto getBankAccount(@RequestHeader("Authorization") String token) {
    //     if (token.startsWith("Bearer ")) {
    //         token = token.substring(7);
    //     }
    //     String userId = JwtUtil.extractUserId(token);
    //     return cartService.getBankAccountById(Long.parseLong(userId));
    // }


    // @PutMapping("{amount}")
    // public CartDto updateAmount( @RequestHeader("Authorization") String token,@PathVariable double amount )  {
    //     if (token.startsWith("Bearer ")) {
    //         token = token.substring(7);
    //     }
    //     String userId = JwtUtil.extractUserId(token);
    //     return bankAccountService.updateBankAccount(amount,Long.parseLong(userId));
    // }
}
