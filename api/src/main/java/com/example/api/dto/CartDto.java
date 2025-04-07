package com.example.api.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CartDto {

    private long id;

    @JsonProperty("creation_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate creationDate;

    @JsonProperty("name")
    private String name;

    @JsonProperty("total_calories")
    private double totalCalories;

    @JsonProperty("total_proteins")
    private double totalProteins;

    @JsonProperty("total_fibers")
    private double totalFibers;

    @JsonProperty("total_carbohydrates")
    private double totalCarbohydrates;

    // @JsonProperty("products")
    // private List<ProductDto> products;

    @JsonProperty("products")
    private List<ProductInCartDto> products;

    @JsonProperty("user_id")
    private long userId;  // User is now a long type, referring to userId

    public CartDto() {
        // Default constructor for Jackson
    }

    // Constructor to initialize CartDto with all fields
    // public CartDto(long id, String name, LocalDate creationDate, double totalCalories, double totalCarbohydrates,
    //                double totalFibers, double totalProteins, long userId, List<ProductDto> products) {
    //     this.id = id;
    //     this.name = name;
    //     this.creationDate = creationDate;
    //     this.totalCalories = totalCalories;
    //     this.totalCarbohydrates = totalCarbohydrates;
    //     this.totalFibers = totalFibers;
    //     this.totalProteins = totalProteins;
    //     this.userId = userId;
    //     this.products = products;
    // }

    public CartDto(long id, String name, LocalDate creationDate, double totalCalories, double totalCarbohydrates,
                   double totalFibers, double totalProteins, long userId, List<ProductInCartDto> products) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.totalCalories = totalCalories;
        this.totalCarbohydrates = totalCarbohydrates;
        this.totalFibers = totalFibers;
        this.totalProteins = totalProteins;
        this.userId = userId;
        this.products = products;
    }

    // Getters and setters are handled by Lombok's @Data annotation
    // Example of manually created getter and setter for id:
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    public double getTotalCarbohydrates() {
        return totalCarbohydrates;
    }

    public void setTotalCarbohydrates(double totalCarbohydrates) {
        this.totalCarbohydrates = totalCarbohydrates;
    }

    public double getTotalFibers() {
        return totalFibers;
    }

    public void setTotalFibers(double totalFibers) {
        this.totalFibers = totalFibers;
    }

    public double getTotalProteins() {
        return totalProteins;
    }

    public void setTotalProteins(double totalProteins) {
        this.totalProteins = totalProteins;
    }

    // public List<ProductDto> getProducts() {
    //     return products;
    // }

    // public void setProducts(List<ProductDto> products) {
    //     this.products = products;
    // }
    
    public List<ProductInCartDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInCartDto> products) {
        this.products = products;
    }

    public long getUserId() {
        return userId; // Returning the userId instead of the full User object
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
