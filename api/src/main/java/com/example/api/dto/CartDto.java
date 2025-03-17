package com.example.api.dto;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.api.model.Cart;
import com.example.api.model.User;
import com.example.api.repository.CartRepository;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import lombok.Data;


@Data
public class CartDto {

    @Autowired
    private CartRepository cartRepository;
    private long cartId;

    @JsonProperty("creation_date")
    private Date creation_date;

    @JsonProperty("name")
    private String name;

    @Column(name = "total_calories")
    private double total_calories;

    @Column(name = "total_proteins")
    private double total_proteins;

    @Column(name = "total_fibers")
    private double total_fibers;

    @Column(name = "total_carbohydrates")
    private double total_carbohydrates;

    @JsonProperty("user_id")
    private User userId;

    public CartDto(long cartId, String name, Date creation_date, double total_calories, double total_carbohydrates, double total_fibers, double total_proteins, User userId) {
        this.cartId = cartId;
        this.name = name;
        this.creation_date = creation_date;
        this.total_carbohydrates = total_carbohydrates;
        this.total_calories = total_calories;
        this.total_fibers = total_fibers;
        this.total_proteins = total_proteins;
        // this.userId = userId;
    }

    private CartDto convertToDto(Cart cart) {
        return new CartDto(cart.getId(), cart.getName(), cart.getCreationDate(), cart.getTotalCalories(), cart.getTotal_carbohydrates(), cart.getTotal_fibers(), cart.getTotalProteins(), cart.getUserId());
        // return new CartDto(cart.getId(), cart.getName(), cart.getCreationDate(), cart.getTotalCalories(), cart.getTotal_carbohydrates(), cart.getTotal_fibers(), cart.getTotalProteins());
    }

    public long getId() {
        return cartId;
    }

    public void setId(long cartId) {
        this.cartId = cartId;
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
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
}
