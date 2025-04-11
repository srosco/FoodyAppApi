package com.example.api.controller;

import com.example.api.dto.CartDto;
import com.example.api.dto.ProductInCartDto;
import com.example.api.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import javax.servlet.http.HttpServletRequest;

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
        System.out.println("Received cart: " + cartDto);

        // Call the service method to handle the update logic
        return cartService.updateCart(id, cartDto);
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

    @DeleteMapping("/{cartId}/products")
    public CartDto removeProductsFromCart(@PathVariable long cartId, @RequestBody List<Long> productIdsToRemove) {
        System.out.println("List of carts to delete : " + productIdsToRemove);
        // Call the service method to remove products from cart
        return cartService.removeProductsFromCart(cartId, productIdsToRemove);
    }

    @PostMapping("/{cartId}/products")
    public ResponseEntity<CartDto> addProductsToCart(@PathVariable long cartId, @RequestBody List<ProductInCartDto> productsToAdd) {
        // Call the service to add products to the cart
        CartDto updatedCartDto = cartService.addProductsToCart(cartId, productsToAdd);

        // Return the updated CartDto
        return ResponseEntity.ok(updatedCartDto);
    }
    
}
