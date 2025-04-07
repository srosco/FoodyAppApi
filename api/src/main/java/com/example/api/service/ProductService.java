package com.example.api.service;

import com.example.api.dto.ProductDto;
import com.example.api.exception.ProductNameAlreadyExistsException;
import com.example.api.model.Product;
import com.example.api.repository.ProductRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
// import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    // Constructor injection of ProductRepository
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductDto convertToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getProteins(),
                product.getFibers(),
                product.getCalories(),
                product.getCarbohydrates()
        );
    }

    private Product convertToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getCategory(),
                productDto.getProteins(), productDto.getFibers(), productDto.getCalories(),
                productDto.getCarbohydrates());
    }

    public ProductDto getProduct(final long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDto(product);
    }

    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        return true;
    }

    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        Product product = convertToEntity(productDto);
        try {
            product = productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate entry error
            throw new ProductNameAlreadyExistsException(
                    "Product with name " + productDto.getName() + " already exists.");
        }
        return convertToDto(product);
    }

    @Transactional
    public ProductDto updateProduct(final long id, ProductDto productDto) {
        // Fetch the product by its ID
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Debugging the version field
        // System.out.println("Current version: " + product.getVersion());

        // Update product fields based on the provided ProductDto
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setProteins(productDto.getProteins());
        product.setFibers(productDto.getFibers());
        product.setCalories(productDto.getCalories());
        product.setCarbohydrates(productDto.getCarbohydrates());

        // Save and flush the updated product
        Product savedProduct = productRepository.saveAndFlush(product);

        // Debugging the version field after save
        // System.out.println("Updated version: " + savedProduct.getVersion());

        // Return the updated Product as a ProductDto
        return convertToDto(savedProduct);
    }

    public Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }
}
