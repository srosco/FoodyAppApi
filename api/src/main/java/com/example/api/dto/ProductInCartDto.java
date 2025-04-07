package com.example.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductInCartDto {
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("category")
    private String category;

    @JsonProperty("proteins")
    private double proteins;

    @JsonProperty("fibers")
    private double fibers;

    @JsonProperty("calories")
    private double calories;

    @JsonProperty("carbohydrates")
    private double carbohydrates;

    @JsonProperty("quantity_in_grams")
    private double quantityInGrams;

    public ProductInCartDto(long id, String name, String category, double proteins, double fibers, double calories, double carbohydrates, double quantityInGrams) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.proteins = proteins;
        this.fibers = fibers;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.quantityInGrams = quantityInGrams;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFibers() {
        return fibers;
    }

    public void setFibers(double fibers) {
        this.fibers = fibers;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getQuantityInGrams() {
        return quantityInGrams;
    }

    public void setQuantityInGrams(double quantityInGrams) {
        this.quantityInGrams = quantityInGrams;
    }
}
