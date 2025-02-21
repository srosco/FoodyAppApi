package com.example.api.repository;

import com.example.api.model.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount,Long> {
    Optional<BankAccount> findByUserId(Long userId);
}
