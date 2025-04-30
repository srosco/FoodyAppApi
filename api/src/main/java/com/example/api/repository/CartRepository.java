package com.example.api.repository;

import com.example.api.model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    public List<Cart> findAll();
    
    public void deleteById(Long id);

    public void deleteAll();

    List<Cart> findByUserId(Long userId);
}
