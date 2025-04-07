package com.example.api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "creation_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate creationDate;

    @Column(name = "name")
    private String name;

    @Column(name = "total_calories")
    private double totalCalories;

    @Column(name = "total_proteins")
    private double totalProteins;

    @Column(name = "total_fibers")
    private double totalFibers;

    @Column(name = "total_carbohydrates")
    private double totalCarbohydrates;

    // Many-to-one relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // One-to-many relationship with CartProduct
    @OneToMany
    @JoinColumn(name = "cart_id")
    private List<CartProduct> cartProducts;

    // Default constructor
    public Cart() {}

    // Constructor with parameters
    public Cart(long id, String name, LocalDate creationDate, double totalCalories, double totalCarbohydrates, 
                double totalFibers, double totalProteins, User user) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.totalCalories = totalCalories;
        this.totalCarbohydrates = totalCarbohydrates;
        this.totalFibers = totalFibers;
        this.totalProteins = totalProteins;
        this.user = user;
    }

    // Getters and setters
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<CartProduct> getProductList() {
        return cartProducts;
    }

    public void setProductList(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
