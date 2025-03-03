package com.example.api.dto;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.api.model.Cart;
import com.example.api.model.User;
import com.example.api.repository.CartRepository;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class CartDto {

    @Autowired
    private CartRepository cartRepository;
    private long cart_id;

    @JsonProperty("creation_date")
    private Date creation_date;

    @JsonProperty("name")
    private String name;

    @JsonProperty("user")
    private User user;

    public CartDto(long cart_id, String name, Date creation_date, User user) {
        this.cart_id = cart_id;
        this.name = name;
        this.creation_date = creation_date;
        this.user = user;
    }

    private CartDto convertToDto(Cart cart) {
        return new CartDto(cart.getId(), cart.getName(), cart.getCreationDate(), cart.getUser());
    }

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
