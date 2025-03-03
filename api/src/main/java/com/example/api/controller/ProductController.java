package com.example.api.controller;

import com.example.api.dto.ProductDto;
import com.example.api.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:9000")
public class ProductController {
    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping()
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }
    
    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable long id) {
        return productService.getProduct(id);
    }
    
    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }
    
    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable long id) {
        return productService.deleteProduct(id);
    }
    
    @PostMapping("")
    public ProductDto createProduct(@RequestBody ProductDto productDto) throws Exception{
        System.out.println(productDto);
        
        return productService.createProduct(productDto);
    }
  
    // ProductService productRepository;
    // @GetMapping()
    // public ProductDto getBankAccount(@RequestHeader("Authorization") String token) {
    //     if (token.startsWith("Bearer ")) {
    //         token = token.substring(7);
    //     }
    //     String userId = JwtUtil.extractUserId(token);
    //     return productService.getBankAccountById(Long.parseLong(userId));
    // }


    // @PutMapping("{amount}")
    // public ProductDto updateAmount( @RequestHeader("Authorization") String token,@PathVariable double amount )  {
    //     if (token.startsWith("Bearer ")) {
    //         token = token.substring(7);
    //     }
    //     String userId = JwtUtil.extractUserId(token);
    //     return bankAccountService.updateBankAccount(amount,Long.parseLong(userId));
    // }
}
