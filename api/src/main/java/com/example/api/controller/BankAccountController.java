package com.example.api.controller;

import com.example.api.JwtUtil;
import com.example.api.dto.BankAccountDto;
import com.example.api.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank_account")
@CrossOrigin(origins = "http://localhost:4200")
public class BankAccountController {
    @Autowired
    BankAccountService bankAccountService;
    @GetMapping()
    public BankAccountDto getBankAccount(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String userId = JwtUtil.extractUserId(token);
        return bankAccountService.getBankAccountById(Long.parseLong(userId));
    }


    @PutMapping("{amount}")
    public BankAccountDto updateAmount( @RequestHeader("Authorization") String token,@PathVariable double amount )  {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String userId = JwtUtil.extractUserId(token);
        return bankAccountService.updateBankAccount(amount,Long.parseLong(userId));
    }
}
