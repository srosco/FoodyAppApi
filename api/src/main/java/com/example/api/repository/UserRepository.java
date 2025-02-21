package com.example.api.repository;

import com.example.api.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    public List<User> findAll();
    public Optional<User> findByMail(String mail);

}
