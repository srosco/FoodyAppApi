package com.example.api.repository;

import com.example.api.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    public List<Product> findAll();
    
    public void deleteById(Long id);

    public void deleteAll();
}
