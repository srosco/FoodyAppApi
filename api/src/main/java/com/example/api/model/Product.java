package com.example.api.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;


@Data
    @Entity
    @Table(name = "products")
    public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name="name")
        private String name;

        @Column(name="category")
        private String category;

        @Column(name="proteins")
        private Double proteins;

        @Column(name="fibers")
        private Double fibers;

        @Column(name="calories")
        private Double calories;

        @Column(name="carbohydrates")
        private Double carbohydrates;

        @ManyToMany(mappedBy = "productList")
        private List<Cart> carts;

    // @OneToOne
    // @JoinColumn(name = "bank_account_id")
    // private BankAccount bankAccount;

    public Product() {

    }

    public Product(long id, String name, String category, double proteins, double fibers, double calories, double carbohydrates) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.proteins = proteins;
        this.fibers = fibers;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
    }

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

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
