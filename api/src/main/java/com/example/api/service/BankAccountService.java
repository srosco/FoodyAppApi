package com.example.api.service;

import com.example.api.dto.BankAccountDto;
import com.example.api.model.BankAccount;
import com.example.api.model.User;
import com.example.api.repository.BankAccountRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Data
@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccountDto convertToDto(BankAccount bankAccount) {
        return new BankAccountDto(bankAccount.getId(), bankAccount.getName(), bankAccount.getBalance());
    }

    public BankAccount convertToEntity(BankAccountDto bankAccount) {
        return new BankAccount(bankAccount.getId(), bankAccount.getName(), bankAccount.getBalance());
    }

    public BankAccountDto getBankAccountById(Long id) {
        return convertToDto(Objects.requireNonNull(bankAccountRepository.findByUserId(id).orElse(null)));
    }

    public BankAccount createBankAccount(User user) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(0);
        bankAccount.setName("compte A");
        bankAccount.setUser(user);
        return bankAccountRepository.save(bankAccount);
    }

    public BankAccountDto updateBankAccount(double balance, long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).get();
        if (bankAccount != null) {
            bankAccount.setBalance(balance);
            var save =bankAccountRepository.save(bankAccount);
            return convertToDto(save);
        }
        return null;
    }
}
