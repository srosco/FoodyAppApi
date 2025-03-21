package com.example.api.service;
import com.example.api.dto.CartDto;
import com.example.api.dto.ProductDto;
import com.example.api.model.Cart;
import com.example.api.model.Product;
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
    private ProductService productService;

    // Conversion from ProductDto to Product
    private Product convertToProduct(ProductDto productDto) {
        // Fetch the product entity from the database using the Product ID from the DTO
        return productService.findProductById(productDto.getId());
    }

    @Autowired
    private CartRepository cartRepository;

    private CartDto convertToDto(Cart cart) {
        return new CartDto(cart.getId(), cart.getName(), cart.getCreationDate(), cart.getTotalCalories(), cart.getTotal_carbohydrates(), cart.getTotal_fibers(), cart.getTotalProteins(), cart.getUserId());
    }

    private Cart convertToEntity(CartDto cartDto) {
        return new Cart(cartDto.getId(), cartDto.getName(), cartDto.getCreationDate(), cartDto.getTotalCalories(), cartDto.getTotal_carbohydrates(), cartDto.getTotal_fibers(), cartDto.getTotalProteins(), cartDto.getUserId());
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
        cart.setUserId(cartDto.getUserId());
        cart.setTotalCalories(cartDto.getTotalCalories());
        cart.setTotalCarbohydrates(cartDto.getTotalCarbohydrates());
        cart.setTotalFibers(cartDto.getTotal_fibers());
        cart.setTotalProteins(cartDto.getTotal_proteins());

        if (cartDto.getProducts() != null && !cartDto.getProducts().isEmpty()) {
            List<Product> products = cartDto.getProducts().stream()
                .map(this::convertToProduct)  // Convert ProductDto to Product
                .collect(Collectors.toList());
    
            cart.setProductList(products);
        }

        // Save the cart entity
        cart = cartRepository.save(cart);
        System.out.println("Received cart: " + cart);
        return convertToDto(cart);
    }

    public CartDto updateCart(CartDto cartDto) {
        Cart cart = cartRepository.findById(cartDto.getId()).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.setName(cartDto.getName());
        cart.setCreationDate(cartDto.getCreationDate());
        cart.setUserId(cartDto.getUserId());
        cart.setTotalCalories(cartDto.getTotalCalories());
        cart.setTotalCarbohydrates(cartDto.getTotalCarbohydrates());
        cart.setTotalFibers(cartDto.getTotal_fibers());
        cart.setTotalProteins(cartDto.getTotal_proteins());
        Cart savedCart = cartRepository.save(cart);
        return convertToDto(savedCart);
    }
}
