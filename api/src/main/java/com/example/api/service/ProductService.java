package com.example.api.service;
import com.example.api.dto.ProductDto;
import com.example.api.model.Product;
import com.example.api.repository.ProductRepository;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private ProductDto convertToDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getCategory(), product.getProteins(), product.getFibers(), product.getCalories(), product.getCarbohydrates());
    }

    private Product convertToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getCategory(), productDto.getProteins(), productDto.getFibers(), productDto.getCalories(), productDto.getCarbohydrates());
    }

    public ProductDto getProduct(final Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDto(product);
    }

    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id){
        productRepository.deleteById(id);
        return true;
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setProteins(productDto.getProteins());
        product.setFibers(productDto.getFibers());
        product.setCalories(productDto.getCalories());
        product.setCarbohydrates(productDto.getCarbohydrates());
        product = productRepository.save(product);
        System.out.println("Received product: " + product);
        return convertToDto(product);
    }

    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setProteins(productDto.getProteins());
        product.setFibers(productDto.getFibers());
        product.setCalories(productDto.getCalories());
        product.setCarbohydrates(productDto.getCarbohydrates());
        Product savedProduct = productRepository.save(product);
        return convertToDto(savedProduct);
    }
}
