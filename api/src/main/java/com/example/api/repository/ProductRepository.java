package com.example.api.repository;

import com.example.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // JpaRepository already provides the following methods:
    // - findById(Long id)
    // - save(Product product)
    // - findAll()
    // - deleteById(Long id)
    
    List<Product> findAll(); // You can use this to get all products if needed

    void deleteById(Long id); // Method to delete a product by its ID

    void deleteAll(); // Method to delete all products
}