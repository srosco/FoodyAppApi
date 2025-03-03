package com.example.api.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cart_id;

    @Column(name = "creation_date")
    private Date creation_date;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "cart_products", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    public Cart() {

    }

    public Cart(long cart_id, String name, Date creation_date, User user) {
        this.cart_id = cart_id;
        this.name = name;
        this.creation_date = creation_date;
        this.user = user;
    }

    // Getters and setters
    public long getId() {
        return cart_id;
    }

    public void setId(long cart_id) {
        this.cart_id = cart_id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}