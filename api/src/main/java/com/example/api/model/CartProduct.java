package com.example.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_products")
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Single primary key
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)  // Change to EAGER fetching
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity_in_grams")
    private double quantityInGrams;

    // Default constructor
    public CartProduct() {}

    // Constructor
    public CartProduct(Cart cart, Product product, double quantityInGrams) {
        this.cart = cart;
        this.product = product;
        this.quantityInGrams = quantityInGrams;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantityInGrams() {
        return quantityInGrams;
    }

    public void setQuantityInGrams(double quantityInGrams) {
        this.quantityInGrams = quantityInGrams;
    }
}


