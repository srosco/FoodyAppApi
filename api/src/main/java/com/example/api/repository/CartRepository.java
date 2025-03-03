package com.example.api.repository;

import com.example.api.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart,Long> {
    public List<Cart> findAll();
    
    public void deleteById(Long id);

    public void deleteAll();
}
