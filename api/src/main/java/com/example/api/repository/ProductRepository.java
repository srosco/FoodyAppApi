package com.example.api.repository;

import com.example.api.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
// public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findAll();

    // public boolean existsProductByName(String name);
    
    public void deleteById(Long id);

    public void deleteAll();
}
