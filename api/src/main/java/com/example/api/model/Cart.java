package com.example.api.model;

import java.sql.Date;
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
    private Date creation_date;

    @Column(name = "name")
    private String name;

    @Column(name = "total_calories")
    private double total_calories;

    @Column(name = "total_proteins")
    private double total_proteins;

    @Column(name = "total_fibers")
    private double total_fibers;

    @Column(name = "total_carbohydrates")
    private double total_carbohydrates;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;

    @ManyToMany
    @JoinTable(
        name = "cart_products",
        joinColumns = @JoinColumn(name = "cart_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productList;

    public Cart() {

    }

    public Cart(long id, String name, Date creation_date, double total_calories, double total_fibers, double total_carbohydrates, double total_proteins, User user_id) {
        this.id = id;
        this.name = name;
        this.creation_date = creation_date;
        this.total_calories = total_calories;
        this.total_carbohydrates = total_carbohydrates;
        this.total_fibers = total_fibers;
        this.total_proteins = total_proteins;
        this.user_id = user_id;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creation_date;
    }

    public void setCreationDate(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUserId() {
        return user_id;
    }

    public void setUserId(User user_id) {
        this.user_id = user_id;
    }

    public double getTotalCalories() {
        return total_calories;
    }

    public void setTotalCalories(double total_calories) {
        this.total_calories = total_calories;
    }

    public double getTotalCarbohydrates() {
        return total_calories;
    }

    public void setTotalCarbohydrates(double total_calories) {
        this.total_calories = total_calories;
    }

    public double getTotalFibers() {
        return total_fibers;
    }

    public void setTotalFibers(double total_fibers) {
        this.total_fibers = total_fibers;
    }

    public double getTotalProteins() {
        return total_proteins;
    }

    public void setTotalProteins(double total_proteins) {
        this.total_proteins = total_proteins;
    }

    public List<Product> getProducts() {
        return productList;
    }

    public void setProducts(List<Product> productList) {
        this.productList = productList;
    }

}