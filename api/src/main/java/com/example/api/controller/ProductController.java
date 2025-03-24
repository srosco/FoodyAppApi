package com.example.api.controller;

import com.example.api.dto.ProductDto;
import com.example.api.service.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:9000")
public class ProductController {
    
    private final ProductService productService;

    // Constructor injection, Spring will automatically inject the productService
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
        return productService.updateProduct(id, productDto);
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
}
