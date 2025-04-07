package com.example.api.repository;

import com.example.api.model.Cart;
import com.example.api.model.CartProduct;
// import com.example.api.model.CartProductId;
import com.example.api.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    // Add custom query methods if necessary
    CartProduct findByCartAndProduct(Cart cart, Product product);
}